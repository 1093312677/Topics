package com.common;

import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookTool {
	/**
	 * 通过不同的excel格式获取不同的workbook
	 * @param fileName
	 * @param is
	 * @return
	 */
	public Workbook getWorkbook(String fileName, InputStream is) {
		Workbook wb = null;
		try{
			String sub = fileName.substring(fileName.lastIndexOf('.'));
	        if (".xlsx".equals(sub)) {
	        	wb = new XSSFWorkbook(is);
	        } else {
	        	wb = new HSSFWorkbook(is);
	        }
	   }catch(Exception e){
	        e.printStackTrace();   
	   }
		
		return wb;
	}
	/**
	 * 通过判断单元格格式获取字符串
	 * @param cell
	 * @return
	 */
	public String getValue(Cell cell) {
		if(cell==null){
            return ""; 
        }else{
        //判断单元格的数据类型
        switch (cell.getCellType()) {  
            case Cell.CELL_TYPE_NUMERIC: // 数字  
            	return String.valueOf(Integer.valueOf((int) cell.getNumericCellValue()));
            case Cell.CELL_TYPE_STRING: // 字符串  
            	return String.valueOf(cell.getStringCellValue() );  
            case Cell.CELL_TYPE_BOOLEAN: // Boolean  
            	return String.valueOf(cell.getBooleanCellValue() );  
            case Cell.CELL_TYPE_FORMULA: // 公式  
            	return String.valueOf(cell.getCellFormula() );  
            case Cell.CELL_TYPE_BLANK: // 空值  
            	 return "";   
            case Cell.CELL_TYPE_ERROR: // 故障  
            	 return "";  
            default:  
            	 return "";   
            }  
    }
	}

}
