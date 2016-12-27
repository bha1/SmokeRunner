package com.smoke.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	
	public Map<String, Object[]> buildRowMap(SmokeHttpWrapperDTO[] wrapperDTOs){
		Map<String, Object[]> testCase = new TreeMap<>();
		int wrapperLength = wrapperDTOs.length;
		for(int i=0;i<wrapperLength;i++){
			testCase.put(String.valueOf(i+2), buildStringArray(wrapperDTOs[i]));
		}
		return testCase;
	}
	
	
	private Object[] buildStringArray(SmokeHttpWrapperDTO wrapperDTO){
		List<Object> list = new ArrayList<>();
		list.add(wrapperDTO.getSmokeHttpRequestDTO().getUrl());
		list.add(wrapperDTO.getSmokeHttpRequestDTO().getHttpRequestType().toString());
		list.add(wrapperDTO.getSmokeHttpRequestDTO().getRequestPayload());
		list.add(wrapperDTO.getSmokeHttpRequestDTO().getHeaders());
		list.add(String.valueOf(wrapperDTO.getSmokeHttpResponseDTO().getStatusLine().getStatusCode()));
		list.add(wrapperDTO.getSmokeHttpResponseDTO().getResponse());
		return list.toArray(new Object[list.size()]);
	}
}
