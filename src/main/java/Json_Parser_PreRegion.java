import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedInputStream;
import java.net.URL;
import java.util.ArrayList; /**IMPORT 할때 주의해서 할것*/

/**
 * Created by admin on 2017. 11. 6..
 */
public class Json_Parser_PreRegion implements Json_Parser {
    public Json_Parser_PreRegion(){
        try{
            System.out.println("PreRegion Class is now Running...");
            apiParserSearch();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("PreRegion Class is Over Now!");
        }
    }
    public void apiParserSearch() throws Exception{
        new Csv_Writer_Json_PreRegion_Dong().Csv_Writer(returnList_Dong(returnList_Region(returnList())));

    }


    public String readURLParam(String kind) throws Exception{
        BufferedInputStream reader=null;
        StringBuffer buffer=null;
        URL url=null;

        try{
            url=new URL(getURLParam(kind));
            reader=new BufferedInputStream(url.openStream());
            buffer=new StringBuffer();
            int i;
            byte[] b=new byte[4096];
            while((i=reader.read(b))!=-1){
                buffer.append(new String(b,0,i));
            }

        }catch (Exception e){
            e.printStackTrace();

        }
        finally {
            if(reader!=null)
                reader.close();
        }

        return buffer.toString();
    }

    public String getURLParam(String kind){
        String url=null;
        if(kind.equals("cityNumber")){
            String cityNumber_url=PRE_BASIC_URL+PRE_WHOLE+"."+PRE_ROF;
            url=cityNumber_url;
        }
        else if(kind.length()==2 && kind.startsWith("11")){
            String regionNumber_url=PRE_BASIC_URL+PRE_REGION+"."+kind+"."+PRE_ROF;
            url=regionNumber_url;
        }
        else if(kind.length()==5 && kind.startsWith("11")){
            String dongNumber_url=PRE_BASIC_URL+PRE_REGION_DONG+"."+kind+"."+PRE_ROF;
            url=dongNumber_url;
        }
        return url;
    }

    public ArrayList<DTO> returnList() throws Exception {
        DTO entity=new DTO();
        ArrayList<DTO> list=new ArrayList<DTO>();
        JSONParser jsonparser = new JSONParser();
        JSONArray array = (JSONArray)jsonparser.parse(readURLParam("cityNumber"));/**해당 사이트를 해석한 결과가 바로 JSONARRAY 형식 그래서 바로 ARRAY로 적용*/
//        for(int i=0;i<array.size();i++){/** 만약 서울뿐만 아니라, 모든 도시지역을 모을경우, 해당반복문 활용*/
            JSONObject obj=(JSONObject)array.get(0);/**0 부분에 i로 대체*/
            String cityNumber = obj.get("code").toString();
            String cityName=obj.get("value").toString();

            entity.setCityNumber(cityNumber);
            entity.setCityName(cityName);

            list.add(entity);

//        }
        return list;
    }
    public ArrayList<DTO> returnList_Region(ArrayList<DTO> list) throws Exception{

        ArrayList<DTO> new_list=new ArrayList<DTO>();
        JSONParser jsonparser = new JSONParser();
        for(DTO entity : list){
            String cityNumber=entity.getCityNumber();
            String cityName=entity.getCityName();
            JSONArray array = (JSONArray)jsonparser.parse(readURLParam(cityNumber));
            for(int i=0;i<array.size();i++) {
                DTO new_entity=new DTO();
                JSONObject obj = (JSONObject) array.get(i);/**0 부분에 i로 대체*/
                String RegionNumber= obj.get("code").toString();
                String RegionName = obj.get("value").toString();

                new_entity.setCityNumber(cityNumber);
                new_entity.setCityName(cityName);
                new_entity.setRegionNumber(RegionNumber);
                new_entity.setRegionName(RegionName);

                new_list.add(new_entity);
            }
        }
        return new_list;
    }

    public ArrayList<DTO> returnList_Dong(ArrayList<DTO> list) throws Exception {

        ArrayList<DTO> new_list=new ArrayList<DTO>();
        JSONParser jsonparser = new JSONParser();
        for(DTO entity : list){
            String cityNumber=entity.getCityNumber();
            String cityName=entity.getCityName();
            String RegionNumber=entity.getRegionNumber();
            String RegionName=entity.getRegionName();
            JSONArray array = (JSONArray)jsonparser.parse(readURLParam(RegionNumber));

            for(int i=0;i<array.size();i++) {
                DTO new_entity=new DTO();
                JSONObject obj = (JSONObject) array.get(i);/**0 부분에 i로 대체*/
                String dongNumber = obj.get("code").toString();
                String dongName=obj.get("value").toString();

                new_entity.setDongNumber(dongNumber);
                new_entity.setDongName(dongName);
                new_entity.setCityNumber(cityNumber);
                new_entity.setCityName(cityName);
                new_entity.setRegionNumber(RegionNumber);
                new_entity.setRegionName(RegionName);

                new_list.add(new_entity);
            }
        }
        return new_list;
    }


}
