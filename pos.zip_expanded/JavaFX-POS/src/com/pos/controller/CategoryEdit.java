package com.pos.controller;

import java.util.function.Consumer;

import com.pos.entity.Category;
import com.pos.util.ApplicationException;
import com.pos.util.MessageView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CategoryEdit {

    @FXML
    private Label title;

    @FXML
    private TextField categoryName;
    
    private Consumer<Category> saveHandler;
    private Category category;

    @FXML
    void close() {
    	title.getScene().getWindow().hide();
    }

    @FXML
    void save() {
    	try {
			if(null == categoryName.getText() || categoryName.getText().isEmpty())
				throw new ApplicationException("Please fill category name");
			
			if(null == category)
				category = new Category();
			
			category.setName(categoryName.getText());
			saveHandler.accept(category);
			close();
		} catch (ApplicationException e) {
			MessageView.showMessage(AlertType.ERROR, e.getMessage());
		}
    }
    
    public static void showView(Category category, Consumer<Category> handler) {
    	try {
    		FXMLLoader loader = new FXMLLoader(CategoryEdit.class.getResource("CategoryEdit.fxml"));
        	Parent root = loader.load();
        	CategoryEdit controller = loader.getController();
        	controller.category = category;
        	controller.saveHandler = handler;
        	controller.title.setText(null == category ? "ADD NEW CATEGORY" : "UPDATE CATEGORY");
        	
        	if(null != category) {
        		controller.categoryName.setText(category.getName());
        	}
        	
        	Stage stage = new Stage(StageStyle.UNDECORATED);
        	stage.initModality(Modality.APPLICATION_MODAL);
        	stage.setScene(new Scene(root));
        	stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }

}
