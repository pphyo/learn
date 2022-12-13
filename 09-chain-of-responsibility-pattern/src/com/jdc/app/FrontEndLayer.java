package com.jdc.app;

public class FrontEndLayer implements IHelpContent {

	private static final int FRONTEND_LAYER = 1;
	private IHelpContent content;

	public FrontEndLayer(IHelpContent content) {
		super();
		this.content = content;
	}

	@Override
	public void help(int i) {
		if(i == FRONTEND_LAYER)
			System.out.println("This is Front End Layer.");
		else
			content.help(i);
	}

}
