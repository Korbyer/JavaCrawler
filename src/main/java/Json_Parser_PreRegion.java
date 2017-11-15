import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList; /**IMPORT 할때 주의해서 할것*/

/**
 * Created by admin on 2017. 11. 6..
 */
public class Json_Parser_PreRegion implements Json_Parser {
    public Json_Parser_PreRegion(){
        try{
            apiParserSearch();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void apiParserSearch() throws Exception{

        JSONParser jsonparser = new JSONParser();
        JSONArray array = (JSONArray)jsonparser.parse(readURLParam());/**해당 사이트를 해석한 결과가 바로 JSONARRAY 형식 그래서 바로 ARRAY로 적용*/
        for(int i=0;i<array.size();i++){
            JSONObject entity=(JSONObject)array.get(i);

        }

//        JSONObject jsonObject=(JSONObject)jsonparser.parse(getURLParam());
//        ArrayList<JSONObject> list = new ArrayList<JSONObject>();
    }

    public String readURLParam() throws Exception{
        BufferedInputStream reader=null;
        StringBuffer buffer=null;
        URL url=null;

        try{
            url=new URL(getURLParam());
            reader=new BufferedInputStream(url.openStream());
            buffer=new StringBuffer();
            int i;
            byte[] b=new byte[4096];
            while((i=reader.read(b))!=-1){
                buffer.append(new String(b,0,i));
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        finally {
            if(reader!=null)
                reader.close();
        }

        return buffer.toString();
    }

    public String getURLParam(){
        String url=PRE_BASIC_URL+PRE_WHOLE+"."+PRE_ROF;
        return url;
    }

}
