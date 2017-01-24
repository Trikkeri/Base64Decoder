package com.trg.Base64Decoder;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class View {

	private JFrame frame;
	private JPanel panel;
	private JScrollPane jsp;
	private JTextArea transformTextArea;
	private JButton decodeBtn;
	private JButton encodeBtn;
	
	public View() {	
        frame = new JFrame("Base64 decoder / encoder");
        frame.setPreferredSize(new Dimension(1280, 1024));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
		GridBagLayout gridBagLayout = new GridBagLayout();
        frame.setLayout(gridBagLayout);
        
        GridBagConstraints gbc  = new GridBagConstraints();
        
        panel = new JPanel();
        panel.setLayout(gridBagLayout);

        // Set panel & textarea to fill whole frame
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets.set(5,5,5,5);
		panel.setBorder(new TitledBorder(null, "Base64 decoder / encoder", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(panel, gbc);
        
        transformTextArea = new JTextArea();
        jsp = new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setViewportView(transformTextArea);
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(jsp, gbc);

		decodeBtn = new JButton("Decode");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		gbc.fill = GridBagConstraints.NONE;
		panel.add(decodeBtn,gbc);
		
		encodeBtn = new JButton("Encode");
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(encodeBtn, gbc);
		
		frame.pack(); 
	}
	
	public String getTransformTextAreaText() {
		return transformTextArea.getText();
	}
	
	public void setTransfromTextAreaText(String str) {
		transformTextArea.setText(str);
	}
	
	public JButton getDecodeButton() {
		return decodeBtn;
	}
}
