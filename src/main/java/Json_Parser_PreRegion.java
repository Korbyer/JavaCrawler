import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;/**IMPORT 할때 주의해서 할것*/

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
        JSONObject jsonObject=(JSONObject)jsonparser.parse(getURLParam());
    }

    public String getURLParam() {

        String url=PRE_BASIC_URL+PRE_WHOLE+PRE_ROF;
        return url;
    }
}
