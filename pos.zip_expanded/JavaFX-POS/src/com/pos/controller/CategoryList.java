package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pos.dao.CategoryDao;
import com.pos.entity.Category;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CategoryList implements Initializable{

    @FXML
    private TableView<Category> categoryTable;

    @FXML
    private TextField categoryName;

    @FXML
    private Button reset;
    
    private CategoryDao dao;

    @FXML
    void addNew() {
    	CategoryEdit.showView(null, this::accept);
    }

	public void accept(Category t) {
		dao.save(t);
		search();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = CategoryDao.getInstance();
		
		reset.setOnAction(e -> categoryName.clear());
		
		categoryName.textProperty().addListener((a, b, c) -> search());
		
		MenuItem edit = new MenuItem("EDIT");
		edit.setOnAction(e -> CategoryEdit.showView(categoryTable.getSelectionModel().getSelectedItem(), this::accept));
		
		categoryTable.setContextMenu(new ContextMenu(edit));
		search();
	}
	
	private void search() {
		categoryTable.getItems().clear();
		categoryTable.getItems().addAll(dao.search(categoryName.getText()));
	}

}
