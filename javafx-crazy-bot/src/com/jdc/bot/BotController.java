package com.jdc.bot;

import java.net.URL;
import java.util.ResourceBundle;

import com.jdc.dic.CrazyBot;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;

public class BotController implements Initializable {
	
	@FXML
	private TextField input;
	@FXML
	private ListView<String> list;
	
	private CrazyBot bot;
	
	public BotController() {
		bot = new CrazyBot();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		input.setOnKeyPressed(e -> {
			if(e.getCode() == KeyCode.ENTER)
				ask();
			if(e.getCode() == KeyCode.ESCAPE)
				Platform.exit();
		});
	}
	
	public void ask() {
		if(!input.getText().isEmpty()) {
			String question = input.getText();
			String answer = bot.ask(question);
			
			list.getItems().add(question);
			list.getItems().add(answer);
			list.getItems().add("");
			
			input.clear();
		} else {
			list.getItems().add("You didn't enter any word!");
			list.getItems().add("");
		}
	}
	
}
