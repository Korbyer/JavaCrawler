import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Xml_Parser_Holiday implements Xml_Parser {
    public Xml_Parser_Holiday(int year){
        try{
            apiParserSearch(year);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public String getURLParam(String data) {
        return null;
    }

    public void printList(ArrayList<DTO> list) {

    }
    public void apiParserSearch(int year) throws Exception{
        new Excel_Writer_Xml_Holiday().Excel_Writer_Xml_Holiday(returnList(year),year);

    }

    public void apiParserSearch() throws Exception {

    }

    public ArrayList<DTO> returnList(int year) throws Exception {
        URL url=null;
        HttpURLConnection conn=null,nation_conn=null,public_conn=null;
        String ServerError = "ServerError";
        String NoData="NoData";
        ArrayList<DTO> list=new ArrayList<DTO>();

        int TIME_OUT_VALUE=5000;

        for(int j=1;j<13;j++) {
            url = new URL(getURLParam(String.valueOf(year), String.valueOf(j), "nation"));
            System.out.println(String.valueOf(year)+"년 "+String.valueOf(j)+"월 - 국가절 처리중..");

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


            int eventType = xp.getEventType();
            String tag=null,isHoliday=null,locdate=null,dateKind=null,dateName=null,method="nation";

            while (eventType != XmlPullParser.END_DOCUMENT) {
                if (eventType == XmlPullParser.START_TAG) {
                    tag = xp.getName();
                } else if (eventType == XmlPullParser.TEXT) {
                    if (tag.equals("isHoliday")) {
                        isHoliday =xp.getText();
                    } else if (tag.equals("locdate")) {
                        locdate = xp.getText();
                    }
                    else if(tag.equals("dateKind")){
                        dateKind=xp.getText();

                    }
                    else if(tag.equals("dateName")){
                        dateName=xp.getText();
                    }
                } else if (eventType == XmlPullParser.END_TAG) {
                    tag = xp.getName();
                    if (tag.equals("item")) {
                        DTO entity=new DTO();
                        if(isHoliday.equals("Y")){
                            isHoliday="1";
                        }
                        else{
                            isHoliday="0";
                        }
                        entity.setDateKind(dateKind);
                        entity.setDateName(dateName);
                        entity.setIsHoliday(isHoliday);
                        entity.setLocdate(locdate);
                        list.add(entity);
                    }
                    else if(tag.equals("items") && method.equals("nation")){
                        System.out.println(String.valueOf(year)+"년 "+String.valueOf(j)+"월 - 국가절 처리완료");
                        method="public";
                        url=new URL(getURLParam(String.valueOf(year),String.valueOf(j),"public"));
                        bf=new BufferedInputStream(url.openStream());
                        xp.setInput(bf,"utf-8");
                        System.out.println(String.valueOf(year)+"년 "+String.valueOf(j)+"월 - 공휴일 진행중..");

                    }
                    else if(tag.equals("items") && method.equals("public")){
                        method="nation";
                        System.out.println(String.valueOf(year)+"년 "+String.valueOf(j)+"월 - 공휴일 처리완료");
                        break;
                    }

                }

                eventType = xp.next();
            }

        }
        return list;
    }
    public String getURLParam(String year,String month,String method){
        String url=PHARAM_URL_HOLIDAY;
        if(method.equals("nation")){
            if(Integer.parseInt(month)<10){
                url=url+METHOD_HOLIDAY_NATION+ROF_HOL_YEAR+year+ROF_HOL_MONTH+"0"+month+PRE_KEY+KEY;
            }
            else if(Integer.parseInt(month)>=10){
                url=url+METHOD_HOLIDAY_NATION+ROF_HOL_YEAR+year+ROF_HOL_MONTH+month+PRE_KEY+KEY;
            }
        }
        else if(method.equals("public")){
            if(Integer.parseInt(month)<10){
                url=url+METHOD_HOLIDAY_PUBLIC+ROF_HOL_YEAR+year+ROF_HOL_MONTH+"0"+month+PRE_KEY+KEY;
            }
            else if(Integer.parseInt(month)>=10){
                url=url+METHOD_HOLIDAY_PUBLIC+ROF_HOL_YEAR+year+ROF_HOL_MONTH+month+PRE_KEY+KEY;
            }
        }
        return url;
    }
    public ArrayList<DTO> returnList() throws Exception{
        return null;
    }
}
