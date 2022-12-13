package com.pos.controller;

import java.net.URL;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import com.pos.dao.CategoryDao;
import com.pos.dao.ItemDao;
import com.pos.entity.Category;
import com.pos.entity.Item;
import com.pos.excel.ExcelExportService;
import com.pos.excel.ExcelFileReader;
import com.pos.util.CommonUtil;
import com.pos.util.MessageView;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ItemList implements Initializable {

	@FXML
	private ComboBox<Category> category;

	@FXML
	private TextField item;

	@FXML
	private TextField priceFrom;

	@FXML
	private TextField priceTo;

	@FXML
	private TableView<Item> itemTable;

	private CategoryDao catDao;
	private ItemDao itemDao;

	@FXML
	void addNew() {
		ItemEdit.showView(null, this::accept);
	}

	@FXML
	void upload() {
		FileChooser chooser = new FileChooser();
		chooser.getExtensionFilters().add(new ExtensionFilter("Text/Excel Only", "*.txt", "*.xlsx"));
	
		Optional.of(chooser.showOpenDialog(item.getScene().getWindow())).ifPresent(file -> {
			try {
				List<String> lines = Collections.emptyList();
				
				if (file.getName().endsWith(".txt"))
					lines = Files.readAllLines(file.toPath());
				else
					lines = new ExcelFileReader(file).readAllLines();
				
				lines.stream().skip(1).map(str -> str.split("\t"))
				.filter(arr -> arr.length == 3)
				.map(Item::new)
				.forEach(itemDao::save);
				
				search();
				
			} catch (Exception e) {
				e.printStackTrace();
				MessageView.showMessage(AlertType.ERROR, "File upload fail");
			}
		});
	}

	@FXML
	void reset() {
		category.setValue(null);
		item.clear();
		priceFrom.clear();
		priceTo.clear();
	}

	public void accept(Item t) {
		itemDao.save(t);
		search();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		catDao = CategoryDao.getInstance();
		itemDao = ItemDao.getInstance();
		category.getItems().addAll(catDao.search(null));
		itemTable.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

		MenuItem edit = new MenuItem("EDIT");
		MenuItem export = new MenuItem("Export As Excel");
		edit.setOnAction(e -> ItemEdit.showView(itemTable.getSelectionModel().getSelectedItem(), this::accept));
		export.setOnAction(e -> export());

		itemTable.setContextMenu(new ContextMenu(edit, export));

		category.valueProperty().addListener((a, b, c) -> search());
		item.textProperty().addListener((a, b, c) -> search());
		priceFrom.textProperty().addListener((a, b, c) -> search());
		priceTo.textProperty().addListener((a, b, c) -> search());
		search();
	}

	private void export() {
		List<Item> items = itemTable.getSelectionModel().getSelectedItems();

		if (!items.isEmpty()) {
			DirectoryChooser chooser = new DirectoryChooser();
			Optional.of(chooser.showDialog(itemTable.getScene().getWindow())).ifPresent(file -> {
				ExcelExportService<Item> service = new ExcelExportService<>(items, file, "ItemList", Item.class);
				service.setOnFailed(e -> MessageView.showMessage(AlertType.ERROR, service.getException().getMessage()));
				service.setOnSucceeded(e -> MessageView.showMessage(AlertType.INFORMATION, "Export process success"));
				service.start();
			});
		}
	}

	private void search() {
		ItemSearchService service = new ItemSearchService();

		service.setOnSucceeded(e -> {
			itemTable.getItems().clear();
			itemTable.getItems().addAll(service.getValue());
		});

		service.setOnFailed(e -> MessageView.showMessage(AlertType.ERROR, service.getException().getMessage()));

		service.start();
	}

	private class ItemSearchService extends Service<List<Item>> {
		@Override
		protected Task<List<Item>> createTask() {
			return new Task<List<Item>>() {
				@Override
				protected List<Item> call() throws Exception {
					return itemDao.search(category.getValue(), item.getText(),
							CommonUtil.convertStringToInt(priceFrom.getText()),
							CommonUtil.convertStringToInt(priceTo.getText()));
				}
			};
		}

	}
}
