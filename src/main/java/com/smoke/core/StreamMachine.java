package com.smoke.core;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

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

}
