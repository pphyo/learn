package com.jdc.cacl;

import java.io.FileInputStream;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("Calculator.fxml"));
		stage.setScene(new Scene(root));
		stage.setTitle("Calculator");
		stage.getIcons().add(new Image(new FileInputStream("calculator.png")));
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
