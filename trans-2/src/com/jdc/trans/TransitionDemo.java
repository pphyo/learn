package com.jdc.trans;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;

import java.util.ArrayList;
import java.util.List;

import javafx.animation.Animation.Status;
import javafx.animation.FadeTransition;
import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class TransitionDemo {

	@FXML
	private Rectangle node;
	@FXML
	private VBox box;
	
	private Transition trans;
	
	private Duration duration = Duration.millis(500);
	
	public void parallel() {
		Transition[] list = getTransitionList();
		
		if(list.length > 0) {
			trans = new ParallelTransition(list);
			trans.setAutoReverse(false);
			trans.setCycleCount(Transition.INDEFINITE);
			trans.play();
		}
	}
	

	public void sequential() {
		Transition[] list = getTransitionList();
		if(list.length > 0) {
			trans = new SequentialTransition(list);
			trans.setAutoReverse(false);
			trans.setCycleCount(Transition.INDEFINITE);
			trans.play();
		}
	}
	
	public void stop() {
		if(null != trans && trans.getStatus() == Status.RUNNING) {
			trans.stop();
		}
	}

	private Transition[] getTransitionList() {
		
		List<Transition> list = new ArrayList<>();
		
		for(Node node : box.getChildren()) {
			
			if(node instanceof CheckBox) {
				
				CheckBox check = (CheckBox) node;
				
				if(check.isSelected()) {
					list.add(getTrans(check.getText()));
				}
			}
			
		}
		
		return list.toArray(new Transition[list.size()]);
	}


	private Transition getTrans(String type) {
		
		Transition trans = null;
		
		switch (type) {
		case "Rotate":
			trans = getRotate();
			break;
		case "Scale":
			trans = getScale();
			break;
		case "Translate":
			trans = getTranslateX();
			break;
		case "Fill":
			trans = getFill();
			break;

		default:
			trans = getFade();
			break;
		}
		
		return trans;
	}


	private Transition getFade() {
		FadeTransition trans = new FadeTransition(duration, node);
		trans.setFromValue(0.3);
		trans.setToValue(1.0);

		trans.setAutoReverse(false);
		trans.setCycleCount(1);
		return trans;
	}


	private Transition getTranslateX() {
		TranslateTransition trans = new TranslateTransition(duration, node);
		trans.setFromX(-100);
		trans.setToX(100);
		
		trans.setAutoReverse(false);
		trans.setCycleCount(1);
		return trans;
	}


	private Transition getFill() {
		
		FillTransition trans = new FillTransition(duration, node);
		trans.setFromValue(Color.BLUE);
		trans.setToValue(Color.RED);
		
		trans.setAutoReverse(false);
		trans.setCycleCount(1);
		return trans;
	}


	private Transition getScale() {
		
		ScaleTransition trans = new ScaleTransition(duration, node);
		
		trans.setFromX(1);
		trans.setToX(2);
		trans.setFromY(1);
		trans.setToY(2);
		
		trans.setAutoReverse(false);
		trans.setCycleCount(1);
		return trans;
	}


	private Transition getRotate() {
		
		RotateTransition trans = new RotateTransition(duration , node);
		trans.setFromAngle(0);
		trans.setToAngle(360);
		trans.setAutoReverse(false);
		trans.setCycleCount(1);
		return trans;
	}
}
