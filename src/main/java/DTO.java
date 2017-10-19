import lombok.Getter;
import lombok.Setter;

/**
 * Created by admin on 2017. 9. 25..
 * 각 데이터들에 대해 오브젝트로 변환해주는 클래스 DTO (Data Transfer Object) 입니다.
 * API 에서 받아오는 정보들을 자바 내부에서 활용 할 수 있도록
 * 데이터 를 가져오고 변환시켜주는 메소드들을 정의해놓은 곳입니다.
 * 각 변수에 대해 get과 set 메소드 설정해놓았습니다.
 * 자세한건 구글에 "자바 Getter Setter" 혹은 "자바 롬복(lombok)" 에 대해 검색해보시기 바랍니다.
 */

public class DTO {
    private @Getter @Setter String cityCode,citySidoName,citySggName,aptCode,aptName,disYear,
            disMonth,disDate,disDay,disQuantity,disQuantityRate,disCount,disCountRate;



}
