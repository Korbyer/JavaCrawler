import java.util.ArrayList;

/**
 * Created by admin on 2017. 9. 23..
 * Xml 내용을 파싱하는 파일
 */

/**
 * API 크롤링에 사용되는
 * 주소 변수들을 모아놓은 곳입니다
 * 주소 변수들은 모두 상수로 선언해놓았습니다
 * */

public interface Xml_Parser {
    public final static String PHARAM_URL="http://apis.data.go.kr/B552584/RfidFoodWasteService/";
    public final static String METHOD_GET_CITY="getCityList?ServiceKey=";
    public final static String METHOD_GET_APT="getAptlist?ServiceKey=";
    public final static String METHOD_GET_Food_Date="getCityAptDateList?ServiceKey=";
    public final static String KEY="RtnuC6jy0Rc0DC281%2FTcmhwC7oWiLMxk%2BRbFGIDTYnZyQz0vYN3g5N8kvqaPmTVOyMSScRrCXFvpFn1NunWXlg%3D%3D";
    public final static String ROF="&type=xml&page=1&rowNum=2000";
    public final static String ROF_BIG="&type=xml&page=1&rowNum=500";
    public final static String CITY_CODE="&cityCode=";
    public final static String APT_CODE="&aptCode=";
    public final static String FOOD_YEAR="&disYear=";
    public final static String FOOD_MONTH="&disMonth=";
    public String getURLParam(String data);
    public void printList(ArrayList<DTO> list);
    public void apiParserSearch() throws Exception;


}
