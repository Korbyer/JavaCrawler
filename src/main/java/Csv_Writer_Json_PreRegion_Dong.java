import au.com.bytecode.opencsv.CSVWriter;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Csv_Writer_Json_PreRegion_Dong implements Csv_Writer{
    public void Csv_Writer(ArrayList<DTO> list, int year) {

    }
    public void Csv_Writer(ArrayList<DTO> list){
        try {
            System.out.println("PreRegion CSV is Now Running...");
            CSVWriter csvWriter=new CSVWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\PreRegion_City_Dong.csv"),"EUC-KR"),',','"');
            csvWriter.writeNext(new String[]{"cityName","cityNumber","regionName","regionNumber","dongName","dongNumber"});
            try{
                for(DTO entity : list){
                    csvWriter.writeNext(new String[]{entity.getCityName(),entity.getCityNumber(),entity.getRegionName(),entity.getRegionNumber(),entity.getDongName(),entity.getDongNumber()});
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            finally {
                csvWriter.flush();
                csvWriter.close();
            }


        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            System.out.println("PreRegion Csv is over now, CLEAR!");
        }
    }
}
