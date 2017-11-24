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
        FileInputStream fis = new FileInputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region_Apt.xls");/**반드시 경로수정 요망 불로얼 엑셀파일Food_Region_Apt*/
        Workbook wbk = new HSSFWorkbook(fis);
        Sheet sheet = wbk.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        ArrayList<DTO> list = new ArrayList<DTO>();
        String checkAptName=null;
        String error="Error";
        URL url=null;

        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            /**전처리 경우의수
             * 해당 아파트 이름을 곧이곧대로 입력하면 결과가 나오지않는 이상현상이 발생
             * 이를 위해 전처리필요
             * 예를 들면
             * 1. 다세대 라는 단어가 들어간 아파트이름 중 다세대 이름을 빼면 결과가 정상적으로 나오는 경우
             * 2. 다가구 라는 단어가 들어간 아파트이름들을 해당단어를 제외하고 다시입력하면 정상결과
             * 3. 이외에 다양한 예외처리 추가바람*/
            checkAptName=row.getCell(3).getStringCellValue();
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
            else if(checkAptName.contains("해당없음")){
                checkAptName.replaceAll("해당없음","");
                url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName));
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


            while (event_type != xpp.END_DOCUMENT) {

                if (event_type == xpp.START_TAG) {
                    tag = xpp.getName();
                }
                else if (event_type == xpp.TEXT) {

                    if (tag.equals("jibunAddr")) {
                        jibunAddr = xpp.getText();
                    } else if (tag.equals("siNm")) {
                        siNm = xpp.getText();
                    } else if (tag.equals("sggNm")) {
                        sggNm = xpp.getText();
                    } else if (tag.equals("emdNm")) {
                        emdNm = xpp.getText();
                    }else if(tag.equals("totalCount")){
                        totalCount=xpp.getText();
                    }

                } else if (event_type == xpp.END_TAG) {
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

                    else if(tag.equals("totalCount")){
                        if(Integer.parseInt(totalCount)==0) {

                            System.out.println(row.getCell(3).getStringCellValue() + " 해당 주소 오류!");
                            DTO entity = new DTO();
                            entity.setCityCode(row.getCell(0).getStringCellValue());
                            entity.setAptCode(row.getCell(1).getStringCellValue());
                            entity.setCitySggName(row.getCell(2).getStringCellValue());
                            entity.setAptName(row.getCell(3).getStringCellValue());
                            entity.setJibunAddr(error);
                            entity.setSiNm(error);
                            entity.setSggNm(error);
                            entity.setEmdNm(error);

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
