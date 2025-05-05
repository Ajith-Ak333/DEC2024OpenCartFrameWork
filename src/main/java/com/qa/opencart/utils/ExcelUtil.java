package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {

	private static final String path = ".\\src\\test\\resources\\testdata\\OpenCartTestData.xlsx";
	private static Workbook work;
	private static Sheet sheet;

	public static Object[][] getTestData(String sheetname) {
		
		Object ob[][] = null;
		
		try {
			FileInputStream fi = new FileInputStream(path);
			work = WorkbookFactory.create(fi);
			sheet = work.getSheet(sheetname);

			ob = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];

			for (int i = 0; i <= sheet.getLastRowNum() - 1; i++) {
				for (int j = 0; j <= sheet.getRow(0).getLastCellNum()-1; j++) {
					ob[i][j] = sheet.getRow(i + 1).getCell(j).toString();
				}
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ob;
	}
}
