import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedInputStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by admin on 2017. 9. 26..
 */
public class Xml_Parser_Region implements Xml_Parser {


    /*
    * ROF끝에 citySidoName="서울" 을 추가하면 바로 서울에 관련된 정보들만 나열되지만
    * apiParserSearch()실행 중, 주어진 URL에 대하여 '한글' 인코딩 처리가 되지 않으므로
    * '서울' 이라는 단어자체가 인식이 되지 않아 오류가 발생한다!
    *
    *  이를 해결하기 위해서는
    * '서울' 에 해당되는 정보들만 수집하기 위해 내부 코드를 추가하여준다(ExcelWriter 클래스 중 Xml_Region 참조)
    *
    * */

        public Xml_Parser_Region(){
            try{
                System.out.println("Xml_Parser_Region is running Now...");
                apiParserSearch();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }

        public void apiParserSearch() throws Exception{

            URL url=new URL(getURLParam(ROF));

            XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xp=factory.newPullParser();
            BufferedInputStream bf=new BufferedInputStream(url.openStream());
            xp.setInput(bf,"utf-8");

            String tag=null;
            int eventType=xp.getEventType();

            ArrayList<DTO> list=new ArrayList<DTO>();

        /*각 기능마다 태그들을 달리 설정*/
            int disYear,disMonth,disDate,disDay,disQuantity,disQuantityRate,disCount,disCountRate;
            String cityCode=null,citySidoName=null,citySggName=null,aptCode=null,aptName=null;

            while(eventType!=XmlPullParser.END_DOCUMENT){
                if(eventType==XmlPullParser.START_TAG){
                    tag=xp.getName();

                }
                else if(eventType==XmlPullParser.TEXT){

                    if(tag.equals("cityCode")){
                        cityCode=xp.getText();
                    }
                    else if(tag.equals("citySidoName")){
                        citySidoName=xp.getText();
                    }
                    else if(tag.equals("citySggName")){
                        citySggName=xp.getText();
                    }


                }
                else if(eventType==XmlPullParser.END_TAG){
                    tag=xp.getName();
                    if(tag.equals("list")){
                        DTO entity=new DTO();

                        entity.setCityCode(cityCode);
                        entity.setCitySidoName(citySidoName);
                        entity.setCitySggName(citySggName);

                        list.add(entity);


                    }
                }
                eventType=xp.next();
            }
            //printList(list);
            new Excel_Writer_Xml_Region().Excel_Writer_Xml_Region(list);

        }

    public ArrayList<DTO> returnList(int year) throws Exception {
        return null;
    }

    public void printList(ArrayList<DTO> list) {
            for(DTO entity:list){
                System.out.println(entity);
            }
        }

        public String getURLParam(String data) {
            String url=PHARAM_URL+METHOD_GET_CITY   +KEY+ROF;
            if(data!=null){
                url=url+data;
            }
            return url;
        }

}
