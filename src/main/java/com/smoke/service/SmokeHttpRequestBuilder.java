package com.smoke.service;

import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.smoke.dto.SmokeHttpRequestDTO;
import com.smoke.dto.SmokeHttpWrapperDTO;
import com.smoke.enumeration.HTTPRequestType;

public class SmokeHttpRequestBuilder {

	public SmokeHttpWrapperDTO[] prepareForLaunch(XSSFWorkbook workBook){
		int numberOfSheets = workBook.getNumberOfSheets();
		return prepareRequestFromSheet(workBook.getSheetAt(0));
		//for(int i = 0 ; i<numberOfSheets; i++){
			
		//}
		
	}
	
	private SmokeHttpWrapperDTO[] prepareRequestFromSheet(XSSFSheet sheet){
		int numberOfRows = sheet.getPhysicalNumberOfRows();
		ArrayList<SmokeHttpWrapperDTO> list = new ArrayList<>();
		if (numberOfRows < 2){
			// add some exit condition here
		}
		for(int i = 1; i < numberOfRows; i++){
			SmokeHttpWrapperDTO smokeHttpWrapperDTO = new SmokeHttpWrapperDTO();
			smokeHttpWrapperDTO.setSmokeHttpRequestDTO(buildSmokeHttpRequestDTO(sheet.getRow(i)));
			list.add(smokeHttpWrapperDTO);
		}
		return list.toArray(new SmokeHttpWrapperDTO[list.size()]);
	}
	
	private SmokeHttpRequestDTO buildSmokeHttpRequestDTO(XSSFRow row){
		SmokeHttpRequestDTO requestDTO = new SmokeHttpRequestDTO();
		requestDTO.setUrl(row.getCell(0).getRichStringCellValue().getString());
		requestDTO.setHttpRequestType(HTTPRequestType.fromValue(row.getCell(1).getRichStringCellValue().getString()));
		requestDTO.setRequestPayload(row.getCell(2).getRichStringCellValue().getString());
		// TODO will do some thing about this, soon
		//requestDTO.setHeaders(row.getCell(0).toString());
		return requestDTO;
	} 
}
