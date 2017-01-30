package com.trg.Base64Decoder;

public class Controller {
	
	private View view;
	private Model model;
	
	public Controller(Model m, View v) {
		view = v;
		model = m;
	}
	
	public void initializeView() {
		view.getCopyToCBCheckbox().setSelected(true);
	}
	
	public void initializeController() {
		view.getDecodeButton().addActionListener(e -> decode());
		view.getEncodeButton().addActionListener(e -> encode());
	}
	
	private void decode() {
		if(view.getTransformTextAreaText().length() == 0) {
			return;
		}
		
		String decodedValue = model.decodeBase64(view.getTransformTextAreaText());
		view.setTransfromTextAreaText(decodedValue);
		
		if(view.getCopyToCBCheckbox().isSelected()) {
			ClipboardCopy c = new ClipboardCopy();
			c.copyToClipBoard(decodedValue);
		}
	}
	
	private void encode() {
		if(view.getTransformTextAreaText().length() == 0) {
			return;
		}
		
		String encodedValue = model.encode2Base64(view.getTransformTextAreaText());
		view.setTransfromTextAreaText(encodedValue);
		
		if(view.getCopyToCBCheckbox().isSelected()) {
			ClipboardCopy c = new ClipboardCopy();
			c.copyToClipBoard(encodedValue);
		}
	}
}
