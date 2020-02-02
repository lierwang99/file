package myStudy.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {
	public static void main(String[] args) {
	}

	public static String readXls_x(String filePath, String sheetName, int row, int column) {
		filePath = filePath.trim();
		sheetName = sheetName.trim();
		File file = new File(filePath);
		if ((!file.exists()) || (!file.isFile()) || (filePath.length() < 5)) {
			System.out.println("请检查文件");
			return null;
		}
		int len = filePath.length();
		String reciprocalThree = filePath.substring(len - 3, len);
		String reciprocalFour = filePath.substring(len - 4, len);
		if ((!"xls".equalsIgnoreCase(reciprocalThree)) && (!"xlsx".equalsIgnoreCase(reciprocalFour))) {
			System.out.println("请检查文件类别");
			return null;
		}
		Sheet sh_x = null;
		Workbook wb = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			if ("xls".equalsIgnoreCase(reciprocalThree)) {
				wb = new HSSFWorkbook(fis);
			}
			if ("xlsx".equalsIgnoreCase(reciprocalFour)) {
				wb = new XSSFWorkbook(fis);
			}
			sh_x = wb.getSheet(sheetName);
			int lastRowNum = sh_x.getLastRowNum();
			if (row > 0 && row <= (lastRowNum +1)) {
				 Row sh_xRow = sh_x.getRow(row - 1);
				 short lastCellNum = sh_xRow.getLastCellNum();
				if (column > 0 && column <= lastCellNum) {
					Cell cell = sh_xRow.getCell(column-1);
					cell.setCellType(Cell.CELL_TYPE_STRING);
					return cell.getStringCellValue();
				}
				return null;
			} else {
				return null;
			}
		} catch (Exception e) {
			String str1 = e.getMessage();
			return str1;
		} finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static boolean writeXls_x(String filePath, String sheetName, String src, int row, int column) {
		filePath = filePath.trim();
		sheetName = sheetName.trim();
		File file = new File(filePath);
		if ((!file.exists()) || (!file.isFile()) || (filePath.length() < 5)) {
			return false;
		}
		int len = filePath.length();
		String reciprocalThree = filePath.substring(len - 3, len);
		String reciprocalFour = filePath.substring(len - 4, len);
		if ((!"xls".equalsIgnoreCase(reciprocalThree)) && (!"xlsx".equalsIgnoreCase(reciprocalFour))) {
			return false;
		}
		Sheet sh_x = null;
		Workbook wb = null;
		FileOutputStream fout = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
			if ("xls".equalsIgnoreCase(reciprocalThree)) {
				wb = new HSSFWorkbook(fis);
			}
			if ("xlsx".equalsIgnoreCase(reciprocalFour)) {
				wb = new XSSFWorkbook(fis);
			}

			sh_x = wb.getSheet(sheetName);
			if ((row > 0) && (column > 0) && (sh_x != null)) {
				row--;
				column--;
				Row xssfRow = sh_x.getRow(row);
				if (xssfRow == null) {
					xssfRow = sh_x.createRow(row);
				}
				Cell xssfCell = xssfRow.getCell(column);
				if (xssfCell == null) {
					xssfCell = xssfRow.createCell(column);
				}
				xssfCell.setCellType(Cell.CELL_TYPE_STRING);
				xssfCell.setCellValue(src);
				fout = new FileOutputStream(file);
				wb.write(fout);
				fout.close();
				return true;
			}
			System.out.println("请检查sheet表名，以及行和列是否有效！");
			return false;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
}