import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;

public class Excel_Writer_Xml_Region_Apt_Dong {

    public void Excel_Writer_Xml_Region_Apt(ArrayList<DTO> list){
        Workbook workbook=new HSSFWorkbook();

        Sheet sheet=workbook.createSheet("sheet1");
        Row row;

        row=sheet.createRow(0);
        row.createCell(0).setCellValue("cityCode");
        row.createCell(1).setCellValue("aptCode");
        row.createCell(2).setCellValue("citySggName");
        row.createCell(3).setCellValue("aptName");
        row.createCell(4).setCellValue("emdNm");/**동주소, 기존의 Food_Region_Apt.xls 파일에서 동주소 추가 및 차이점*/
        //타이틀 작성

        int count=1;
        for(DTO entity:list){

            row = sheet.createRow(count);
            count += 1;
            row.createCell(0).setCellValue(entity.getCityCode());
            row.createCell(1).setCellValue(entity.getAptCode());
            row.createCell(2).setCellValue(entity.getCitySggName());
            row.createCell(3).setCellValue(entity.getAptName());
            row.createCell(4).setCellValue(entity.getEmdNm());

            //항목붙이기
        }

        FileOutputStream fos;
        try{
            fos=new FileOutputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region_Apt_Dong.xls");
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
