import au.com.bytecode.opencsv.CSVReader;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Xml_Parser_Population implements Xml_Parser {
    public Xml_Parser_Population(int year){
        try{
            apiParserSearch(year);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getURLParam(String city,String data) throws Exception{
        String url = JUSO_URL + JUSO_ROF + JUSO_APT_NAME + URLEncoder.encode(city+" "+data,"UTF-8") + JUSO_KEY;
        return url;
    }

    public void apiParserSearch(int year) throws Exception{
        new Csv_Writer_Excel_Population(returnList(year),year);

    }

    public ArrayList<DTO> returnList(int year) throws Exception {
        FileInputStream fis=new FileInputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\OctagonExcel.xls");/** 인구 데이터 엑셀파일 저장위치 불러오기 */
        CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\TC_SPBE17_2015.csv"), "UTF-8"), ',', '"', 1);
        List<String[]> csvBody=reader.readAll();
        reader.readNext();
        Workbook wbk=new HSSFWorkbook(fis);
        Sheet sheet = wbk.getSheetAt(0);
        int rowNum=sheet.getPhysicalNumberOfRows();
        URL url=null;

        HashMap<String,ArrayList<DTO>> map=new HashMap<String, ArrayList<DTO>>();
        ArrayList<DTO> list=new ArrayList<DTO>();

        Loop : for(int i=4;i<rowNum;i++){
            Row row=sheet.getRow(i);
            if(row.getCell(i).getStringCellValue().equals("소계")){
                break Loop;
            }
            else{
                url = new URL(getURLParam("서울",row.getCell(2).getStringCellValue()));
            }

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            BufferedInputStream bis = new BufferedInputStream(url.openStream());
            xpp.setInput(bis, "utf-8");

            String tag = null;
            int event_type = xpp.getEventType();
            String dongName=null,emdNm=null,totalCount=null;
            while (event_type != xpp.END_DOCUMENT) {

                if (event_type == xpp.START_TAG) {
                    tag = xpp.getName();
                }
                else if (event_type == xpp.TEXT) {

                    if (tag.equals("emdNm")) {
                        emdNm = xpp.getText();
                    }
                    else if(tag.equals("totalCount")){
                        totalCount=xpp.getText();
                    }

                } else if (event_type == xpp.END_TAG) {
                    if(Integer.parseInt(totalCount)==0){
//                        /**가장 중요한부분
//                         * 주소 api를 이용해서 주소를 입력하였을 때,
//                         * 절대 값이 없이나오지는 않는 특성을 고려
//                         * 결과가 나오지 않으면 해당 아파트 이름 한글자씩을 빼서 다시 url로 돌림*/
//                        url=new URL(getURLParam(row.getCell(2).getStringCellValue(),checkAptName.substring(0,checkAptName.length()-sub)));
//                        sub++;
//                        bis=new BufferedInputStream(url.openStream());
//                        xpp.setInput(bis,"utf-8");
                        break Loop;
                    }
                    else{
                        tag = xpp.getName();
                        if (tag.equals("juso")) {
                            if(map.containsKey(emdNm)){
                                ArrayList<DTO> hash_list=map.get(emdNm);
                                DTO update_entity=new DTO();
                                int one_furniture=0,two_furniture=0,three_furniture=0,four_furniture=0,five_furniture=0,six_furniture=0,seven_furniture=0;
                                for(DTO entity : hash_list){


                                }
                                break Loop;
                            }
                            else{
                                DTO entity=new DTO();
                                entity.setDongName(emdNm);
                                entity.setOne_furniture(row.getCell(4).getStringCellValue());
                                entity.setTwo_furniture_(row.getCell(5).getStringCellValue());
                                entity.setThree_furniture(row.getCell(6).getStringCellValue());
                                entity.setFour_furniture(row.getCell(7).getStringCellValue());
                                entity.setFive_furniture(row.getCell(8).getStringCellValue());
                                entity.setSix_furniture(row.getCell(9).getStringCellValue());
                                entity.setSeven_furniture(row.getCell(10).getStringCellValue());

                            }


                        }

                    }



                }
                event_type = xpp.next();

            }


        }
        return list;
    }

    public String getURLParam(String data) {
        return null;
    }

    public void printList(ArrayList<DTO> list) {

    }


    public void apiParserSearch() throws Exception {

    }

}
