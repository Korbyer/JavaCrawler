import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by admin on 2017. 9. 26..
 */
public class Xml_Parser_Region_Apt implements Xml_Parser {


    public Xml_Parser_Region_Apt(){
        try{
            System.out.println("Xml_Parser_Region_Apt is running Now...");
            apiParserSearch();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public void apiParserSearch() throws Exception {

        FileInputStream fis=new FileInputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region.xls");
        Workbook wbk=new HSSFWorkbook(fis);
        Sheet sheet = wbk.getSheetAt(0);
        int rowNum=sheet.getPhysicalNumberOfRows();
        ArrayList<DTO> list=new ArrayList<DTO>();

        for (int i=1;i<rowNum;i++){
            Row row=sheet.getRow(i);

            URL url = new URL(getURLParam(row.getCell(0).getStringCellValue()));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xp = factory.newPullParser();
            BufferedInputStream bf = new BufferedInputStream(url.openStream());
            xp.setInput(bf, "utf-8");

            String tag = null;
            int eventType = xp.getEventType();



        /*각 기능마다 태그들을 달리 설정*/
            int disYear, disMonth, disDate, disDay, disQuantity, disQuantityRate, disCount, disCountRate;
            String cityCode = null, citySidoName = null, citySggName = null, aptCode = null, aptName = null;

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tag = xp.getName();

                } else if (eventType == XmlPullParser.TEXT) {

                    if (tag.equals("cityCode")) {
                        cityCode = xp.getText();
                    } else if (tag.equals("aptCode")) {
                        aptCode = xp.getText();
                    } else if (tag.equals("citySggName")) {
                        citySggName = xp.getText();
                    } else if (tag.equals("aptName")) {
                        aptName = xp.getText();
                    } else if (tag.equals("citySidoName")) {
                        citySidoName = xp.getText();
                    }


                } else if (eventType == XmlPullParser.END_TAG) {
                    tag = xp.getName();
                    if (tag.equals("list")) {
                        DTO entity = new DTO();

                        entity.setCityCode(cityCode);
                        entity.setAptCode(aptCode);
                        entity.setCitySggName(citySggName);
                        entity.setAptName(aptName);
                        entity.setCitySidoName(citySidoName);

                        list.add(entity);


                    }
                }
                eventType = xp.next();
            }
            //printList(list);


        }
        new Excel_Writer_Xml_Region_Apt().Excel_Writer_Xml_Region_Apt(list);
        fis.close();
    }

    public ArrayList<DTO> returnList(int year) throws Exception {
        return null;
    }

    public String getURLParam(String data) {
        String url=PHARAM_URL+METHOD_GET_APT+KEY+ROF+CITY_CODE;
        if(data!=null){
            url=url+data;
        }
        return url;

    }



    public void printList(ArrayList<DTO> list) {

    }

//    public ArrayList<DTO> extractData_Excel(Workbook workbook){
//        ArrayList<DTO> list=new ArrayList<DTO>();
//        Sheet sheet = workbook.getSheetAt(0);
//        int rowNum=sheet.getPhysicalNumberOfRows();
//        DTO entity=new DTO();
//
//
//        for(int i=1;i<rowNum;i++){
//            Row row=sheet.getRow(i);
//            entity.setCityCode(row.getCell(0).getStringCellValue());
//            list.add(entity);
//        }
//
//        return list;
//
//    }
}
