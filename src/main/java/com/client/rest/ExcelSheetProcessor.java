package com.client.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelSheetProcessor {
	
	private InputStream getFile(String fileName){
		return getClass().getClassLoader().getResourceAsStream(fileName);
	}
	
public static void main(String[] args) {
	ExcelSheetProcessor exPr = new ExcelSheetProcessor();
	try {
		//File tempFile = new File("D:\\workspace\\cmd_fire\\REST_API.xlsx");
		@SuppressWarnings("resource")
		XSSFWorkbook workBook = new XSSFWorkbook(exPr.getFile("REST_API.xlsx"));
		XSSFSheet workSheet = workBook.getSheetAt(0);
		System.out.println(workSheet.getRow(2).getPhysicalNumberOfCells());
		System.out.println(workSheet.getPhysicalNumberOfRows());
		Iterator<Row> rowIterator = workSheet.iterator();
		while(rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while(cellIterator.hasNext()) {
				String sandBoc = cellIterator.next().toString().replaceAll("\n", "");
				System.out.println(sandBoc+"->");
			}
			System.out.println("");
		}
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
