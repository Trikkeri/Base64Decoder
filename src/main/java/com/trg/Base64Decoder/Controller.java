package com.trg.Base64Decoder;

public class Controller {
	
	private View view;
	private Model model;
	
	public Controller(Model m, View v) {
		view = v;
		model = m;
		
		initializeController();
	}
	
	public void initializeView() {
	}
	
	public void initializeController() {
		view.getDecodeButton().addActionListener(e -> decode());
	}
	
	private void decode() {
		view.setTransfromTextAreaText(model.decodeBase64(view.getTransformTextAreaText()));
	}
}
