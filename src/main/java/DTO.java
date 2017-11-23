import lombok.Getter;
import lombok.Setter;

/**
 * Created by admin on 2017. 9. 25..
 * 각 데이터들에 대해 오브젝트로 변환해주는 클래스 DTO (Data Transfer Object) 입니다.
 * API 에서 받아오는 정보들을 자바 내부에서 활용 할 수 있도록
 * 데이터 를 가져오고 변환시켜주는 메소드들을 정의해놓은 곳입니다.
 * 각 변수에 대해 get과 set 메소드 설정해놓았습니다.
 * 자세한건 구글에 "자바 Getter Setter" 혹은 "자바 롬복(lombok)" 에 대해 검색해보시기 바랍니다.
 * 현재 윈도우 및 맥 사이에서 lombok 의 호환성이 쌍방향으로 호환이 제대로 이루어지지 않아, Alt+Enter 단축키로
 * Getter & Setter 설정을 하였습니다.
 */

public class DTO {
    private String cityCode,citySidoName,citySggName,aptCode,aptName,disYear,
            disMonth,disDate,disDay,disQuantity,disQuantityRate,disCount,disCountRate,
            cityNumber,regionNumber,dongNumber,cityName,regionName,dongName,
            kaptCode,kaptName,kaptAddr,codeSaleNm,codeHeatNm,kaptTarea,kaptDongCnt,kaptdaCnt,kaptBcompany,kaptAcompany,
            kapttel,kaptFax,kaptUrl,codeAptNm,doroJuso,hoCnt,codeMgrNm;

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getCitySidoName() {
        return citySidoName;
    }

    public void setCitySidoName(String citySidoName) {
        this.citySidoName = citySidoName;
    }

    public String getCitySggName() {
        return citySggName;
    }

    public void setCitySggName(String citySggName) {
        this.citySggName = citySggName;
    }

    public String getAptCode() {
        return aptCode;
    }

    public void setAptCode(String aptCode) {
        this.aptCode = aptCode;
    }

    public String getAptName() {
        return aptName;
    }

    public void setAptName(String aptName) {
        this.aptName = aptName;
    }

    public String getDisYear() {
        return disYear;
    }

    public void setDisYear(String disYear) {
        this.disYear = disYear;
    }

    public String getDisMonth() {
        return disMonth;
    }

    public void setDisMonth(String disMonth) {
        this.disMonth = disMonth;
    }

    public String getDisDate() {
        return disDate;
    }

    public void setDisDate(String disDate) {
        this.disDate = disDate;
    }

    public String getDisDay() {
        return disDay;
    }

    public void setDisDay(String disDay) {
        this.disDay = disDay;
    }

    public String getDisQuantity() {
        return disQuantity;
    }

    public void setDisQuantity(String disQuantity) {
        this.disQuantity = disQuantity;
    }

    public String getDisQuantityRate() {
        return disQuantityRate;
    }

    public void setDisQuantityRate(String disQuantityRate) {
        this.disQuantityRate = disQuantityRate;
    }

    public String getDisCount() {
        return disCount;
    }

    public void setDisCount(String disCount) {
        this.disCount = disCount;
    }

    public String getDisCountRate() {
        return disCountRate;
    }

    public void setDisCountRate(String disCountRate) {
        this.disCountRate = disCountRate;
    }

    public String getCityNumber() {
        return cityNumber;
    }

    public void setCityNumber(String cityNumber) {
        this.cityNumber = cityNumber;
    }

    public String getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(String regionNumber) {
        this.regionNumber = regionNumber;
    }

    public String getDongNumber() {
        return dongNumber;
    }

    public void setDongNumber(String dongNumber) {
        this.dongNumber = dongNumber;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getDongName() {
        return dongName;
    }

    public void setDongName(String dongName) {
        this.dongName = dongName;
    }

    public String getKaptCode() {
        return kaptCode;
    }

    public void setKaptCode(String kaptCode) {
        this.kaptCode = kaptCode;
    }

    public String getKaptName() {
        return kaptName;
    }

    public void setKaptName(String kaptName) {
        this.kaptName = kaptName;
    }

    public String getKaptAddr() {
        return kaptAddr;
    }

    public void setKaptAddr(String kaptAddr) {
        this.kaptAddr = kaptAddr;
    }

    public String getCodeSaleNm() {
        return codeSaleNm;
    }

    public void setCodeSaleNm(String codeSaleNm) {
        this.codeSaleNm = codeSaleNm;
    }

    public String getCodeHeatNm() {
        return codeHeatNm;
    }

    public void setCodeHeatNm(String codeHeatNm) {
        this.codeHeatNm = codeHeatNm;
    }

    public String getKaptTarea() {
        return kaptTarea;
    }

    public void setKaptTarea(String kaptTarea) {
        this.kaptTarea = kaptTarea;
    }

    public String getKaptDongCnt() {
        return kaptDongCnt;
    }

    public void setKaptDongCnt(String kaptDongCnt) {
        this.kaptDongCnt = kaptDongCnt;
    }

    public String getKaptdaCnt() {
        return kaptdaCnt;
    }

    public void setKaptdaCnt(String kaptdaCnt) {
        this.kaptdaCnt = kaptdaCnt;
    }

    public String getKaptBcompany() {
        return kaptBcompany;
    }

    public void setKaptBcompany(String kaptBcompany) {
        this.kaptBcompany = kaptBcompany;
    }

    public String getKaptAcompany() {
        return kaptAcompany;
    }

    public void setKaptAcompany(String kaptAcompany) {
        this.kaptAcompany = kaptAcompany;
    }

    public String getKapttel() {
        return kapttel;
    }

    public void setKapttel(String kapttel) {
        this.kapttel = kapttel;
    }

    public String getKaptFax() {
        return kaptFax;
    }

    public void setKaptFax(String kaptFax) {
        this.kaptFax = kaptFax;
    }

    public String getKaptUrl() {
        return kaptUrl;
    }

    public void setKaptUrl(String kaptUrl) {
        this.kaptUrl = kaptUrl;
    }

    public String getCodeAptNm() {
        return codeAptNm;
    }

    public void setCodeAptNm(String codeAptNm) {
        this.codeAptNm = codeAptNm;
    }

    public String getDoroJuso() {
        return doroJuso;
    }

    public void setDoroJuso(String doroJuso) {
        this.doroJuso = doroJuso;
    }

    public String getHoCnt() {
        return hoCnt;
    }

    public void setHoCnt(String hoCnt) {
        this.hoCnt = hoCnt;
    }

    public String getCodeMgrNm() {
        return codeMgrNm;
    }

    public void setCodeMgrNm(String codeMgrNm) {
        this.codeMgrNm = codeMgrNm;
    }
}
