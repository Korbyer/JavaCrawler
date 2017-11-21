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
    public final static String PHARAM_URL="http://apis.data.go.kr/B552584/RfidFoodWasteService/"; /** 음식물 API URL */
    public final static String METHOD_GET_CITY="getCityList?ServiceKey="; /** 지자체목록 수집 method*/
    public final static String METHOD_GET_APT="getAptlist?ServiceKey="; /** 아파트목록 수집 method*/
    public final static String METHOD_GET_Food_Date="getCityAptDateList?ServiceKey="; /** 일일 아파트쓰레기목록 수집 method*/
    public final static String KEY="RtnuC6jy0Rc0DC281%2FTcmhwC7oWiLMxk%2BRbFGIDTYnZyQz0vYN3g5N8kvqaPmTVOyMSScRrCXFvpFn1NunWXlg%3D%3D"; /**API 키*/
    public final static String ROF="&type=xml&page=1&rowNum=50"; /**데이터갯수(너무많으면 시간오래걸림)*/
    public final static String ROF_BIG="&type=xml&page=1&rowNum=2000"; /**데이터갯수설정(아파트목록전용)*/
    public final static String CITY_CODE="&cityCode="; /**일일 음식물데이터 수집 설정 파라미터 - 1*/
    public final static String APT_CODE="&aptCode="; /**일일 음식물데이터 수집 설정 파라미터 - 2*/
    public final static String FOOD_YEAR="&disYear="; /**일일 음식물데이터 수집 설정 파라미터 - 3*/
    public final static String FOOD_MONTH="&disMonth="; /**일일 음식물데이터 수집 설정 파라미터 - 4*/
    public final static String SEPERATOR="\n"; /**작업중*/
    public final static Object[] HEADER_FOOD_REGION={"cityCode","citySidoName","citySggName"}; /**작업중*/
    public final static Object[] HEADER_FOOD_APT={"cityCode","aptCode","citySggName","aptName"}; /**작업중*/
    public final static Object[] HEADER_FOOD_DATE={"disYear","disMonth","disDate","disDay","cityCode","citySidoName","citySggName","aptCode","aptName","disQuantity","disQuantityRate","disCount","disCountRate"}; /**작업중*/
    public String getURLParam(String data);
    public void printList(ArrayList<DTO> list);
    public void apiParserSearch() throws Exception;
    public ArrayList<DTO> returnList(int year) throws Exception;


}
