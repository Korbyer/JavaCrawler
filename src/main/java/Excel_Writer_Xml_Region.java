import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by admin on 2017. 9. 25..
 * 파싱된 Xml 내용을 엑셀에 저장하는 파일
 */
public class Excel_Writer_Xml_Region {

    public void Excel_Writer_Xml_Region(ArrayList<DTO> list){
        Workbook workbook=new HSSFWorkbook();


        Sheet sheet=workbook.createSheet("sheet1");
        Row row;

        row=sheet.createRow(0);
        row.createCell(0).setCellValue("cityCode");
        row.createCell(1).setCellValue("citySidoName");
        row.createCell(2).setCellValue("citySggName");
        //타이틀 작성

        int count=1;
        for(DTO entity:list){
            if(entity.getCitySidoName().startsWith("서울")) {
                //가장 중요한부분! 서울만 모으기위해서는 이 부분에서 전처리를 실행한다
                row = sheet.createRow(count);
                count += 1;

                row.createCell(0).setCellValue(entity.getCityCode());
                row.createCell(1).setCellValue(entity.getCitySidoName());
                row.createCell(2).setCellValue(entity.getCitySggName());
                //항목붙이기
            }


        }

        FileOutputStream fos;
        try{
            fos=new FileOutputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region.xls");
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
