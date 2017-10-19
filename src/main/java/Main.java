/**
 * Created by admin on 2017. 9. 25..
 */
/**조사하고자 하는 연도를 그냥 숫자 그래도 넣으시면 됩니다
 * 수정이 완료되면 Main 자바 클래스를 오른쪽 클릭해서 Run 시키시면 됩니다
 * 현재 발견된 오류사항 :
 * 콘솔창에서 HTTP ERROR 500 이 뜨는 경우는
 * 제대로 된 정보를 받아오지 못한 경우입니다
 * 이를 다시 확인하기 위해서는 해당 샘플 URL을 복사하고 인터넷으로 들어가봅니다
 * 만약 해당 사이트가 HTTP ERROR CODE 04 가 발생한다면
 * 해당 기관에 대하여 분제가 있는것이니 이건 저희도 어쩔 수 없습니다
 * 만약 다른 종류의 에러인 경우 제가 첨부한 에러코드 문서파일을 보고 확인하시고
 * 문의사항 있으시면 저에게 톡해주시면 됩니다
 * */
        /**샘플 주소 URL*/
        /**http://apis.data.go.kr/B552584/RfidFoodWasteService/getCityAptDateList?ServiceKey=RtnuC6jy0Rc0DC281%2FTcmhwC7oWiLMxk%2BRbFGIDTYnZyQz0vYN3g5N8kvqaPmTVOyMSScRrCXFvpFn1NunWXlg%3D%3D&type=xml&page=1&rowNum=1000&disYear=2015&disMonth=01&&cityCode=W02&aptCode=W02001*/
        /**2017_10_19 오전1시 기준 서버 정상 확인*/

        /**코드에 대한 간략한 설명*/

        /**
         * 1. 우선 JSon으로 시작되는 부분은 아직 작업해보지도 못했으니 신경쓰지않으셔도 됩니다
         * 2. 현재 저희가 가장 우선적으로 모아야 할 데이터들은 "자치별 & 아파트별 일일 배출내역" 입니다
         *      이는 API 명세표에 보시면 아시겠지만 getCityAptDateList로 시작하고 있습니다
         * 3. 해당 API주소에 필수적으로 넣어야 할 파라미터 값들은 연도(disYear),월(disMonth), 도시코드(cityCode), 아파트코드(aptCode) 총 다섯가지입니다
         *      이미 해당 파라미터들은 Xml_Parser_Food_Date 클래스에 제가 선언해놓았으니 따로 수정하지 않으셔도 됩니다
         * 4. 사용방법 :
         *       - 메인클래스안에 괄호 숫자를 수정시킨다(조사하고자 하는 연도)
         *       - Main클래스를 오른쪽 클릭해서 Run Main 을 선택해준다.
         *       - 기다린다(2015년 기준, 대략 1시간 정도 소요)*/

public class Main {
    public static void main(String[] args){
        //new Xml_Parser_Region();//지역코드파싱 성공
        //new Xml_Parser_Region_Apt();//아파트리스트파싱 성공
        new Xml_Parser_Food_Date(2015); /**반드시 수정*/



    }
}
