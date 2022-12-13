package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pos.dao.MemberDao;
import com.pos.entity.Member;
import com.pos.entity.Role;
import com.pos.util.MemberSearchService;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class MemberList implements Initializable{

	@FXML
	private ComboBox<Role> role;

	@FXML
	private TextField username;

	@FXML
	private TableView<Member> memberTable;

	private MemberDao dao;

	@FXML
	void addNew() {
		MemberEdit.showView(null, this::accept);
	}

	public void accept(Member t) {
		dao.save(t);
		search();
	}
	
	@FXML
	void reset() {
		role.setValue(null);
		username.clear();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		role.getItems().addAll(Role.values());
		dao = MemberDao.getInstance();
		search();
		
		MenuItem edit = new MenuItem("EDIT");
		MenuItem delete = new MenuItem("DELETE");
		
		memberTable.setContextMenu(new ContextMenu(edit, delete));

		edit.setOnAction(e -> MemberEdit.showView(memberTable.getSelectionModel().getSelectedItem(), this::accept));
		
		delete.setOnAction(e -> {
			Member member = memberTable.getSelectionModel().getSelectedItem();
			member.setDelete(true);
			dao.delete(member);
			search();
		});
	
		role.valueProperty().addListener((a, b, c) -> search());
		username.textProperty().addListener((a, b, c) -> search());
	}

	private void search() {
		MemberSearchService service = new MemberSearchService(username.getText(), role.getValue());
		service.setOnSucceeded(e -> {
			memberTable.getItems().clear();
			memberTable.getItems().addAll(service.getValue());
		});
		service.start();
	}

}
