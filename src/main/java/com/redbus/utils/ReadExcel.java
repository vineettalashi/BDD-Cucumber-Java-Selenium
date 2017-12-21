package com.redbus.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.log4testng.Logger;

public class ReadExcel {

	private static FileInputStream fis;
	private final static Logger logger = Logger.getLogger(ReadExcel.class);
	private static XSSFWorkbook wb;

	
	public static Object[][] getAllTestData(String fileName, String Sheetname)
	{
		List<Object[]> results = new ArrayList<Object[]>();
		HashMap<String, String> input = new HashMap<String, String>();
		
		try {
				fis = new FileInputStream(Constants.datafilesPath + "/" + fileName + ".xlsx");
				wb = new XSSFWorkbook(fis);
			} 
		catch (IOException e) {
				logger.warn("Data excel not found!!!!");
			}
		XSSFSheet sh = wb.getSheet(Sheetname);
		int rowCount = sh.getPhysicalNumberOfRows();
		int colCount = sh.getRow(0).getLastCellNum();
		for(int row = 0; row<rowCount;row++)
		{
		for (int i = 0; i < colCount; i++) {
			input.put(sh.getRow(0).getCell(i).getStringCellValue(), sh.getRow(1).getCell(i).getStringCellValue());
		}
		
		
		results.add(new Object[]{input});
		}
		return results.toArray(new Object[0][]);
	}

}
