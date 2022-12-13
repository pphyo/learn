package com.jdc.input;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class InputControl {

	@FXML
	private TextField tf;
	@FXML
	private Label tfLbl;
	@FXML
	private PasswordField pf;
	@FXML
	private Label pfLbl;
	@FXML
	private TextArea ta;
	@FXML
	private Label taLbl;
	@FXML
	private RadioButton male;
	@FXML
	private Label radiosLbl;
	@FXML
	private CheckBox read;
	@FXML
	private CheckBox code;
	@FXML
	private CheckBox swim;
	@FXML
	private Label checksLbl;
	@FXML
	private Slider slider;
	@FXML
	private Label sliderLbl;
	@FXML
	private Spinner<Integer> spinner;
	@FXML
	private Label spinnerLbl;
	
	@FXML
	private void initialize() {
		spinner.setValueFactory(new IntegerSpinnerValueFactory(0, 10));
		clear();
	}

	public void show() {
		if(!tf.getText().isEmpty())
			tfLbl.setText(tf.getText());
		if(!pf.getText().isEmpty())
			pfLbl.setText(pf.getText());
		if(!ta.getText().isEmpty())
			taLbl.setText(ta.getText());
		radiosLbl.setText(male.isSelected() ? "Male" : "Female");
		
		String result = "";
		if(read.isSelected())
			result += " " + read.getText();
		if(code.isSelected())
			result += " " + code.getText();
		if(swim.isSelected())
			result += " " + swim.getText();
		checksLbl.setText(result);
		sliderLbl.setText(String.valueOf((int)slider.getValue()));
		spinnerLbl.setText(String.valueOf(spinner.getValue()));
	}
	
	public void clear() {
		tf.clear(); tfLbl.setText("");
		pf.clear(); pfLbl.setText("");
		ta.clear(); taLbl.setText("");
		male.setSelected(true); radiosLbl.setText("");
		read.setSelected(false); code.setSelected(false); swim.setSelected(false);
		checksLbl.setText("");
		slider.setValue(0); sliderLbl.setText("");
		spinner.getValueFactory().setValue(0); spinnerLbl.setText("");
	}
	
	public void exit() {
		Platform.exit();
	}

}