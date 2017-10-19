import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by admin on 2017. 10. 18..
 * 간단합니다.
 * ArrayList<DTO> 형식으로 받은 인자값을 분석하여(정제된 데이터)
 * 각 데이터에 들어있는 값들을 모두 읽어서 Excel 파일에 쓰는 것입니다
 * 쓰기가 모두 끝나면 엑셀데이터 파일 저장! 끝!
 */
public class Excel_Writer_Xml_Food_Date implements Excel_Writer {

    public Excel_Writer_Xml_Food_Date(ArrayList<DTO> list){
        try{
            System.out.println("Excel_Writer_Xml_Food_Date is running now...");
            ExcelWrite(list);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void ExcelWrite(ArrayList<DTO> list){
        Workbook workbook=new XSSFWorkbook();


        Sheet sheet=workbook.createSheet("sheet1");
        Row row;

        row=sheet.createRow(0);
        row.createCell(0).setCellValue("disYear");
        row.createCell(1).setCellValue("disMonth");
        row.createCell(2).setCellValue("disDate");
        row.createCell(3).setCellValue("disDay");//중요데이터
        row.createCell(4).setCellValue("cityCode");
        row.createCell(5).setCellValue("citySidoName");
        row.createCell(6).setCellValue("citySggName");
        row.createCell(7).setCellValue("aptCode");//중요데이터
        row.createCell(8).setCellValue("aptName");//중요데이터
        row.createCell(9).setCellValue("disQuantity");//중요데이터
        row.createCell(10).setCellValue("disQuantityRate");
        row.createCell(11).setCellValue("disCount");
        row.createCell(12).setCellValue("disCountRate");
        //타이틀 작성

        int count=1;
        for(DTO entity:list){
            if(entity.getCitySidoName().startsWith("서울")) {
                //가장 중요한부분! 서울만 모으기위해서는 이 부분에서 전처리를 실행한다
                row = sheet.createRow(count);
                count += 1;

                row.createCell(0).setCellValue(entity.getDisYear());
                row.createCell(1).setCellValue(entity.getDisMonth());
                row.createCell(2).setCellValue(entity.getDisDate());
                row.createCell(3).setCellValue(entity.getDisDay());
                row.createCell(4).setCellValue(entity.getCityCode());
                row.createCell(5).setCellValue(entity.getCitySidoName());
                row.createCell(6).setCellValue(entity.getCitySggName());
                row.createCell(7).setCellValue(entity.getAptCode());
                row.createCell(8).setCellValue(entity.getAptName());
                row.createCell(9).setCellValue(entity.getDisQuantity());
                row.createCell(10).setCellValue(entity.getDisQuantityRate());
                row.createCell(11).setCellValue(entity.getDisCount());
                row.createCell(12).setCellValue(entity.getDisCountRate());
                //항목붙이기
            }


        }

        FileOutputStream fos;
        try{
            fos=new FileOutputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Date.xlsx"); /** <2> */
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
