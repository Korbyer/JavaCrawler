import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.net.URL;
import java.util.ArrayList;

public class Xml_Parser_Fix_PreRegion_Dong_Apt extends Xml_Parser_PreRegion_Dong_Apt{
    public Xml_Parser_Fix_PreRegion_Dong_Apt(){
        try {
            this.apiParserSearch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void apiParserSearch() throws Exception{
        new Excel_Writer_Xml_Region_Apt_Dong().Excel_Writer_Xml_Region_Apt(this.returnList());

    }

    public ArrayList<DTO> returnList() throws Exception {
        FileInputStream fis = new FileInputStream("/Users/admin/IdeaProjects/JavaCrawler/data/Food_Region_Apt_Dong.xls");
        Workbook wbk = new HSSFWorkbook(fis);
        Sheet sheet = wbk.getSheetAt(0);
        int rowNum = sheet.getPhysicalNumberOfRows();
        ArrayList<DTO> list = new ArrayList<DTO>();
        String checkAptName=null;
        String error="Error";
        String subString=error.substring(1,4);
        URL url=null;

        for (int i = 1; i < rowNum; i++) {
            Row row = sheet.getRow(i);
            if(row.createCell(4).getStringCellValue().equals(error)){

            }
            else{

            }
        }
        return list;
    }
}
