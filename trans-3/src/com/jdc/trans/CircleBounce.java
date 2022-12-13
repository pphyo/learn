package com.jdc.trans;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.util.Duration;

public class CircleBounce {
	
	@FXML
	private Circle circle;
	@FXML
	private Line verti;
	@FXML
	private Line horizon;
	
	private TranslateTransition trans;
	
	public void play() {
		trans = new TranslateTransition(new Duration(500), circle);
		trans.setFromX(0);
		trans.setToX(-400);
		trans.setFromY(0);
		trans.setToY(-200);
		trans.setAutoReverse(true);
		trans.setCycleCount(Transition.INDEFINITE);
		trans.play();
	}
	
	public void stop() {
		trans.stop();
	}
	
}
