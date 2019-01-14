package com.cpscs.omsorderbill.utils;


import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * /**
 *
 * @Description
 * @Author xuzhong <xuzhongiop@qq.com>
 * @Version V1.0.0
 * @Since 1.0
 * @Date 2019/1/4 0004
 */
public class ExcelUtil<T> {
    public File writeExcel(List<T> list,Class<T> cls,List<String> fieldNames,List<String> titleNames,String fileName){
        FileOutputStream xlsStream = null;
        File xlsFile = new File(fileName);
        try {
        // 创建工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 创建工作表
        HSSFSheet sheet = workbook.createSheet("sheet1");
        int startRow=0;

        //写表头
        if(titleNames!=null&&titleNames.size()!=0){
            startRow++;
            HSSFRow rows = sheet.createRow(0);
            for (int col = 0; col < titleNames.size(); col++) {
                rows.createCell(col).setCellValue(titleNames.get(col));
            }
        }


        if(fieldNames==null){
            for (int row = startRow; row < list.size(); row++) {
                HSSFRow rows = sheet.createRow(row);
                Object object = list.get(row-startRow);
                for (int col = 0; col < cls.getDeclaredFields().length; col++) {
                    // 向工作表中添加数据
                    Field field = cls.getDeclaredFields()[col];
                    objectWriteToHSSFRow(field,object,rows,col);
                }
            }

        }else {
            for (int row = startRow; row < list.size(); row++) {
                HSSFRow rows = sheet.createRow(row);
                Object object = list.get(row-startRow);
                for (int col = 0; col < fieldNames.size(); col++) {
                    // 向工作表中添加数据
                    Field field = cls.getDeclaredField(fieldNames.get(col));
                    objectWriteToHSSFRow(field,object,rows,col);
                }
            }
        }


        if(!xlsFile.exists()){
            xlsFile.createNewFile();
        }

            xlsStream = new FileOutputStream(xlsFile);
            workbook.write(xlsStream);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                xlsStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return xlsFile;
    }
    private void objectWriteToHSSFRow(Field field,Object object,HSSFRow rows ,int col) throws Exception{
        field.setAccessible(true);
        Object value=field.get(object);
        if(value==null){
            rows.createCell(col).setCellValue("无");
        }else{
            rows.createCell(col).setCellValue(value.toString());
        }
    }


    private final static String xls = "xls";
    private final static String xlsx = "xlsx";
    public List<T> readExcel(MultipartFile file, Class<T> cls, String [] fieldNames){
        //检查文件
        try {
            checkFile(file);

        //获得Workbook工作薄对象
        Workbook workbook = getWorkBook(file);
        //创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
        List<T> list = new ArrayList<T>();
        if(workbook != null){
            for(int sheetNum = 0;sheetNum < workbook.getNumberOfSheets();sheetNum++){
                //获得当前sheet工作表
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if(sheet == null){
                    continue;
                }
                //获得当前sheet的开始行
                int firstRowNum  = sheet.getFirstRowNum();
                //获得当前sheet的结束行
                int lastRowNum = sheet.getLastRowNum();
                //循环除了第一行的所有行
                for(int rowNum = firstRowNum+1;rowNum <= lastRowNum;rowNum++){
                    //获得当前行
                    Row row = sheet.getRow(rowNum);
                    if(row == null){
                        continue;
                    }
                    //获得当前行的开始列
                    int firstCellNum = row.getFirstCellNum();
                    //获得当前行的列数
                    int lastCellNum = row.getPhysicalNumberOfCells();
                    //String[] cells = new String[row.getPhysicalNumberOfCells()];
                    //循环当前行
                    T object =cls.newInstance();


                    for(int cellNum = firstCellNum,fieldIndex=0; cellNum < lastCellNum;cellNum++,fieldIndex++){
                        Cell cell = row.getCell(cellNum);
                        Field field=cls.getDeclaredField(fieldNames[fieldIndex]);
                        field.setAccessible(true);
                        if("无".equals(getCellValue(cell))) {
                            field.set(object, "");
                        }else {
                            field.set(object, getCellValue(cell));
                        }
                    }
                    list.add(object);
                }
            }
            workbook.close();
        }
        return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void checkFile(MultipartFile file) throws IOException{
        //判断文件是否存在
        if(null == file){

            throw new FileNotFoundException("文件不存在！");
        }
        //获得文件名
        String fileName = file.getOriginalFilename();
        //判断文件是否是excel文件
        if(!fileName.endsWith(xls) && !fileName.endsWith(xlsx)){

            throw new IOException(fileName + "不是excel文件");
        }
    }
    public static Workbook getWorkBook(MultipartFile file) {
        //获得文件名
        String fileName = file.getOriginalFilename();
        //创建Workbook工作薄对象，表示整个excel
        Workbook workbook = null;
        try {
            //获取excel文件的io流
            InputStream is = file.getInputStream();
            //根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
            if(fileName.endsWith(xls)){
                //2003
                workbook = new HSSFWorkbook(is);
            }else if(fileName.endsWith(xlsx)){
                //2007
                workbook = new XSSFWorkbook(is);
            }
        } catch (IOException e) {

        }
        return workbook;
    }
    public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况

        if(cell.getCellType() == CellType.NUMERIC){
            cell.setCellType(CellType.STRING);
        }
       cellValue=cell.getStringCellValue();
        return cellValue;
    }

}
