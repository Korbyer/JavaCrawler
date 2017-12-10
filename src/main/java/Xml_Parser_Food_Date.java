import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by admin on 2017. 10. 18..
 * 반드시 필독!
 *
 * 각자의 컴퓨터는 서로 이름도 다르고 경로도 다를 수 있기 때문에
 * 해당 프로젝트를 받은 후, 프로젝트 하단 data폴더에 엑셀 파일들을 저장해놓으시길 바랍니다(혹은 개인 취향으로 다른곳에 저장해도 상관없음)
 * 그리고 반드시, 해당 엑셀 데이터가 저장되어있는 경로와 파일이름을 복사해서 <1> 부분에 넣으시기 바랍니다.
 * <2> 부분 (Excel_Writer_Xml_Food_Date 클래스 참조) 은 해당 엑셀 결과를 어디에 저장할 것인지 따로 개인적으로 경로를 지정해주시면 됩니다.
 */
public class Xml_Parser_Food_Date implements Xml_Parser{

    /**
     * 클래스 기본 생성자입니다.
     * 기본생성자 내부에 apiParserSearch() 메소드를 실행합니다.*/
    public Xml_Parser_Food_Date(int year){
        try{
            System.out.println("Xml_Parser_Food_Date is running Now...");
            apiParserSearch(year);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * 크롤링의 핵심 메소드입니다.
     * 1. 우선 참조할 data를 불러옵니다(엑셀파일 불러오기)
     * 2. 참조할 data에서 cityCode & aptCode 를 불러옵니다.
     * 3. 각 도시 및 아파트코드별로 API 의 주소(URL)을 불러옵니다
     * 4. 불러온 주소가 유효한지 검사합니다 (여기서 오류 자주발생, 주로 상대 서버문제)
     * 5. 유효하면 데이터들이 명시되는데 이들을 각 변수에 맞게 파싱합니다.
     * 6. 파싱된 데이터들을 정제합니다.
     * 7. 정제된 데이터들은 ArrayList 형식으로 만들어줍니다. 타입은 DTO 입니다.
     * 8. 정제된 데이터(list 변수) 를 엑셀 메소드의 변수로 넣어주어 실행시킵니다.*/

    public ArrayList<DTO> returnList(int year) throws Exception{
        FileInputStream fis=new FileInputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Region_Apt_Dong.xls");/** Food_Region_Apt 엑셀파일 저장위치 불러오기 */
        FileInputStream hol_fis=new FileInputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Region_Holiday_"+year+".xls");

        Workbook wbk=new HSSFWorkbook(fis);
        Workbook hol_wbk=new HSSFWorkbook(hol_fis);

        Sheet sheet = wbk.getSheetAt(0);
        Sheet hol_sheet=hol_wbk.getSheetAt(0);

        int rowNum=sheet.getPhysicalNumberOfRows();
        int hol_rowNum=hol_sheet.getPhysicalNumberOfRows();

        ArrayList<DTO> list=new ArrayList<DTO>();
        String disYear = null, disMonth = null, disDate = null, disDay = null, disQuantity = null, disQuantityRate = null,
                disCount = null, disCountRate = null, cityCode = null, citySidoName = null, citySggName = null,
                aptCode = null, aptName = null, errMsg=null, returnAuthMsg=null, returnReasonCode=null, count=null,dongName=null,dateKind=null,dateName=null,isHoliday="0",locdate=null,
                checkHoliday=null,remakeDate=null,remakeMonth=null,checkDisDay=null,dumi=null;

        int numOfEntity=0;
        int sheetNum=0;
        URL url=null;
        HttpURLConnection conn=null;
        String ServerError = "ServerError";
        String NoData="NoData";
        int TIME_OUT_VALUE=5000;


        for(int i=1;i<rowNum;i++) {//rowNum
            Row row = sheet.getRow(i);
            for (int j = 1; j < 13; j++) {//
                try
                {
                    url = new URL(getURLParam(row.getCell(0).getStringCellValue(), row.getCell(1).getStringCellValue(), String.valueOf(year), String.valueOf(j)));


                    conn = (HttpURLConnection) url.openConnection();
                    conn.setConnectTimeout(TIME_OUT_VALUE);
                    conn.setReadTimeout(TIME_OUT_VALUE);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-type", "application/json");
                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    factory.setNamespaceAware(true);
                    XmlPullParser xp = factory.newPullParser();
                    BufferedInputStream bf = new BufferedInputStream(url.openStream());
                    xp.setInput(bf, "utf-8");



                    String tag = null;
                    int eventType = xp.getEventType();



                 /*각 기능마다 태그들을 달리 설정*/




                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        if (eventType == XmlPullParser.START_TAG) {
                            tag = xp.getName();

                        } else if (eventType == XmlPullParser.TEXT) {

                            if (tag.equals("disYear")) {
                                disYear = xp.getText();
                            }
                            else if (tag.equals("disMonth")) {
                                disMonth=xp.getText();
                            }
                            else if (tag.equals("disDate")) {
                                disDate=xp.getText();
                            }
                            else if (tag.equals("disDay")) {
                                disDay=xp.getText();
                            }
                            else if (tag.equals("cityCode")) {
                                cityCode=xp.getText();
                            }
                            else if (tag.equals("citySidoName")) {
                                citySidoName=xp.getText();
                            }
                            else if (tag.equals("citySggName")) {
                                citySggName=xp.getText();
                            }
                            else if (tag.equals("aptCode")) {
                                aptCode=xp.getText();
                            }
                            else if (tag.equals("aptName")) {
                                dumi=xp.getText();
                                aptName=dumi.replace(',','.');

                            }
                            else if (tag.equals("disQuantity")) {
                                disQuantity=xp.getText();
                            }
                            else if (tag.equals("disQuantityRate")) {
                                disQuantityRate=xp.getText();
                            }
                            else if (tag.equals("disCount")) {
                                disCount=xp.getText();
                            }
                            else if (tag.equals("disCountRate")) {
                                disCountRate=xp.getText();
                            }
                            else if (tag.equals("errMsg")){
                                errMsg=xp.getText();
                            }
                            else if (tag.equals("returnAuthMsg")){
                                returnAuthMsg=xp.getText();
                            }
                            else if (tag.equals("returnReasonCode")){
                                returnReasonCode=xp.getText();
                            }
                            else if (tag.equals("count")){
                                count=xp.getText();
                            }



                        } else if (eventType == XmlPullParser.END_TAG) {
                            tag = xp.getName();
                            if (tag.equals("list")) {
                                DTO entity = new DTO();
                                isHoliday="0";
                                Loop1: for(int a=1;a<hol_rowNum;a++){
                                    Row hol_row=hol_sheet.getRow(a);
                                    checkHoliday=hol_row.getCell(3).getStringCellValue();
                                    String remakeFormat=disYear+disMonth+disDate;
//                                    if(Integer.parseInt(disDate)<10){
//                                        remakeDate="0"+disDate;
//                                    }
//                                    else if(Integer.parseInt(disDate)>=10){
//                                        remakeDate=disDate;
//                                    }
//
//                                    if(Integer.parseInt(disMonth)<10){
//                                        remakeMonth="0"+disMonth;
//                                    }
//                                    else if(Integer.parseInt(disMonth)>=10){
//                                        remakeMonth=disMonth;
//                                    }

                                    if(remakeFormat.equals(checkHoliday) || Integer.parseInt(disDay)==6 || Integer.parseInt(disDay)==7){
                                        isHoliday="1";
                                        break Loop1;
                                    }



                                }
                                entity.setDisYear(disYear);
                                entity.setDisMonth(disMonth);
                                entity.setDisDate(disDate);
                                entity.setDisDay(disDay);
                                entity.setCityCode(cityCode);
                                entity.setCitySidoName(citySidoName);
                                entity.setCitySggName(citySggName);
                                entity.setAptCode(aptCode);
                                entity.setAptName(aptName);
                                entity.setDisQuantity(disQuantity);
                                entity.setDisQuantityRate(disQuantityRate);
                                entity.setDisCount(disCount);
                                entity.setDisCountRate(disCountRate);
                                entity.setDongName(row.getCell(4).getStringCellValue());
                                entity.setIsHoliday(isHoliday);

                                list.add(entity);
                                numOfEntity+=1;




                            }
                            else if(tag.equals("returnReasonCode")){
                                DTO entity = new DTO();

                                entity.setDisYear(String.valueOf(year));
                                entity.setDisMonth(String.valueOf(j));
                                entity.setDisDate(ServerError);
                                entity.setDisDay(ServerError);
                                entity.setCityCode(row.getCell(0).getStringCellValue());
                                entity.setCitySidoName(ServerError);
                                entity.setCitySggName(row.getCell(2).getStringCellValue());
                                entity.setAptCode(row.getCell(1).getStringCellValue());
                                entity.setAptName(row.getCell(3).getStringCellValue().replace(',','.'));
                                entity.setDisQuantity(ServerError);
                                entity.setDisQuantityRate(ServerError);
                                entity.setDisCount(ServerError);
                                entity.setDisCountRate(ServerError);
                                entity.setDongName(row.getCell(4).getStringCellValue());
                                entity.setIsHoliday(ServerError);

                                list.add(entity);
                                numOfEntity += 1;

                            }
                            else if(tag.equals("count")){

                                if(Integer.parseInt(count)==0) {
                                    DTO entity = new DTO();
//                                    Calendar c=Calendar.getInstance();
//                                    c.set(Integer.parseInt(String.valueOf(year)),j,1);
//                                    int max=c.getActualMaximum(Calendar.DAY_OF_MONTH);
//                                    for(int x=1;x<max;x++){
//                                        if (x < 10) {
//                                            remakeDate = "0" + String.valueOf(x);
//                                        } else if (j < 10) {
//                                            remakeMonth = "0" + String.valueOf(j);
//                                        } else if (x >= 10) {
//                                            remakeDate = String.valueOf(x);
//                                        } else if (j >= 10) {
//                                            remakeMonth = String.valueOf(j);
//                                        }
//                                        String dateType=String.valueOf(year)+"-"+remakeMonth+"-"+remakeDate;
//                                        SimpleDateFormat dateFormat=new SimpleDateFormat(dateType);
//                                        Date nDate=dateFormat.parse(String.valueOf(remakeDate));
//                                        c.setTime(nDate);
//                                        int dayNum=c.get(Calendar.DAY_OF_WEEK);
//                                        checkDisDay="";
//
//                                        switch (dayNum){
//                                            case 1:
//                                                checkDisDay="1";
//                                                break;
//                                            case 2:
//                                                checkDisDay="2";
//                                                break;
//                                            case 3:
//                                                checkDisDay="3";
//                                                break;
//                                            case 4:
//                                                checkDisDay="4";
//                                                break;
//                                            case 5:
//                                                checkDisDay="5";
//                                                break;
//                                            case 6:
//                                                checkDisDay="6";
//                                                break;
//                                            case 7:
//                                                checkDisDay="7";
//                                                break;
//                                        }
//
//                                        for(int a=1;a<hol_rowNum;a++) {
//                                            Row hol_row = hol_sheet.getRow(a);
//                                            checkHoliday = hol_row.getCell(3).getStringCellValue();
//
//                                            if ((String.valueOf(year) + remakeMonth + remakeDate).equals(checkHoliday)) {
//                                                isHoliday = "1";
//                                            }
//                                            else if (!checkHoliday.equals(String.valueOf(year) + remakeMonth + remakeDate)) {
//                                                isHoliday = "0";
//                                            }
//                                            else if(checkDisDay.equals("6") || checkDisDay.equals("7")){
//                                                isHoliday="1";
//                                            }
//
//                                        }
                                        entity.setDisYear(String.valueOf(year));
                                        entity.setDisMonth(String.valueOf(j));
                                        entity.setDisDate(String.valueOf(NoData));
                                        entity.setDisDay(NoData);
                                        entity.setCityCode(row.getCell(0).getStringCellValue());
                                        entity.setCitySidoName(NoData);
                                        entity.setCitySggName(row.getCell(2).getStringCellValue());
                                        entity.setAptCode(row.getCell(1).getStringCellValue());
                                        entity.setAptName(row.getCell(3).getStringCellValue().replace(',','.'));
                                        entity.setDisQuantity("0");
                                        entity.setDisQuantityRate("0");
                                        entity.setDisCount("0");
                                        entity.setDisCountRate("0");
                                        entity.setDongName(row.getCell(4).getStringCellValue());
                                        entity.setIsHoliday(NoData);

                                        list.add(entity);
                                        numOfEntity += 1;

                                    }


                                }
                            }

                        eventType = xp.next();
                    }


                } catch (Exception e) {
                    System.out.println(i+"번째 "+j+"월 데이터 오류발생, HTTP코드 = "+e.getMessage());


                    DTO entity = new DTO();

                    entity.setDisYear(String.valueOf(year));
                    entity.setDisMonth(String.valueOf(j));
                    entity.setDisDate(ServerError);
                    entity.setDisDay(ServerError);
                    entity.setCityCode(row.getCell(0).getStringCellValue());
                    entity.setCitySidoName(ServerError);
                    entity.setCitySggName(row.getCell(2).getStringCellValue());
                    entity.setAptCode(row.getCell(1).getStringCellValue());
                    entity.setAptName(row.getCell(3).getStringCellValue().replace(',','.'));
                    entity.setDisQuantity(ServerError);
                    entity.setDisQuantityRate(ServerError);
                    entity.setDisCount(ServerError);
                    entity.setDisCountRate(ServerError);
                    entity.setDongName(row.getCell(4).getStringCellValue());
                    entity.setIsHoliday(ServerError);

                    list.add(entity);
                    numOfEntity += 1;
                } finally {

                    conn.disconnect();
                    fis.close();

                }
            }
        }
        return list;
    }
    public void apiParserSearch(int year) throws Exception {
        new Csv_Writer_Xml_Food_Date().Csv_Writer_Xml_Food_Date(returnList(year),year);

    }


    public String getURLParam(String cityCode,String aptCode,String year,String month) {
        String url=PHARAM_URL+METHOD_GET_Food_Date+KEY+ROF+FOOD_YEAR+year+FOOD_MONTH;
        if(Integer.parseInt(month)<10 && cityCode!=null && aptCode!=null){
            url=url+"0"+month+CITY_CODE+cityCode+APT_CODE+aptCode;
        }
        if(Integer.parseInt(month)>=10 && cityCode!=null && aptCode!=null){
            url=url+month+CITY_CODE+cityCode+APT_CODE+aptCode;
        }
        return url;
    }


    public void printList(ArrayList<DTO> list) {

    }
    /*사용하지 않는 메소드입니다.*/
    public void apiParserSearch() throws Exception {

    }

    public String getURLParam(String data){
        return null;
    }



    public int returnRowNum(){
        return 0;
    }
    public int returnRowNum(int year){
        return 0;
    }
}
