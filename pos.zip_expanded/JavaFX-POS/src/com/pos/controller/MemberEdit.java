package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Consumer;

import com.pos.entity.Member;
import com.pos.entity.Role;
import com.pos.util.ApplicationException;
import com.pos.util.MessageView;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MemberEdit implements Initializable {

	@FXML
	private Label title;

	@FXML
	private TextField loginName;

	@FXML
	private TextField userName;

	@FXML
	private PasswordField password;

	@FXML
	private ComboBox<Role> role;

	@FXML
	private TextField phone;

	private Consumer<Member> saveHandler;
	private Member member;

	@FXML
	void close() {
		title.getScene().getWindow().hide();
	}

	@FXML
	void save() {
		try {
			createMemberFromViewData();
			saveHandler.accept(member);
			close();
		} catch (ApplicationException e) {
			MessageView.showMessage(AlertType.ERROR, e.getMessage());
		}
	}

	public static void showView(Member member, Consumer<Member> saveHandler) {
		try {
			FXMLLoader loader = new FXMLLoader(MemberEdit.class.getResource("MemberEdit.fxml"));
			Parent root = loader.load();
			MemberEdit controller = loader.getController();
			controller.member = member;
			controller.saveHandler = saveHandler;
			
			controller.title.setText(controller.member == null ? "ADD NEW MEMBER" : "UPDATE MEMBER");
			
			if (null != member) {
				controller.loginName.setEditable(false);
				controller.setDataToView();
			}

			Stage stage = new Stage(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.setScene(new Scene(root));
			stage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setDataToView() {
		loginName.setText(member.getLoginID());
		userName.setText(member.getUsername());
		password.setText(member.getPassword());
		role.setValue(member.getRole());
		phone.setText(member.getPhone());
	}

	private void createMemberFromViewData() {
		if(null == member) {
			member = new Member();
		}
		member.setLoginID(loginName.getText());
		member.setUsername(userName.getText());
		member.setPassword(password.getText());
		member.setRole(role.getValue());
		member.setPhone(phone.getText());
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		role.getItems().addAll(Role.values());
		role.setValue(Role.SALE);
	}

}
