package com.pos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.dao.InvoiceDao;
import com.pos.entity.Invoice;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class InvoiceList implements Initializable {
	@FXML
	private TextField salePerson;

	@FXML
	private DatePicker dateFrom;

	@FXML
	private DatePicker dateTo;

	@FXML
	private TableView<Invoice> invoiceTable;

	@FXML
	private Label totalRecord;

	private InvoiceDao invoiceDao;

	@FXML
	void reset() {
		salePerson.clear();
		dateFrom.setValue(null);
		dateTo.setValue(null);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		invoiceDao = InvoiceDao.getInstance();

		salePerson.textProperty().addListener((a, b, c) -> search());
		dateFrom.valueProperty().addListener((a, b, c) -> search());
		dateTo.valueProperty().addListener((a, b, c) -> search());

		search();
	}

	private void search() {
		invoiceTable.getItems().clear();
		List<Invoice> orders = invoiceDao.search(salePerson.getText(), dateFrom.getValue(), dateTo.getValue());
		invoiceTable.getItems().addAll(orders);
		long count = invoiceTable.getItems().stream().count();
		totalRecord.setText(String.valueOf(count));
	}
}
