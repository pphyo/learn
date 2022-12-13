package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.pos.dao.CategoryDao;
import com.pos.entity.Category;
import com.pos.entity.Item;
import com.pos.util.CommonUtil;
import com.pos.util.MessageView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ItemEdit implements Initializable{

	@FXML
	private Label title;

	@FXML
	private ComboBox<Category> category;

	@FXML
	private TextField itemName;

	@FXML
	private TextField price;

	@FXML
	private CheckBox soldOut;
	
	private CategoryDao catDao;
	private Item item;
	private Consumer<Item> saveHandler;
	
	@FXML
	void close() {
		itemName.getScene().getWindow().hide();
	}

	@FXML
	void save() {
		try {
			createItemFromViewData();
			saveHandler.accept(item);
			close();
		} catch (Exception e) {
			MessageView.showMessage(AlertType.ERROR, e.getMessage());
		}
	}
	
	public static void showView(Item item, Consumer<Item> handler) {
		try {
			FXMLLoader loader = new FXMLLoader(ItemEdit.class.getResource("ItemEdit.fxml"));
			Parent root = loader.load();
			ItemEdit controller = loader.getController();
			boolean isNew = null == item;
			
			controller.item = item;
			controller.saveHandler = handler;
			controller.title.setText( isNew ? "ADD NEW ITEM" : "UPDATE ITEM");
			
			if(!isNew)
				controller.setDataToView();
			
			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		catDao = CategoryDao.getInstance();
		category.getItems().addAll(catDao.search(null));
	}

	private void setDataToView() {
		category.setValue(item.getCategory());
		itemName.setText(item.getName());
		price.setText(String.valueOf(item.getPrice()));
		soldOut.setSelected(item.isDelete());
	}
	
	private void createItemFromViewData() {
		if(null == item) {
			item = new Item();
		}
		
		item.setCategory(category.getValue());
		item.setName(itemName.getText());
		item.setPrice(CommonUtil.convertStringToInt(price.getText()));
		item.setDelete(soldOut.isSelected());
	}
}
