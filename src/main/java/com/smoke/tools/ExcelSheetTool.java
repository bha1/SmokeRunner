package com.smoke.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetTool {

	public XSSFWorkbook generateTemplateFile(){
		XSSFWorkbook templateWorkBook = new XSSFWorkbook();
		XSSFSheet sheet = templateWorkBook.createSheet("test plan");

		Map<String, Object[]> testCase = new TreeMap<>();
		testCase.put("1", new Object[]{"URL","TYPE","REQUEST_PAYLOAD","HEADERS","STATUS","RESPONSE_PAYLOAD"});
		testCase.put("2", new Object[]{"http://ofss601022:7002/digx/v1/submissions/","POST","{\"dictionaryArray\":[{\"nameValuePairDTOArray\":[{\"name\":\"offerId\",\"value\":\"NCS006\",\"genericName\":\"offerId\"},{\"name\":\"campaignCode\",\"value\":\"LRH\",\"genericName\":\"campaignCode\"}]}]}","","",""});
		
		Set<String> keyid = testCase.keySet();
		int rowid = 0;
		XSSFRow row;
		for (String key : keyid)
	      {
	         row = sheet.createRow(rowid++);
	         Object [] objectArr = testCase.get(key);
	         int cellid = 0;
	         for (Object obj : objectArr)
	         {
	            Cell cell = row.createCell(cellid++);
	            cell.setCellValue((String)obj);
	         }
	      }
		return templateWorkBook;
	}
	
	public XSSFWorkbook getWorkbookFromInputStream(InputStream inputStream){
		XSSFWorkbook xssfWorkbook = null;
		try {
			xssfWorkbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return xssfWorkbook;
	}
}
