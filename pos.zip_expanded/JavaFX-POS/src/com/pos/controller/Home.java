package com.pos.controller;

import java.net.URL;
import java.time.LocalDate;
import java.util.Map;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import java.util.Set;

import com.pos.dao.CategoryDao;
import com.pos.dao.ItemDao;
import com.pos.dao.MemberDao;
import com.pos.dao.SaleOrderDao;
import com.pos.util.LocalDateConverter;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.chart.XYChart.Series;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class Home implements Initializable{
	
	@FXML
	private LineChart<String, Integer> chart;
	
	@FXML
	private DatePicker dateFrom;
	
	@FXML
	private DatePicker dateTo;
	
	@FXML
	private Label memberCount;
	
	@FXML
	private Label categoryCount;
	
	@FXML
	private Label itemCount;
	
	private SaleOrderDao orderDao;
	private MemberDao memberDao;
	private CategoryDao categoryDao;
	private ItemDao itemDao;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		orderDao = SaleOrderDao.getInstance();
		memberDao = MemberDao.getInstance();
		categoryDao = CategoryDao.getInstance();
		itemDao = ItemDao.getInstance();
		
		dateFrom.setConverter(new LocalDateConverter());
		dateTo.setConverter(new LocalDateConverter());
		
		dateFrom.setValue(LocalDate.now().minusMonths(1));
		dateTo.setValue(LocalDate.now());
		
		dateFrom.valueProperty().addListener((a, b, c) -> loadChart());
		dateTo.valueProperty().addListener((a, b, c) -> loadChart());
		loadChart();
	}

	private void loadChart() {
		chart.getData().clear();
		
		Map<String, Map<String, Integer>> orders = orderDao.find(dateFrom.getValue(), dateTo.getValue());
	
		Set<Entry<String, Map<String, Integer>>> entrySet = orders.entrySet();
		
		for (Entry<String, Map<String, Integer>> entry : entrySet) {
			Series<String, Integer> series = new Series<>();
			series.setName(entry.getKey());
			
			Set<Entry<String, Integer>> dataSet = entry.getValue().entrySet();
			
			for(Entry<String, Integer> data : dataSet) {
				series.getData().add(new Data<>(data.getKey(), data.getValue()));
			}
			
			chart.getData().add(series);
		}
		
		memberCount.setText(String.valueOf(memberDao.getCount()));
		categoryCount.setText(String.valueOf(categoryDao.getCount()));
		itemCount.setText(String.valueOf(itemDao.getCount()));
	}

}
