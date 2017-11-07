package com.js.imageViewer;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.JFileChooser;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;

import com.js.util.myUtil;


public class ImageViewer extends JFrame {
	
	private JDesktopPane desktop;
	private JMenuBar mainMenuBar;
	private JMenu fileMenu;
	private JMenuItem openMenuItem;
	private JSeparator jSeparator1;
	private JMenuItem exitMenuItem;
	private JFileChooser jFileChooser;
	private String iconPath;
	private File icon;
	private final String currentWorkingFolder;
	
	public ImageViewer(){
		try {
			if(myUtil.checkOS().equals("window")){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}else{
				//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		currentWorkingFolder = System.getProperty("user.dir");
		
		initComponents();
		pack();
		setBounds(100,100,1000,800);
	}
	
	public ImageViewer(String resultFolder){
		try {
			if(myUtil.checkOS().equals("window")){
				UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
			}else{
				//UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//iconPath =  System.getProperty("user.dir")+File.separator+"img"+File.separator+"icon.png";
		//icon = new File(iconPath);
		
		currentWorkingFolder = resultFolder;
		
		initComponents();
		pack();
		setBounds(100,100,1000,800);
		
		
	}
	
	private void initComponents() {
		// TODO Auto-generated method stub
		desktop = new JDesktopPane();
		mainMenuBar = new JMenuBar();
		fileMenu = new JMenu();
		openMenuItem = new JMenuItem();
		jSeparator1 = new JSeparator();
		exitMenuItem = new JMenuItem();
		
		setTitle("Image Viewer");
		
		/*
		try
        {
        // The read(), static method of ImageIO class
        // takes InputStream object pointing to the image file
        setIconImage(ImageIO.read(new FileInputStream(this.iconPath)));
        }catch(Exception e){
        	e.printStackTrace();
        }
		*/
		
		//setIconImage(new ImageIcon(this.iconPath).getImage());
														  
		//Image icon = Toolkit.getDefaultToolkit().getImage("D:\\Q\\new\\Workspace\\com.js\\img\\icon.png");
	    //setIconImage(icon);
		
		/*
		ImageIcon imageIcon = new ImageIcon(getClass().getResource("D:\\Q\\new\\Workspace\\com.js\\img\\icon.png"));
		Image image = imageIcon.getImage();
		this.setIconImage(image);
		*/
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				exitForm(e);
			}
		});
		
		this.getAccessibleContext().setAccessibleName("Image Viewer Frame");
		this.getContentPane().add(desktop, BorderLayout.CENTER);
		
		desktop.getAccessibleContext().setAccessibleName("Image Desktop");
		desktop.getAccessibleContext().setAccessibleDescription("Image desktop.");
		
		fileMenu.setMnemonic('f');
		fileMenu.setText("File");
		
		
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
		openMenuItem.setMnemonic('o');
		openMenuItem.setText("Open");
		openMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				openMenuItemActionPerformed(e);
			}
			
		});
		
		fileMenu.add(openMenuItem);
		openMenuItem.getAccessibleContext().setAccessibleName("Open Menu Item");
		openMenuItem.getAccessibleContext().setAccessibleDescription("Open menu item.");
		
		fileMenu.add(jSeparator1);
		
		exitMenuItem.setMnemonic('x');
		exitMenuItem.setText("Exit");
		exitMenuItem.addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				exitMenuItemActionPerformed(e);
			}
			
		});
		
		fileMenu.add(exitMenuItem);
		exitMenuItem.getAccessibleContext().setAccessibleName("Exit Menu Item");
		exitMenuItem.getAccessibleContext().setAccessibleDescription("Exit menu item.");
		
		mainMenuBar.add(fileMenu);
		fileMenu.getAccessibleContext().setAccessibleName("File Menu");
		fileMenu.getAccessibleContext().setAccessibleDescription("File menu.");
		
		setJMenuBar(mainMenuBar);
		mainMenuBar.getAccessibleContext().setAccessibleName("Main Menu Bar");
		mainMenuBar.getAccessibleContext().setAccessibleDescription("Main menu bar.");
		
		
	}
	
	
	protected void exitMenuItemActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		dispose();
	}

	protected void openMenuItemActionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//new File("D:\\Q\\new\\Workspace\\com.js\\src\\com\\js\\imageViewer\\image")
		jFileChooser = new JFileChooser(this.currentWorkingFolder);
		jFileChooser.setMultiSelectionEnabled(true);
		jFileChooser.addChoosableFileFilter(new ImageFileFilter());
		//jFileChooser.setCurrentDirectory(new java.io.File(this.currentWorkingFolder));
		
		
		int option = jFileChooser.showOpenDialog(this);
		if(option == JFileChooser.APPROVE_OPTION){
			//============================================================
			/* 
			// 1. Single 
			File f = jFileChooser.getSelectedFile();
			
			if(f == null){
				return;
			}
			ImageFrame ifr = new ImageFrame(f.getAbsolutePath());
			desktop.add(ifr,JLayeredPane.DEFAULT_LAYER);
			
			ifr.setVisible(true);
			ifr.setSize(200,200);
			ifr.setLocation(0,0);
			// */
			//============================================================
			/* */
			// 2. Multi
			File[] files = jFileChooser.getSelectedFiles();
			int internalFramePoint = 0;
			for(File file : files){
				if(file == null){
					return;
				}
				ImageFrame ifrs = new ImageFrame(file.getAbsolutePath());
				desktop.add(ifrs,JLayeredPane.DEFAULT_LAYER);
				
				ifrs.setVisible(true);
				ifrs.setSize(880, 560);
				ifrs.setLocation(internalFramePoint, internalFramePoint);
				internalFramePoint = internalFramePoint+5;
			}
			// */
			//============================================================
		}
		
	}

	protected void exitForm(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private static class ImageFileFilter extends FileFilter{
		public boolean accept(File file){
			if(file == null){
				return false;
			}else {
				return file.isDirectory() || file.getName().toLowerCase().endsWith(".gif") || file.getName().toLowerCase().endsWith(".jpg");
			}
		}
		
		public String getDescription(){
			return "Image files(*.gif, *.jpg)";
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//ImageViewer viewer = new ImageViewer();
		//viewer.setVisible(true);
	}
	
}
