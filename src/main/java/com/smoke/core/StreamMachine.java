package com.smoke.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedHashMap;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.smoke.dto.SmokeHttpWrapperDTO;
import com.smoke.dto.SmokeThreadLocal;
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
	
	private ThreadLocal<SmokeThreadLocal> threadLocal;
	
	public StreamMachine(){
		this.threadLocal = new ThreadLocal<>();
	}
	
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
			tempFile = File.createTempFile("input", "xlsx");
			tempFile.deleteOnExit();
			SmokeThreadLocal smokeThreadLocal = new SmokeThreadLocal();
			LinkedHashMap<String, String> threadMap = new LinkedHashMap<>();
			threadMap.put("somevalue", "someValue");
			smokeThreadLocal.setThreadMap(threadMap);
			threadLocal.set(smokeThreadLocal);;
			ExcelSheetTool excelSheetTool = new ExcelSheetTool();
			XSSFWorkbook workBook = excelSheetTool.getWorkbookFromInputStream(inputStream);
			SmokeHttpRequestBuilder smokeHttpRequestBuilder = new SmokeHttpRequestBuilder();
			SmokeHttpWrapperDTO[] wrapperDTOs = smokeHttpRequestBuilder.prepareForLaunch(workBook);
			HTTPEngine httpEngine = new HTTPEngine();
			FileOutputStream out = new FileOutputStream(tempFile);
			excelSheetTool.generateSmokeRunnerResult(httpEngine.httpSmokeRunner(wrapperDTOs)).write(out);
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return tempFile;
	}

}
