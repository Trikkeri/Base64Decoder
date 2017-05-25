package com.trg.Base64Decoder;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
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
	private JCheckBox copyToCBCheckbox;
	private JLabel errorLabel;
	
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
        transformTextArea.setFont(transformTextArea.getFont().deriveFont(12f));
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
		decodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlsPanel.add(decodeBtn);
		
		controlsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
		encodeBtn = new JButton("Encode");
		encodeBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
		controlsPanel.add(encodeBtn);
		
		copyToCBCheckbox = new JCheckBox("<html>Copy to clipboard</html>");
		copyToCBCheckbox.setAlignmentX(Component.CENTER_ALIGNMENT);
		copyToCBCheckbox.setHorizontalAlignment(SwingConstants.CENTER);
		copyToCBCheckbox.setToolTipText("Decoded / Encoded value is automatically copied to clipboard after processing");
		controlsPanel.add(copyToCBCheckbox);
		
		// Error labels takes too much space, figure another solution
		
//		controlsPanel.add(Box.createRigidArea(new Dimension(0, 5)));
		
//		errorLabel = new JLabel(" ");
//		errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
//		errorLabel.setMinimumSize(new Dimension(275, 35));
//		errorLabel.setPreferredSize(new Dimension(275, 35));
//		errorLabel.setHorizontalAlignment(SwingConstants.CENTER);
//		errorLabel.setForeground(Color.red);
//		controlsPanel.add(errorLabel);
		
		frame.pack(); 
	}
	
	public String getTransformTextAreaText() {
		return transformTextArea.getText();
	}
	
	public void setTransfromTextAreaText(String str) {
		transformTextArea.setText(str);
	}
	
	public JTextArea getTransfromTextArea() {
		return transformTextArea;
	}
	
	public JButton getDecodeButton() {
		return decodeBtn;
	}
	
	public JCheckBox getCopyToCBCheckbox() {
		return copyToCBCheckbox;
	}
	
	public JButton getEncodeButton() {
		return encodeBtn;
	}
	
	public void displayErrorMessage(Throwable exception, String message) {
		StringWriter errors = new StringWriter();
		JTextArea jta = new JTextArea();
		jta.setEditable(false);
		if(exception != null) {
			exception.printStackTrace(new PrintWriter(errors));
			jta.setText(errors.toString());
		} else {
			jta.setText(message.toString());
		}
		
		jta.setFont(jta.getFont().deriveFont(11f));
		@SuppressWarnings("serial")
		JScrollPane jsp = new JScrollPane(jta) {
			@Override
			public Dimension getPreferredSize() {
				return new Dimension(480, 320);
			}
		};
		JOptionPane.showMessageDialog(
				null, jsp, "Something has gone wrong", JOptionPane.ERROR_MESSAGE);
	}
	
	public JLabel getErrorLabel() {
		return errorLabel;
	}
}
