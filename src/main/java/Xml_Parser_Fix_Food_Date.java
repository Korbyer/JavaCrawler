import au.com.bytecode.opencsv.CSVReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Xml_Parser_Fix_Food_Date implements Xml_Parser {

    public Xml_Parser_Fix_Food_Date(int year){
        try{
            System.out.println("Xml_Parser_Food_Date is running Now...");
            apiParserSearch(year);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void apiParserSearch(int year) throws Exception{
        File inputFile=new File("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\Food_Date_"+String.valueOf(year)+".csv");
        FileReader fReader=new FileReader(inputFile);
        BufferedReader buffReader=new BufferedReader(fReader);
        CSVReader reader=new CSVReader(fReader,',','"');
        List<String[]> csvBody=reader.readAll();
        String input=null,errorCheck=null;
        int lineCount=0;
        while((input=buffReader.readLine())!=null)
        {
            lineCount++;
        }
        buffReader.close();

        for(int i=1;i<lineCount;i++){

                errorCheck = csvBody.get(i)[2];
                if(errorCheck=="ServerError"){
                    try{

                    }catch (Exception e){
                        System.out.println(i+"번째 "+csvBody.get(i)[1]+"월 데이터 오류발생, HTTP코드 = "+e.getMessage());
                    }
                }


        }
    }


    public String getURLParam(String cityCode,String aptCode,String year,String month){/**4, 7, 1*/
        String url=PHARAM_URL+METHOD_GET_Food_Date+KEY+ROF+FOOD_YEAR+year+FOOD_MONTH;
        if(Integer.parseInt(month)<10 && cityCode!=null && aptCode!=null){
            url=url+"0"+month+CITY_CODE+cityCode+APT_CODE+aptCode;
        }
        if(Integer.parseInt(month)>=10 && cityCode!=null && aptCode!=null){
            url=url+month+CITY_CODE+cityCode+APT_CODE+aptCode;
        }
        return url;
    }
    public String getURLParam(String data) {
        return null;
    }

    public void printList(ArrayList<DTO> list) {

    }

    public void apiParserSearch() throws Exception {

    }
}
