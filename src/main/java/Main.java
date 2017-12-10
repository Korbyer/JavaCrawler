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
         *
         * 2. 현재 저희가 가장 우선적으로 모아야 할 데이터들은 "자치별 & 아파트별 일일 배출내역" 입니다
         *      이는 API 명세표에 보시면 아시겠지만 getCityAptDateList로 시작하고 있습니다
         *
         * 3. 해당 API주소에 필수적으로 넣어야 할 파라미터 값들은 연도(disYear),월(disMonth), 도시코드(cityCode), 아파트코드(aptCode) 총 다섯가지입니다
         *      이미 해당 파라미터들은 Xml_Parser_Food_Date 클래스에 제가 선언해놓았으니 따로 수정하지 않으셔도 됩니다
         *
         * 4. 사용방법 :
         *       (1) new Xml_Parser_Region() 을 실행, 음식물 지역 코드를 먼저 수집한다.
         *       (2) new Xml_Parser_Region_Apt() 을 실행, 앞서 수집한 지역코드를 매개로 각 지역별 아파트목록을 수집한다.
         *       (3) new Xml_Parser_PreRegion_Dong_Apt() 을 실행, 앞서 수집한 아파트목록을 매개로 각 아파트가 속한 법정동 항목을 추가한다.
         *       (4) new Xml_Parser_Holiday(연도) 을 실행, 연도를 입력하여 해당 연도에 속한 모든 공휴일을 수집한다
         *       (5) new Xml_Parser_Food_Date(연도) 를 실행, 연도를 입력하여 지금까지 수집했던 지역코드, 아파트목록,
         *          아파트소속 법정동, 공휴일 정보를 토대로 해당 연도에 발생했던 모든 음식물 배출 데이터를 수집한다. Food_Date_연도.csv 파일이 생성될것이다.
         *       (6) new Xml_Parser_Fix_Food_Date(연도) 를 실행. 앞서 수집했던 csv파일 내부에 serverError 항목을 다시한번 갱신시켜주는 과정이다.
         *          Console 창에 HTTP 500 이 생성이 되지 않을때까지 계속 반복해서 실행시켜준다.
         *       (7) 만약 위 (6) 과정을 진행하다가 HTTP 500 으로 시작하는 과정이 콘솔창에 생겨나지 않았다면
         *          데이터수집 완료. 해당 csv 데이터(최종결과 : Food_Date_연도.csv) 를 토대로 데이터분석을 시작하면 된다.
         *
         * 5. 주의사항 :
         *       (1) 위 4번째 사용방법의 과정수행 중, 파일이 생성되고 저장되는 위치는 사용자가 각각 일일이 설정을 해주어야 한다.
         *       (2) 공통적으로 사용하는 모든 인자는 Xml_Parser 인터페이스에서 정의되어있다.(API 키, Parameter 정의 등등)
         *          만약 해당 항목들을 재정의해야된다면 즉시 수정 요망(특히, API키는 수정 요망)
         *       (3) 중간중간 에러 발생시, 하단의 메일주소로 알려주시면 감사하겠습니다.
         *
         *
         *
         * 감사합니다!
         *    [데이터수집모듈]
         *    Food_Date_Alpha_v0.02
         *    2017.11.25  -  v0.01
         *    2017.12.09  -  v0.02 : aptName 부분에서 ' , '  포함된 단어들 다른 단어로 대체(sql 문법수정중, csv 포맷 인식 과정에서 문제발생)
         *    박효열
         *    hypark5540@naver.com
         *
         *    https://github.com/Korbyer/JavaCrawler
         *
         *       */


public class Main {
    public static void main(String[] args){
        //new Xml_Parser_Region();//지역코드파싱 성공 -1-
        //new Xml_Parser_Region_Apt();//아파트리스트파싱 성공 -2-
        //new Xml_Parser_PreRegion_Dong_Apt();//법정동 수집 모두성공 -3-
        //new Xml_Parser_Holiday(2016);//2016 모든 공휴일 파싱 성공
        //new Xml_Parser_Food_Date(2017); /**반드시 수정, 성공*//**-4-*/
        new Xml_Parser_Fix_Food_Date(2017);/**CSV파일 재생성 성공*/


        /**아래 두 가지 실행은 기상청 API를 토대로 만든 법정동 결과물을 만들어내는것이다.
         * 당장 지금 프로젝트 과정에서는 필요없는 기능이므로 실행을 배제한다(차후 추가기능 도입시 활용 고려)*/
        //new Excel_Writer_PreRegion_Remake();//성공, 법정동 xls파일 리메이크
        //new Json_Parser_PreRegion(); /**동 목록 성공, 기상청 동주소(다름) 필요없음 현재는*/




    }
}
