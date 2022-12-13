package com.pos.excel;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileReader {

	private List<String> lines;

	public ExcelFileReader(File file) {
		lines = new LinkedList<>();
		readFile(file);
	}

	public List<String> readAllLines() {
		return Collections.unmodifiableList(lines);
	}

	private void readFile(File file) {
		try {
			Workbook workbook = WorkbookFactory.create(file);
			Sheet sheet = workbook.getSheetAt(0);
			int columns = sheet.getLastRowNum() > 0 ? sheet.getRow(0).getLastCellNum() : 0;
			Iterator<Row> rowItr = sheet.iterator();

			while (rowItr.hasNext()) {
				Row row = rowItr.next();
				StringBuilder sb = new StringBuilder();

				for (int i = 0; i < columns; i++) {
					Cell cell = row.getCell(i);
					sb.append(getCellValue(cell) + "\t");
				}

				if (!sb.toString().trim().isEmpty())
					lines.add(sb.toString());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getCellValue(Cell cell) {
		if (null == cell) {
			return "";
		} else if (cell.getCellType() == CellType.STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == CellType.NUMERIC) {
			if (DateUtil.isCellDateFormatted(cell)) {
				Date date = cell.getDateCellValue();
				return new SimpleDateFormat("yyyy-MM-dd").format(date);
			}

			Double doubleValue = cell.getNumericCellValue();
			Integer intValue = doubleValue.intValue();

			if (doubleValue.equals(intValue.doubleValue()))
				return intValue.toString();

			return doubleValue.toString();
		}
		return "";
	}
}
