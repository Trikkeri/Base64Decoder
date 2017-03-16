package com.trg.Base64Decoder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.swing.Timer;
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
	}
	
	private void decode() {
		if(view.getTransformTextAreaText().length() == 0) {
			return;
		}
		
		boolean errorsDuringDecoding = false;
		String decodedValueWithPrettyPrint = "";
		String decodedValueWithoutPrettyPrint = "";
		String returnValue = "";
		
		try {
			decodedValueWithoutPrettyPrint = model.decodeBase64(view.getTransformTextAreaText(), false);
			decodedValueWithPrettyPrint = model.decodeBase64(view.getTransformTextAreaText(), true);
		} catch (XPathExpressionException | ParserConfigurationException
				| TransformerException | IOException e) {
			view.displayErrorMessage(e);
			errorsDuringDecoding = true;
		} catch (SAXException e) {
			setErrorLabelText("<html>Decoded value is not xml or is not<br>wellformed, pretty print canceled</html>");
			errorsDuringDecoding = true;
		} finally {
			if(errorsDuringDecoding) {
				returnValue = decodedValueWithoutPrettyPrint;
			} else {
				returnValue = decodedValueWithPrettyPrint;
			}
			
			view.setTransfromTextAreaText(returnValue);
			
			if(view.getCopyToCBCheckbox().isSelected()) {
				ClipboardCopy c = new ClipboardCopy();
				c.copyToClipBoard(returnValue);
			}
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
	
	private void setErrorLabelText(String string) {
		view.getErrorLabel().setText(string);
		
		ActionListener taskPerformer = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				view.getErrorLabel().setText(" ");
			}
		};
		Timer t = new Timer(5000, taskPerformer); 
		t.setRepeats(false);
		t.start();
	}
}
