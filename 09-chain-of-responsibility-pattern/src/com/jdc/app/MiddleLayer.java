package com.jdc.app;

public class MiddleLayer implements IHelpContent {
	
	private static final int MIDDLE_LAYER = 2;
	private IHelpContent content;
	
	public MiddleLayer(IHelpContent content) {
		this.content = content;
	}

	@Override
	public void help(int i) {
		if(i == MIDDLE_LAYER)
			System.out.println("This is Middle Layer.");
		else
			content.help(i);
	}

}
