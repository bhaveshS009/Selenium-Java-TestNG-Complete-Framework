package com.org.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;


public class ExcelReader {
	public ConfigDataProvider config;
	
	public Row getExcelRowElement(String ExcelSheetName,int RowValue)  {
		Row rw = null;
		try {
			config = new ConfigDataProvider();
			
			FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ config.getTestDataExcelPath());
	
			//System.out.println(System.getProperty("user.dir")+ config.getTestDataExcelPath());
	
			Workbook wb = WorkbookFactory.create(fis);
			
			Sheet sh = wb.getSheet(ExcelSheetName);
			//Increase with 1 count to match the indexing with Excel standard
			rw = sh.getRow(RowValue);
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}
				
		return rw;		
	}
	
	
	public String getExcelCellData(String ExcelSheetName,int RowValue, int ColoumnValue) throws EncryptedDocumentException, IOException {
		//Increase with 1 count to match the indexing with Excel standard
		Cell cellData = (getExcelRowElement(ExcelSheetName,RowValue)).getCell(ColoumnValue);
		
		return cellData.getStringCellValue();	
	}
	
	
	public List<String> convertRowToList(Row row) {
	    List<String> rowData = new ArrayList<>();
	    if (row != null) {
	        for (Cell cell : row) {
	        	//ignoring the row header to merge with added list
	        	if (cell.getColumnIndex()>0) {
	        	switch (cell.getCellType()) {
	                case STRING:
	                    rowData.add(cell.getStringCellValue());
	                    break;
	                case NUMERIC:
	                    rowData.add(String.valueOf(cell.getNumericCellValue()));
	                    break;
	                case BOOLEAN:
	                    rowData.add(String.valueOf(cell.getBooleanCellValue()));
	                    break;
	                case BLANK:
	                    rowData.add("");
	                    break;
	                default:
	                    rowData.add("");
	            }
	            continue;
	            }
	        }
	    }
	    return rowData;
	}

	
	public List<String> getExcelRowList(String ExcelSheetName,int RowValue)  {
		//Row rw = null;
		Row RowElement = getExcelRowElement(ExcelSheetName,RowValue);
		return convertRowToList(RowElement);
		
	}	
}
