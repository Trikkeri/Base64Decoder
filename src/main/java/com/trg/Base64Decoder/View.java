package com.trg.Base64Decoder;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.TitledBorder;

public class View {

	private JFrame frame;
	private JPanel jPanel;
	private JScrollPane jsp;
	private JTextArea transformTextArea;
	private JButton decodeBtn;
	private JButton encodeBtn;
	private JPanel controlsPanel;
	private JCheckBox isXmlCheckbox;
	private JCheckBox copyToCBCheckbox;
	
	public View() {	
		
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
		
        frame = new JFrame("Base64 decoder / encoder");
        frame.setPreferredSize(new Dimension(1280, 1024));
        frame.setResizable(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
		GridBagLayout gridBagLayout = new GridBagLayout();
        frame.setLayout(gridBagLayout);
        
        jPanel = new JPanel();
        GridBagConstraints gbc  = new GridBagConstraints();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.X_AXIS));
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.insets.set(5,5,5,5);
		jPanel.setBorder(new TitledBorder(null, "Base64 decoder / encoder", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(jPanel, gbc);
        
        transformTextArea = new JTextArea();
        gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		gbc.insets.set(5,5,5,5);
		gbc.gridx = 0;
		gbc.gridy = 0;
        jsp = new JScrollPane();
        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsp.setViewportView(transformTextArea);
        jPanel.add(jsp, gbc);
        
        controlsPanel = new JPanel();
		gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.insets.set(10,10,10,10);
		controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
		frame.add(controlsPanel, gbc);
		
		decodeBtn = new JButton("Decode");
		controlsPanel.add(decodeBtn);
		
		controlsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		encodeBtn = new JButton("Encode");
		controlsPanel.add(encodeBtn);
		
		isXmlCheckbox = new JCheckBox("<html>Encoded value<br>contains xml</html>");
		isXmlCheckbox.setToolTipText("This should be deselected if ");
		controlsPanel.add(isXmlCheckbox);
		
		copyToCBCheckbox = new JCheckBox("<html>Copy processed<br>value to clipboard</html>");
		isXmlCheckbox.setToolTipText("Decoded / Encoded value is automatically copied to clipboard after processing");
		controlsPanel.add(copyToCBCheckbox);
		
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
