import au.com.bytecode.opencsv.CSVWriter;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Csv_Writer_Excel_Population implements Csv_Writer{
    public Csv_Writer_Excel_Population(ArrayList<DTO> list,int year){
        try{
            Csv_Writer(list,year);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void Csv_Writer(ArrayList<DTO> list, int year) {
        try{
            System.out.println("PreRegion CSV is Now Running...");
            CSVWriter csvWriter=new CSVWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Population_Dong.csv"),"EUC-KR"),',','"');
            csvWriter.writeNext(new String[]{"dongName","one_furniture","two_furniture","three_furniture","four_furniture","five_furniture","six_furniture","seven_furniture"});
            try{
                for(DTO entity : list){
                    csvWriter.writeNext(new String[]{entity.getDongName(),entity.getOne_furniture(),entity.getTwo_furniture_(),
                            entity.getThree_furniture(),entity.getFour_furniture(),entity.getFive_furniture(),entity.getSix_furniture(),entity.getSeven_furniture()});
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                csvWriter.flush();
                csvWriter.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
