import java.util.ArrayList;

/**
 * Created by admin on 2017. 9. 25..
 */
public interface Json_Parser {
    public final static String PHARAM_URL="http://apis.data.go.kr/B552584/RfidFoodWasteService/getCityList?ServiceKey=";
    public final static String KEY="RtnuC6jy0Rc0DC281%2FTcmhwC7oWiLMxk%2BRbFGIDTYnZyQz0vYN3g5N8kvqaPmTVOyMSScRrCXFvpFn1NunWXlg%3D%3D";
    public final static String PRE_BASIC_URL="http://www.kma.go.kr/DFSROOT/POINT/DATA/";
    public final static String PRE_WHOLE="top";
    public final static String PRE_REGION="mdl";
    public final static String PRE_REGION_DONG="leaf";
    public final static String PRE_ROF="json.txt";
    public final static String ROF="&type=json&page=1&rowNum=10000&citySidoName=서울";
/**http://www.kma.go.kr/DFSROOT/POINT/DATA/mdl.11.json.txt*/
    public void apiParserSearch() throws Exception;
    public String readURLParam(String kind) throws Exception;
    public String getURLParam(String kind);
    public ArrayList<DTO> returnList() throws Exception;
}
