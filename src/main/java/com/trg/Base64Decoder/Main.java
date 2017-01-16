package com.trg.Base64Decoder;

import javax.swing.SwingUtilities;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				try {
					Model m = new Model();
					View v = new View();
					Controller c = new Controller(m, v);
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
