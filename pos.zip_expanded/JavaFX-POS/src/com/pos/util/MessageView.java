package com.pos.util;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MessageView {
	
	private static final String WARNING = "M48.777 44.95l-22.267-41.58c-0.31-0.537-0.885-0.87-1.51-0.87-0.627 0-1.202 0.333-1.51 0.87l-22.265 41.58c-0.302 0.527-0.298 1.177 0.012 1.7 0.312 0.527 0.88 0.85 1.495 0.85h44.535c0.612 0 1.185-0.323 1.495-0.85 0.31-0.523 0.315-1.173 0.015-1.7zM27.5 42.5h-5v-5h5v5zM27.5 33.75h-5v-16.25h5v16.25zM48.777 44.95l-22.267-41.58c-0.31-0.537-0.885-0.87-1.51-0.87-0.627 0-1.202 0.333-1.51 0.87l-22.265 41.58c-0.302 0.527-0.298 1.177 0.012 1.7 0.312 0.527 0.88 0.85 1.495 0.85h44.535c0.612 0 1.185-0.323 1.495-0.85 0.31-0.523 0.315-1.173 0.015-1.7zM27.5 42.5h-5v-5h5v5zM27.5 33.75h-5v-16.25h5v16.25z";
	private static final String SUCCESS = "M19.406 44.809l-19.427-19.121 9.638-9.486 9.79 9.635 20.978-20.647 9.638 9.486-30.615 30.133zM5.269 25.688l14.137 13.914 25.325-24.926-4.347-4.279-20.978 20.647-9.79-9.635-4.347 4.279z";
	private static final String ERROR = "M49.544 40.169c-0-0-0-0-0.001-0l-15.168-15.168 15.168-15.168c0-0 0-0 0.001-0 0.163-0.163 0.281-0.354 0.357-0.557 0.207-0.555 0.089-1.205-0.358-1.652l-7.165-7.165c-0.447-0.447-1.096-0.565-1.652-0.357-0.203 0.076-0.394 0.194-0.558 0.357 0 0-0 0-0 0l-15.168 15.169-15.168-15.168c-0-0-0-0-0-0-0.163-0.163-0.354-0.281-0.557-0.357-0.556-0.207-1.205-0.089-1.652 0.357l-7.165 7.165c-0.447 0.447-0.565 1.096-0.358 1.652 0.076 0.203 0.194 0.394 0.357 0.557 0 0 0 0 0 0l15.168 15.168-15.168 15.169c-0 0-0 0-0 0-0.163 0.163-0.281 0.354-0.357 0.557-0.207 0.555-0.089 1.205 0.358 1.652l7.165 7.165c0.447 0.447 1.096 0.565 1.652 0.358 0.203-0.076 0.394-0.194 0.557-0.357 0-0 0-0 0-0l15.168-15.168 15.168 15.168c0 0 0 0 0.001 0 0.163 0.163 0.354 0.281 0.557 0.357 0.556 0.207 1.205 0.089 1.652-0.358l7.165-7.165c0.447-0.447 0.565-1.096 0.358-1.652-0.076-0.203-0.194-0.394-0.357-0.557z";
	
	@FXML
	private SVGPath icon;
	@FXML
	private Label message;
	@FXML
	private SVGPath close;
	
	public static void showMessage(AlertType alert, String message) {
		try {
			FXMLLoader loader = new FXMLLoader(MessageView.class.getResource("MessageView.fxml"));
			Parent root = loader.load();
			MessageView controller = loader.getController();
			
			switch (alert) {
			case WARNING:
				controller.icon.setContent(WARNING);
				controller.icon.setFill(Color.rgb(252, 173, 45));
				break;

			case INFORMATION:
				controller.icon.setContent(SUCCESS);
				controller.icon.setFill(Color.rgb(88, 247, 19));
				break;
				
			case ERROR:
				controller.icon.setContent(ERROR);
				controller.icon.setFill(Color.rgb(255, 0, 0));
				break;
			default:
				break;
			}
			
			controller.message.setText(message);
			
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.setScene(new Scene(root));
			stage.show();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	void close() {
		message.getScene().getWindow().hide();
	}

}
