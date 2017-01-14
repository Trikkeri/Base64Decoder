package com.trg.Base64Decoder;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					new View();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
