import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;

public class Excel_Writer_PreRegion_Remake implements Excel_Writer {

    public Excel_Writer_PreRegion_Remake(){
            try{
                System.out.println("Excel_Writer_PreRegion_Remake is running now...");
                ExcelWrite(FileRemake());
            }
            catch (Exception e){
                e.printStackTrace();
            }

    }
    public void ExcelWrite(ArrayList<DTO> list) {
        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet("sheet1");
        Row row;

        row = sheet.createRow(0);
        row.createCell(0).setCellValue("cityName");
        row.createCell(1).setCellValue("regionName");
        row.createCell(2).setCellValue("dongName");
        row.createCell(3).setCellValue("dongNumber");//중요데이터

        int count = 1;
        for (DTO entity : list) {
            row = sheet.createRow(count);
            count += 1;

            row.createCell(0).setCellValue(entity.getCityName());
            row.createCell(1).setCellValue(entity.getRegionName());
            row.createCell(2).setCellValue(entity.getDongName());
            row.createCell(3).setCellValue(entity.getDongNumber());
            //항목붙이기
        }


        FileOutputStream fos;
        try{
            fos=new FileOutputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\PreRegion_Dong_Remake.xls"); /** <2> */
            workbook.write(fos);
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public ArrayList<DTO> FileRemake() throws Exception{


        /**http://code.mogaha.go.kr/cod/frt/codesearch_beop_main.do
         * 해당 사이트에서 직접 xls 파일을 저장
         * 이를 대체할 자동 API 필요*/
        FileInputStream fis=new FileInputStream("C:\\Users\\admin\\IdeaProjects\\JavaCrawler\\data\\PreRegion_Dong.xls");/** PreRegion_Dong.xls 엑셀파일 저장위치 불러오기 */
        Workbook wbk=new HSSFWorkbook(fis);
        Sheet sheet = wbk.getSheetAt(0);
        String regionName,cityName,dongName,dongNumber=null;
        String[] remake=null;
        String noData="NoData";
        int rowNum=sheet.getPhysicalNumberOfRows();
        ArrayList<DTO> list=new ArrayList<DTO>();

        for(int i=2;i<rowNum;i++){
            DTO entity = new DTO();
            Row row = sheet.getRow(i);
            dongNumber = row.getCell(0).getStringCellValue();
            remake = row.getCell(1).getStringCellValue().split(" ");
            int remake_length=remake.length;
            if(remake_length<2){
                cityName=remake[0];
                regionName=noData;
                dongName=noData;
            }
            else if(remake_length<3){
                cityName=remake[0];
                regionName=remake[1];
                dongName=noData;
            }
            else{
                cityName=remake[0];
                regionName=remake[1];
                dongName=remake[2];
            }



            entity.setDongNumber(dongNumber);
            entity.setRegionName(regionName);
            entity.setCityName(cityName);
            entity.setDongName(dongName);

            list.add(entity);

        }
        return list;
    }
}
