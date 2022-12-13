package com.pos.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.pos.dao.MemberDao;
import com.pos.entity.Member;
import com.pos.entity.Role;
import com.pos.util.ApplicationException;
import com.pos.util.MessageView;
import com.pos.util.Security;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Login implements Initializable{

    @FXML
    private TextField loginID;

    @FXML
    private PasswordField password;
    
    private MemberDao dao;

    @FXML
    void close() {
    	Platform.exit();
    }

    @FXML
    void login() {
    	try {
    		Member member = dao.login(loginID.getText(), password.getText());
        	
        	if(null == member)
        		throw new ApplicationException("Member not found");
        	
        	Security.setLoginMember(member);
        	
        	if(member.getRole() == Role.MANAGER)
        		ManagerHome.showView();
        	else
        		SaleHome.showView();
        	
        	loginID.getScene().getWindow().hide();
        	
		} catch (ApplicationException e) {
			MessageView.showMessage(AlertType.ERROR, e.getMessage());
		}
    }
    
    @FXML
    void pressEnter(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER)
    		login();
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = MemberDao.getInstance();
	}

}
