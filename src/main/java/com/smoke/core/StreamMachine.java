package com.smoke.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.smoke.dto.SmokeHttpWrapperDTO;
import com.smoke.service.SmokeHttpRequestBuilder;
import com.smoke.tools.ExcelSheetTool;

/**
 * all the input, output and file streams
 * are being managed here
 * 
 * @author pbhawan
 *
 */
public class StreamMachine {
	
	public File getTemplateFile(){
		File tempFile = null;
		try {
			tempFile = File.createTempFile("template", "xlsx");
			tempFile.deleteOnExit();
			ExcelSheetTool excelSheetTool = new ExcelSheetTool();
			FileOutputStream out = new FileOutputStream(tempFile);
			excelSheetTool.generateTemplateFile().write(out);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tempFile;
	}
	
	public File smokeRunner(InputStream inputStream){
		File tempFile = null;
		
		try {
			ExcelSheetTool excelSheetTool = new ExcelSheetTool();
			XSSFWorkbook workBook = excelSheetTool.getWorkbookFromInputStream(inputStream);
			SmokeHttpRequestBuilder smokeHttpRequestBuilder = new SmokeHttpRequestBuilder();
			SmokeHttpWrapperDTO[] wrapperDTOs = smokeHttpRequestBuilder.prepareForLaunch(workBook);
			HTTPEngine httpEngine = new HTTPEngine();
			httpEngine.httpSmokeRunner(wrapperDTOs);
			tempFile = File.createTempFile("input", "xlsx");
			tempFile.deleteOnExit();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tempFile;
	}

}
