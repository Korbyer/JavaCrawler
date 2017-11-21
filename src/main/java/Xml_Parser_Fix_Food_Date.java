import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Xml_Parser_Fix_Food_Date implements Xml_Parser {

    public Xml_Parser_Fix_Food_Date(int year){
        try{
            System.out.println("Xml_Parser_Food_Date is running Now...");
            apiParserSearch(year);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public ArrayList<DTO> returnList(int year) throws Exception {
        ArrayList<DTO> list=new ArrayList<DTO>();

//        BufferedReader buffReader=new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Date_"+String.valueOf(year)+"_errSample.csv")),"EUC-KR"));
        CSVReader reader=new CSVReader(new BufferedReader(new InputStreamReader(new FileInputStream(new File("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Date_"+String.valueOf(year)+"_errSample.csv")),"EUC-KR")),',','"');

        List<String[]> csvBody=reader.readAll();
        String input="",errorCheck="",ServerError="ServerError",NoData="NoData";
        int lineCount=0, TIME_OUT_VALUE=5000,numOfEntity=0,i=1;
        HttpURLConnection conn=null;
        URL url=null;

        String disYear = null, disMonth = null, disDate = null, disDay = null, disQuantity = null, disQuantityRate = null,
                disCount = null, disCountRate = null, cityCode = null, citySidoName = null, citySggName = null,
                aptCode = null, aptName = null, errMsg=null, returnAuthMsg=null, returnReasonCode=null, count=null;
//        for(input=buffReader.readLine();input!=null;i++){
        for(i=1;i<csvBody.size();i++){
            errorCheck = csvBody.get(i)[2];
            if(errorCheck.equals(ServerError)){
                try{
                    url = new URL(getURLParam(csvBody.get(i)[4],csvBody.get(i)[7],csvBody.get(i)[0],csvBody.get(i)[1]));
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
                                aptName=xp.getText();
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
                            else if (tag.equals("returnReasonCode")) {
                                returnReasonCode = xp.getText();
                            }
                            else if(tag.equals("count")){
                                count=xp.getText();
                            }
                        } else if (eventType == XmlPullParser.END_TAG) {
                            tag = xp.getName();
                            if (tag.equals("list")) {
                                DTO entity = new DTO();
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

                                list.add(entity);
                                numOfEntity+=1;


                            }
                            else if(tag.equals("returnReasonCode")){
                                DTO entity = new DTO();

                                entity.setDisYear(String.valueOf(year));
                                entity.setDisMonth(csvBody.get(i)[1]);
                                entity.setDisDate(ServerError);
                                entity.setDisDay(ServerError);
                                entity.setCityCode(csvBody.get(i)[5]);
                                entity.setCitySidoName(ServerError);
                                entity.setCitySggName(csvBody.get(i)[6]);
                                entity.setAptCode(csvBody.get(i)[7]);
                                entity.setAptName(csvBody.get(i)[8]);
                                entity.setDisQuantity(ServerError);
                                entity.setDisQuantityRate(ServerError);
                                entity.setDisCount(ServerError);
                                entity.setDisCountRate(ServerError);

                                list.add(entity);
                                numOfEntity += 1;

                            }
                            else if(tag.equals("count")){

                                if(Integer.parseInt(count)==0) {
                                    DTO entity = new DTO();

                                    entity.setDisYear(String.valueOf(year));
                                    entity.setDisMonth(csvBody.get(i)[1]);
                                    entity.setDisDate(NoData);
                                    entity.setDisDay(NoData);
                                    entity.setCityCode(csvBody.get(i)[4]);
                                    entity.setCitySidoName(NoData);
                                    entity.setCitySggName(csvBody.get(i)[6]);
                                    entity.setAptCode(csvBody.get(i)[7]);
                                    entity.setAptName(csvBody.get(i)[8]);
                                    entity.setDisQuantity("0");
                                    entity.setDisQuantityRate("0");
                                    entity.setDisCount("0");
                                    entity.setDisCountRate("0");

                                    list.add(entity);
                                    numOfEntity += 1;
                                }
                            }
                        }
                        eventType = xp.next();
                    }



                }catch (Exception e){
                    System.out.println(i+"번째 "+csvBody.get(i)[1]+"월 데이터 오류발생, HTTP코드 = "+e.getMessage());

                    DTO entity = new DTO();

                    entity.setDisYear(String.valueOf(year));
                    entity.setDisMonth(csvBody.get(i)[1]);
                    entity.setDisDate(ServerError);
                    entity.setDisDay(ServerError);
                    entity.setCityCode(csvBody.get(i)[5]);
                    entity.setCitySidoName(ServerError);
                    entity.setCitySggName(csvBody.get(i)[6]);
                    entity.setAptCode(csvBody.get(i)[7]);
                    entity.setAptName(csvBody.get(i)[8]);
                    entity.setDisQuantity(ServerError);
                    entity.setDisQuantityRate(ServerError);
                    entity.setDisCount(ServerError);
                    entity.setDisCountRate(ServerError);

                    list.add(entity);
                    numOfEntity += 1;
                }
                finally {
                    conn.disconnect();
                    reader.close();

                }


            }
            else{
                DTO entity=new DTO();
                entity.setDisYear(csvBody.get(i)[0]);
                entity.setDisMonth(csvBody.get(i)[1]);
                entity.setDisDate(csvBody.get(i)[2]);
                entity.setDisDay(csvBody.get(i)[3]);
                entity.setCityCode(csvBody.get(i)[4]);
                entity.setCitySidoName(csvBody.get(i)[5]);
                entity.setCitySggName(csvBody.get(i)[6]);
                entity.setAptCode(csvBody.get(i)[7]);
                entity.setAptName(csvBody.get(i)[8]);
                entity.setDisQuantity(csvBody.get(i)[9]);
                entity.setDisQuantityRate(csvBody.get(i)[10]);
                entity.setDisCount(csvBody.get(i)[11]);
                entity.setDisCountRate(csvBody.get(i)[12]);
                list.add(entity);
                numOfEntity+=1;

            }

        }

        return list;
    }

    public void apiParserSearch(int year) throws Exception{
        new Csv_Writer_Xml_Food_Date().Csv_Writer_Xml_Food_Date(returnList(year),year);
    }


    public String getURLParam(String cityCode,String aptCode,String year,String month){/**4, 7, 1*/
        String url=PHARAM_URL+METHOD_GET_Food_Date+KEY+ROF+FOOD_YEAR+year+FOOD_MONTH;
        if(Integer.parseInt(month)<10 && cityCode!=null && aptCode!=null){
            url=url+"0"+month+CITY_CODE+cityCode+APT_CODE+aptCode;
        }
        if(Integer.parseInt(month)>=10 && cityCode!=null && aptCode!=null){
            url=url+month+CITY_CODE+cityCode+APT_CODE+aptCode;
        }
        return url;
    }
    public String getURLParam(String data) {
        return null;
    }

    public void printList(ArrayList<DTO> list) {

    }

    public void apiParserSearch() throws Exception {

    }


}
