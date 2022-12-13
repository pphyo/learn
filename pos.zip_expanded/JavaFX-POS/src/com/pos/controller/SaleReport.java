package com.pos.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.dao.SaleOrderDao;
import com.pos.entity.SaleOrder;
import com.pos.util.Reloader;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class SaleReport implements Initializable, Reloader{

    @FXML
    private TextField salePerson;

    @FXML
    private DatePicker dateFrom;

    @FXML
    private DatePicker dateTo;

    @FXML
    private TableView<SaleOrder> reportTable;
    
    @FXML
    private Label totalRecord;
    
    private SaleOrderDao saleDao;

    @FXML
    void reset() {
    	salePerson.clear();
    	dateFrom.setValue(null);
    	dateTo.setValue(null);
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		saleDao = SaleOrderDao.getInstance();
		
		salePerson.textProperty().addListener((a, b, c) -> search());
		dateFrom.valueProperty().addListener((a, b, c) -> search());
		dateTo.valueProperty().addListener((a, b, c) -> search());
		
		search();
	}
	
	private void search() {
		reportTable.getItems().clear();
		List<SaleOrder> orders = saleDao.search(salePerson.getText(), dateFrom.getValue(), dateTo.getValue());
		reportTable.getItems().addAll(orders);
		long count = reportTable.getItems().stream().count();
		totalRecord.setText(String.valueOf(count));
	}

	@Override
	public void reload() {
		search();
	}
}
