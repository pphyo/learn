package com.jdc.trans;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.Animation.Status;
import javafx.animation.ScaleTransition;
import javafx.animation.Transition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TransitionDemo implements Initializable{

	@FXML
	private Rectangle node;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		ScaleTransition trans = new ScaleTransition(Duration.millis(500), node);
		trans.setFromX(1);
		trans.setToX(2);
		trans.setFromY(1);
		trans.setToY(2);
		
		trans.setAutoReverse(true);
		trans.setCycleCount(Transition.INDEFINITE);
		
		node.setOnMouseClicked(e -> {
			
			if(trans.getStatus() == Status.RUNNING)  {
				trans.pause();
			} else {
				trans.play();
			}
		});
	}
	
}
