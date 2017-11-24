import au.com.bytecode.opencsv.CSVWriter;


import java.io.*;
import java.util.ArrayList;


public class Csv_Writer_Xml_Food_Date implements Csv_Writer{
    public void Csv_Writer_Xml_Food_Date(ArrayList<DTO> list,int year) {
        try{
            System.out.println("Csv_Writer is Running Now...");
            Csv_Writer(list,year);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void Csv_Writer(ArrayList<DTO> list,int year) {

        try {
            CSVWriter csvWriter=new CSVWriter(new OutputStreamWriter(new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Date_"+String.valueOf(year)+".csv"),"EUC-KR"),',','"');
            csvWriter.writeNext(new String[]{"disYear","disMonth","disDate","disDay","cityCode","citySidoName","citySggName","aptCode","aptName","disQuantity","disQuantityRate","disCount","disCountRate","dongName"});
            try{
                for(DTO entity : list){
                    csvWriter.writeNext(new String[]{entity.getDisYear(),entity.getDisMonth(),entity.getDisDate(),entity.getDisDay(),entity.getCityCode(),
                            entity.getCitySidoName(),entity.getCitySggName(),entity.getAptCode(),entity.getAptName(),entity.getDisQuantity(),entity.getDisQuantityRate(),entity.getDisCount(),entity.getDisCountRate(),entity.getDongName()});
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
