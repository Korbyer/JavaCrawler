import au.com.bytecode.opencsv.CSVWriter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Csv_Writer_Xml_Food_Date implements Csv_Writer{
    public void Csv_Writer_Xml_Food_Date(ArrayList<DTO> list) {
        try{
            System.out.println("Csv_Writer is Running Now...");
            Csv_Writer(list);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Csv_Writer(ArrayList<DTO> list) {

        try {
            CSVWriter csvWriter=new CSVWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Date.csv"),"EUC-KR"),',','"');
            csvWriter.writeNext(new String[]{"disYear","disMonth","disDate","disDay","cityCode","citySidoName","citySggName","aptCode","aptName","disQuantity","disQuantityRate","disCount","disCountRate"});
            try{
                for(DTO entity : list){
                    csvWriter.writeNext(new String[]{entity.getDisYear(),entity.getDisMonth(),entity.getDisDate(),entity.getDisDay(),entity.getCityCode(),
                            entity.getCitySidoName(),entity.getCitySggName(),entity.getAptCode(),entity.getAptName(),entity.getDisQuantity(),entity.getDisQuantityRate(),entity.getDisCount(),entity.getDisCountRate()});
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

    }

}
