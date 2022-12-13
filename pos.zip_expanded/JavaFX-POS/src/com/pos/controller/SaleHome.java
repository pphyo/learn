package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pos.util.Reloader;
import com.pos.util.Security;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SaleHome implements Initializable {

	@FXML
	private HBox controlBox;

	@FXML
	private Label username;

	@FXML
	private HBox pos;

	@FXML
	private HBox report;

	@FXML
	private HBox close;

	@FXML
	private StackPane contentView;

	private Parent posView;
	private Parent reportView;
	
	private Reloader reloader;

	@FXML
	void showPos() {
		controlBox.getChildren().remove(pos);
		controlBox.getChildren().add(1, report);
		loadView(posView);
	}

	@FXML
	void showReport() {
		controlBox.getChildren().remove(report);
		controlBox.getChildren().add(1, pos);
		loadView(reportView);
		reloader.reload();
	}

	private void loadView(Parent view) {
		contentView.getChildren().clear();
		contentView.getChildren().add(view);
	}

	public static void showView() {
		try {
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(new Scene(FXMLLoader.load(SaleHome.class.getResource("SaleHome.fxml"))));
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		try {
			controlBox.getChildren().removeAll(pos, report);
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource("SaleReport.fxml"));
			reportView = loader.load();
			reloader = loader.getController();
		
			posView = FXMLLoader.load(getClass().getResource("SalePos.fxml"));
			
			username.setText(Security.getLoginMember().getUsername());
			showPos();
			close.setOnMouseClicked(e -> username.getScene().getWindow().hide());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
