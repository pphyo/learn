package com.jdc.cacl;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class Calculator implements Initializable {
	
	@FXML
	private Label temp;
	@FXML
	private Label output;
	@FXML
	private GridPane grid;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		grid.getChildren().stream()
			.filter(node -> node instanceof Button)
			.map(node -> (Button)node)
			.forEach(btn -> btn.setOnAction(this::pressButton));
		
		clear();
		
	}
	
	private void pressButton(ActionEvent event) {
		Button btn = (Button)event.getSource();
		String btnName = btn.getText();
		switch (btnName) {
		case "C":
			clear();
			break;
		case "+/-":
			plusOrMinus();
			break;
		case ".":
			addDecimal();
			break;
		case "=":
			equal();
			break;
		case "%":
			calculatePercent();
			break;
		case "+":
		case "-":
		case "*":
		case "÷":
			pressOperator(btnName);
			break;
		default:
			pressNumber(btnName);
			break;
		}
	}
	
	private void plusOrMinus() {
		String text = output.getText();
		if(text.startsWith("-")) {
			output.setText(text.replaceAll("-", ""));
		} else {
			output.setText("-".concat(text));
		}
	}
	
	private void addDecimal() {
		String text = output.getText();
		if(!text.contains(".")) {
			output.setText(text.concat("."));
		}
	}
	
	private void equal() {
		String str1 = temp.getText();
		String str2 = output.getText();
		
		if(!str1.isEmpty()) {
			clear();
			String[] arr = str1.split(" ");
			String result = calculate(arr[0], str2, arr[1]);
			output.setText(result);
		}
	}
	
	private void calculatePercent() {
		equal();
		
		String str = output.getText();
		Double d = Double.valueOf(str);
		Double result = d / 100;
		
		output.setText(result.toString());
	}
	
	private void pressOperator(String ops) {
		String str1 = temp.getText();
		String str2 = output.getText();
		
		clear();
		
		if(str1.isEmpty()) {
			temp.setText(String.format("%s %s", str2, ops));
		} else {
			String[] arr = str1.split(" ");
			String result = calculate(arr[0], str2, arr[1]);
			temp.setText(String.format("%s %s", result, ops));
		}
	}
	
	private String calculate(String str1, String str2, String ops) {
		Double d1 = Double.valueOf(str1);
		Double d2 = Double.valueOf(str2);
		Double d3 = 0d;
		
		switch(ops) {
		case "+":
			d3 = d1 + d2;
			break;
		case "-":
			d3 = d1 - d2;
			break;
		case "*":
			d3 = d1 * d2;
			break;
		case "÷":
			d3 = d1 / d2;
			break;
		}
		
		return d3.toString();
	}
	
	private void pressNumber(String value) {
		String text = output.getText();
		if(text.equals("0")) {
			output.setText(value);
		} else {
			output.setText(text.concat(value));
		}
	}
	
	private void clear() {
		output.setText("0");
		temp.setText("");
	}
	
}
