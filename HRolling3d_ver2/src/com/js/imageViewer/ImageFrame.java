package com.js.imageViewer;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

public class ImageFrame extends JInternalFrame {

	private JLabel imageLabel;
	private JScrollPane jScrollPanel;
	
	public ImageFrame(String imageName){
		initComponents();
		setTitle(imageName);
		imageLabel.setIcon(new ImageIcon(imageName));
	}
	
	
	private void initComponents() {
		// TODO Auto-generated method stub
		jScrollPanel = new JScrollPane();
		imageLabel = new JLabel();
		
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		getAccessibleContext().setAccessibleName("Image Internal Frame");
		getAccessibleContext().setAccessibleDescription("Image interal frame.");
		jScrollPanel.setViewportView(imageLabel);
		imageLabel.getAccessibleContext().setAccessibleName("Image Label");
		imageLabel.getAccessibleContext().setAccessibleDescription("Image label.");
		
		getContentPane().add(jScrollPanel,BorderLayout.CENTER);
		
	}

}
