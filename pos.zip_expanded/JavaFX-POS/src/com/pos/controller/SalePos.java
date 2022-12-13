package com.pos.controller;

import static com.pos.util.CommonUtil.DECIMAL_FORMAT;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import com.pos.dao.CategoryDao;
import com.pos.dao.InvoiceDao;
import com.pos.dao.ItemDao;
import com.pos.entity.Category;
import com.pos.entity.Invoice;
import com.pos.entity.Item;
import com.pos.entity.SaleOrder;
import com.pos.util.CommonUtil;
import com.pos.util.Security;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

public class SalePos implements Initializable{

    @FXML
    private ComboBox<Category> category;

    @FXML
    private TextField item;
    
    @FXML
    private TextField priceFrom;
    
    @FXML
    private TextField priceTo;

    @FXML
    private TilePane itemView;

    @FXML
    private TableView<SaleOrder> orderTable;

    @FXML
    private Label subTotal;

    @FXML
    private Label tax;

    @FXML
    private Label total;
    
    @FXML
    private HBox queue;
    
    private CategoryDao categoryDao;
    private ItemDao itemDao;
    private InvoiceDao invoiceDao;
    
    private List<List<SaleOrder>> carts;
    private int index;
    
    @FXML
    void createCart() {
    	carts.add(new LinkedList<>());
    	index = carts.size() - 1;
    	showVoucher(0, 0, 0);
    	initQueue();
    }
    
    @FXML
    void deleteCart() {
    	carts.remove(index);
    	index = carts.size() - 1;
    	
    	if(index < 0) {
    		createCart();
    	}else {
    		initQueue();
    	}
    }

    @FXML
    void paid() {
    	List<SaleOrder> saleOrder = carts.get(index);
    	
    	if(null != saleOrder && saleOrder.size() > 0) {
    		Invoice invoice = new Invoice();
        	invoice.setInvoiceDate(LocalDateTime.now());
        	invoice.setMember(Security.getLoginMember());
        	invoice.setSubTotal(Integer.parseInt(subTotal.getText().replace(",", "")));
        	invoice.setTax(Integer.parseInt(tax.getText().replace(",", "")));
        	invoice.setTotal(Integer.parseInt(total.getText().replace(",", "")));
        	
        	invoiceDao.create(invoice, saleOrder);
        	carts.remove(index);
        	index = carts.size() - 1;
        	
        	if(index < 0) {
        		createCart();
        	}else {
        		initQueue();
        	}
    	}
    }

    @FXML
    void reset() {
    	category.setValue(null);
    	item.clear();
    	priceFrom.clear();
    	priceTo.clear();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		categoryDao = CategoryDao.getInstance();
		itemDao = ItemDao.getInstance();
		invoiceDao = InvoiceDao.getInstance();
		
		carts = new ArrayList<List<SaleOrder>>();
		carts.add(new ArrayList<>());
		
		category.getItems().addAll(categoryDao.search(null));
		
		category.valueProperty().addListener((a, b, c) -> search());
		item.textProperty().addListener((a, b, c) -> search());
		priceFrom.textProperty().addListener((a, b, c) -> search());
		priceTo.textProperty().addListener((a, b, c) -> search());
		search();
		initQueue();
	}
	
	private void search() {
		List<Item> items = itemDao.search(category.getValue(), item.getText(), 
				CommonUtil.convertStringToInt(priceFrom.getText()), CommonUtil.convertStringToInt(priceTo.getText()));
	
		itemView.getChildren().clear();
		items.stream().map(SaleItem::new).forEach(itemView.getChildren()::add);
	}
	
	private class SaleItem extends HBox{
		Item item;
		SaleItem(Item item){
			this.item = item;
			VBox vBox = new VBox(5);
			Label cat = new Label(item.getCategory().getName());
			cat.setWrapText(true);
			Label name = new Label(item.getName());
			name.setWrapText(true);
			
			HBox hbox = new HBox(5);
			Label priceLbl = new Label("Price : ");
			Label price = new Label(item.getPrice() + " Ks");
			hbox.getChildren().addAll(priceLbl, price);
			
			vBox.getChildren().addAll(cat, name, hbox);
			vBox.getStyleClass().add("left");
			
			VBox actionBtns = new VBox(5);
			Button addBtn = new Button("+");
			addBtn.setOnAction(this::addItem);
			
			Button removeBtn = new Button("-");
			removeBtn.setOnAction(this::removeItem);
			
			actionBtns.getStyleClass().add("right");
			actionBtns.getChildren().addAll(addBtn, removeBtn);
			
			getChildren().addAll(vBox, actionBtns);
			getStyleClass().add("item");
		}
		
		void addItem(ActionEvent event) {
			SaleOrder saleOrder = carts.get(index).stream().filter(od -> od.getItem().getId() == item.getId())
			.findFirst().orElse(null);
			
			if(null == saleOrder) {
				saleOrder = new SaleOrder();
				carts.get(index).add(saleOrder);
				saleOrder.setItem(item);
				saleOrder.setUnitPrice(item.getPrice());
				saleOrder.setQuantity(1);
			}else {
				saleOrder.setQuantity(saleOrder.getQuantity() + 1);
			}
			
			saleOrder.setTotal(saleOrder.getQuantity() * saleOrder.getUnitPrice());
			loadTable(carts.get(index));
			orderTable.refresh();
		}
		
		void removeItem(ActionEvent event) {
			SaleOrder saleOrder = carts.get(index).stream().filter(od -> od.getItem().getId() == item.getId())
					.findFirst().orElse(null);
			
			if(null != saleOrder) {
				if(saleOrder.getQuantity() > 1) {
					saleOrder.setQuantity(saleOrder.getQuantity() - 1);
					saleOrder.setTotal(saleOrder.getQuantity() * saleOrder.getUnitPrice());
				}else {
					carts.get(index).remove(saleOrder);
				}
				
				loadTable(carts.get(index));
			}
		}
	}
	

	private void initQueue() {
		queue.getChildren().clear();
		
		for (int i = 0; i < carts.size(); i++) {
			Label lbl = new Label(String.valueOf(i + 1));
			
			lbl.setOnMouseClicked(e -> {
				Label cartNo = (Label) e.getSource();
				int currentNo = Integer.parseInt(cartNo.getText()) - 1;
				index = currentNo;
				List<SaleOrder> list = carts.get(currentNo);
				loadTable(list);
				initQueue();
			});
			
			if(i == index) {
				loadTable(carts.get(index));
				lbl.getStyleClass().add("cartNo");
			}
			
			queue.getChildren().add(lbl);
		}
	}
	
	private void loadTable(List<SaleOrder> list) {
		orderTable.getItems().clear();
		orderTable.getItems().addAll(list);
		
		int subTotal = list.stream().mapToInt(od -> od.getQuantity() * od.getUnitPrice()).sum();
		Double tax = subTotal * 0.05;
		int total = tax.intValue() + subTotal;
		
		showVoucher(subTotal, tax.intValue(), total);
	}
	
	private void showVoucher(int subTotal, int tax, int total) {
		this.subTotal.setText(DECIMAL_FORMAT.format(subTotal));
		this.tax.setText(DECIMAL_FORMAT.format(tax));
		this.total.setText(DECIMAL_FORMAT.format(total));
	}
}
