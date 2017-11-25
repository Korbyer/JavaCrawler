import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashSet;

public class Excel_Writer_Xml_Holiday {

    public void Excel_Writer_Xml_Holiday(ArrayList<DTO> list,int year){
        Workbook workbook=new HSSFWorkbook();

        ArrayList<DTO> save_list=new ArrayList<DTO>();
        ArrayList<String> check_list=new ArrayList<String>();
        ArrayList<String> result=new ArrayList<String>();
        String dateKind=null,dateName=null,isHoliday=null,locdate="";

        Sheet sheet=workbook.createSheet("sheet1");
        Row row;

        row=sheet.createRow(0);
        row.createCell(0).setCellValue("dateKind");
        row.createCell(1).setCellValue("dateName");
        row.createCell(2).setCellValue("isHoliday");
        row.createCell(3).setCellValue("locdate");

        int count=1;

        for(DTO entity:list){
            check_list.add(entity.getLocdate());
        }
        HashSet hashSet=new HashSet(check_list);
        result=new ArrayList<String>(hashSet);

        for(DTO entity : list){
            if(result.contains(entity.getLocdate())){
                result.remove(entity.getLocdate());
                row = sheet.createRow(count);
                count += 1;
                row.createCell(0).setCellValue(entity.getDateKind());
                row.createCell(1).setCellValue(entity.getDateName());
                row.createCell(2).setCellValue(entity.getIsHoliday());
                row.createCell(3).setCellValue(entity.getLocdate());
            }
        }


        FileOutputStream fos;
        try{
            fos=new FileOutputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region_Holiday_"+year+".xls");
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
