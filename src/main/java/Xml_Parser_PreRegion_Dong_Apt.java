import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Xml_Parser_PreRegion_Dong_Apt implements Xml_Parser {
    public Xml_Parser_PreRegion_Dong_Apt(){
        try{
            apiParserSearch();
        }catch (Exception e){
            e.printStackTrace();
        }
        finally {

        }
    }
    public void apiParserSearch() throws Exception {
        new Excel_Writer_Xml_Region_Apt_Dong().Excel_Writer_Xml_Region_Apt(returnList());
    }

    public String getURLParam(String region,String aptName) throws Exception{
        String url = JUSO_URL + JUSO_ROF + JUSO_APT_NAME + URLEncoder.encode(region+" "+aptName,"UTF-8") + JUSO_KEY;
        return url;

    }



    public ArrayList<DTO> returnList() throws Exception {
        FileInputStream fis = new FileInputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region_Apt.xls");
        Workbook wbk = new HSSFWorkbook(fis);
        Sheet sheet = wbk.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        ArrayList<DTO> list = new ArrayList<DTO>();
        String checkAptName=null;
        String error="Error";
        URL url=null;

        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);

            checkAptName=row.getCell(3).getStringCellValue();
            /**해당 전처리 부분은 있어도되곡 없어도 되는부분입니다.
             * 바로 밑에 예외처리부분에서 이 모든것을 한번에 해결해줍니다.
             * 시간단축이 될 수 있을지는 모르지만 후일 용량문제를 고려해보았을때
             * 해당 부분은 지우셔도 됩니다.*/
            if(checkAptName.contains("다세대")){
                checkAptName=checkAptName.replaceAll("다세대","");
                if(checkAptName.contains("(")){
                    System.out.println("다세대 주소 + ( 문제 발견...");
                    checkAptName.replaceAll("\\("," ");/**ReplaceAll 메소드에서 ( 에 대한 표현식은 따로 특수한 경우이므로 앞에 \\를 붙여줘야함*/
                    checkAptName.replaceAll("\\)"," ");
                    url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName));
                }
                else if(checkAptName.contains("_")){
                    System.out.println("다세대 주소 + _ 문제 발견...");
                    checkAptName.replaceAll("_","");
                    url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName));
                }
            }
            else if(checkAptName.endsWith(".")){
                checkAptName.replaceAll(".","");
                url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName));

            }
            else if(checkAptName.contains("다가구")){
                if(checkAptName.contains("다가구주택")){
                    url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName));
                }
                else{
                    checkAptName.replaceAll("\\("," ");/**ReplaceAll 메소드에서 ( 에 대한 표현식은 따로 특수한 경우이므로 앞에 \\를 붙여줘야함*/
                    checkAptName.replaceAll("\\)"," ");
                    url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName));

                }

            }
            else{/**가장 일반적인 주소입력 방식*/
                System.out.println("일반주소 변환중...");
                url = new URL(getURLParam(row.getCell(2).getStringCellValue(),row.getCell(3).getStringCellValue()));
            }


            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();

            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            xpp.setInput(bis, "utf-8");

            String tag = null;
            int event_type = xpp.getEventType();

            String jibunAddr = null, siNm = null, sggNm = null, emdNm = null,totalCount=null;
            int sub=1;


            while (event_type != xpp.END_DOCUMENT) {

                if (event_type == xpp.START_TAG) {
                    tag = xpp.getName();
                }
                else if (event_type == xpp.TEXT) {

                    if (tag.equals("jibunAddr")) {
                        jibunAddr = xpp.getText();
                    }
                    else if (tag.equals("siNm")) {
                        siNm = xpp.getText();
                    }
                    else if (tag.equals("sggNm")) {
                        sggNm = xpp.getText();
                    }
                    else if (tag.equals("emdNm")) {
                        emdNm = xpp.getText();
                    }
                    else if(tag.equals("totalCount")){
                        totalCount=xpp.getText();
                    }

                } else if (event_type == xpp.END_TAG) {
                    if(Integer.parseInt(totalCount)==0){
                        /**가장 중요한부분
                         * 주소 api를 이용해서 주소를 입력하였을 때,
                         * 절대 값이 없이나오지는 않는 특성을 고려
                         * 결과가 나오지 않으면 해당 아파트 이름 한글자씩을 빼서 다시 url로 돌림*/
                        url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName.substring(0,checkAptName.length()-sub)));
                        sub++;
                        bis=new BufferedInputStream(url.openStream());
                        xpp.setInput(bis,"utf-8");
                    }
                    else{
                        tag = xpp.getName();
                        if (tag.equals("juso")) {

                            DTO entity = new DTO();

                            entity.setCityCode(row.getCell(0).getStringCellValue());
                            entity.setAptCode(row.getCell(1).getStringCellValue());
                            entity.setCitySggName(row.getCell(2).getStringCellValue());
                            entity.setAptName(row.getCell(3).getStringCellValue());
                            entity.setJibunAddr(jibunAddr);
                            entity.setSiNm(siNm);
                            entity.setSggNm(sggNm);
                            entity.setEmdNm(emdNm);
                            list.add(entity);
                        }

                    }



                }
                event_type = xpp.next();
            }
        }
        // 엑셀파일을 쓰는 부분
        fis.close();


        return list;
    }


    public void printList(ArrayList<DTO> list) {

    }


    public ArrayList<DTO> returnList(int year) throws Exception {

        return null;
    }
    public String getURLParam(String data){
        return null;

    }
}
