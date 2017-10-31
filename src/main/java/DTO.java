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
    private String cityCode,citySidoName,citySggName,aptCode,aptName,disYear,
            disMonth,disDate,disDay,disQuantity,disQuantityRate,disCount,disCountRate;

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public void setCitySidoName(String citySidoName) {
        this.citySidoName = citySidoName;
    }

    public void setCitySggName(String citySggName) {
        this.citySggName = citySggName;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public void setDisYear(String disYear) {
        this.disYear = disYear;
    }

    public void setDisMonth(String disMonth) {
        this.disMonth = disMonth;
    }

    public void setDisDate(String disDate) {
        this.disDate = disDate;
    }

    public void setDisDay(String disDay) {
        this.disDay = disDay;
    }

    public void setDisQuantity(String disQuantity) {
        this.disQuantity = disQuantity;
    }

    public void setDisQuantityRate(String disQuantityRate) {
        this.disQuantityRate = disQuantityRate;
    }

    public void setDisCount(String disCount) {
        this.disCount = disCount;
    }

    public void setDisCountRate(String disCountRate) {
        this.disCountRate = disCountRate;
    }

    public String getCitySidoName() {
        return citySidoName;
    }

    public String getCitySggName() {
        return citySggName;
    }

    public String getAptCode() {
        return aptCode;
    }

    public String getAptName() {
        return aptName;
    }

    public String getDisYear() {
        return disYear;
    }

    public String getDisMonth() {
        return disMonth;
    }

    public String getDisDate() {
        return disDate;
    }

    public String getDisDay() {
        return disDay;
    }

    public String getDisQuantity() {
        return disQuantity;
    }

    public String getDisQuantityRate() {
        return disQuantityRate;
    }

    public String getDisCount() {
        return disCount;
    }

    public String getDisCountRate() {
        return disCountRate;
    }

    public String getCityCode() {
        return cityCode;
    }
}
