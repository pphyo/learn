package com.pos.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pos.util.ApplicationException;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class ExcelExportService<T> extends Service<Void> {

	private List<T> list;
	private File directory;
	private String exportFileName;
	private Class<?> type;

	public ExcelExportService(List<T> list, File directory, String exportFileName, Class<?> type) {
		this.list = list;
		this.directory = directory;
		this.exportFileName = exportFileName;
		this.type = type;
	}

	@Override
	protected Task<Void> createTask() {
		return new Task<Void>() {
			@Override
			protected Void call() throws Exception {
				try (Workbook workbook = new XSSFWorkbook();
						FileOutputStream fos = new FileOutputStream(getFileName(directory, exportFileName))) {
					Sheet sheet = workbook.createSheet();
					Row headerRow = sheet.createRow(0);

					ExcelHelper<T> helper = ExcelHelper.getInstance(type);
					List<String> headers = helper.getHeaders();

					IntStream.range(0, headers.size()).forEach(i -> {
						headerRow.createCell(i).setCellValue(headers.get(i));
					});

					for (int i = 0; i < list.size(); i++) {
						Row dataRow = sheet.createRow(i + 1);
						List<Object> values = helper.getValues(list.get(i));

						for (int j = 0; j < values.size(); j++) {
							dataRow.createCell(j).setCellValue(values.get(j).toString());
						}
					}

					IntStream.range(0, headers.size()).forEach(sheet::autoSizeColumn);
					workbook.write(fos);
				}catch(Exception e) {
					e.printStackTrace();
					throw new ApplicationException("Export Process Fail");
				}
				return null;
			}
		};
	}

	private File getFileName(File directory, String exportFileName) {
		return new File(directory, String.format("%s-%s.xlsx", LocalDateTime.now().format(DateTimeFormatter.ofPattern("ddMMyyyyHHmmssa")),
				exportFileName));
	}

}
