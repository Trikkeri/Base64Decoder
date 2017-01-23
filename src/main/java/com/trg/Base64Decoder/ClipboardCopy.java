package com.trg.Base64Decoder;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ClipboardCopy {
	
	public boolean copyToClipBoard(String str) {
		StringSelection stringSelection = new StringSelection(str);
		Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
		clpbrd.setContents(stringSelection, null);

		//Check if copy was successful
		String clpbrdContents = "";
		try {
			clpbrdContents = (String) clpbrd.getData(DataFlavor.stringFlavor);
		} catch (UnsupportedFlavorException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(clpbrdContents.isEmpty()) {
			return false;
		}
		return true;
	}
}
