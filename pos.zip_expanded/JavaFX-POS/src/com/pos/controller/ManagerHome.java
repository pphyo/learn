package com.pos.controller;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ManagerHome {

    @FXML
    private Label title;

    @FXML
    private StackPane contentView;

    @FXML
    void showHome() {
    	loadView("MANAGER DASHBOARD", "Home.fxml");
    }
    
    @FXML
    void showMember() {
    	loadView("MEMBER MANAGEMENT", "MemberList.fxml");
    }

    @FXML
    void showCategory() {
    	loadView("CATEGORY MANAGEMENT", "CategoryList.fxml");
    }

    @FXML
    void showItem() {
    	loadView("ITEM MANAGEMENT", "ItemList.fxml");
    }

    @FXML
    void showSale() {
    	loadView("INVOICE MANAGEMENT", "InvoiceList.fxml");
    }
    
	@FXML
    void showPos() {
		SaleHome.showView();
    }

    @FXML
    void close() {
    	Platform.exit();
    }
    
    private void loadView(String title, String viewName) {
    	try {
    		this.title.setText(title);
			Parent root = FXMLLoader.load(getClass().getResource(viewName));
			contentView.getChildren().clear();
			contentView.getChildren().add(root);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public static void showView() {
    	try {
    		FXMLLoader loader = new FXMLLoader(ManagerHome.class.getResource("ManagerHome.fxml"));
    		Parent root = loader.load();
    		ManagerHome controller = loader.getController();
        
    		Stage stage = new Stage();
    		stage.setScene(new Scene(root));
        	stage.show();
        	
        	controller.showHome();
        	
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
