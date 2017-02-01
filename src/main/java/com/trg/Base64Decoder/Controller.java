package com.trg.Base64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

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
		
		// Testing
		
		try {
			throw new NullPointerException();
		} catch (NullPointerException e) {
			view.displayErrorMessage(e);
		}
		
	}
	
	private void decode() {
		if(view.getTransformTextAreaText().length() == 0) {
			return;
		}
		
		String decodedValue = "";
		try {
			decodedValue = model.decodeBase64(view.getTransformTextAreaText());
		} catch (XPathExpressionException | SAXException | IOException | ParserConfigurationException
				| TransformerException e) {
			view.displayErrorMessage(e);
		}
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
		
		String encodedValue = "";
		try {
			encodedValue = model.encode2Base64(view.getTransformTextAreaText());
		} catch (UnsupportedEncodingException e) {
			view.displayErrorMessage(e);
		}
		view.setTransfromTextAreaText(encodedValue);
		
		if(view.getCopyToCBCheckbox().isSelected()) {
			ClipboardCopy c = new ClipboardCopy();
			c.copyToClipBoard(encodedValue);
		}
	}
}
