package com.js.chartViewer;

import com.js.parser.ParserDefault;

import java.io.File;
import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.dnd.*;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.js.util.myUtil;

public class PlotViewer{

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			PlotViewer window = new PlotViewer("emptyHRolling-3d");
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private Display display;
	private Shell shell;
	
	private List list_resultFiles;
	private DropTarget target;
	
	private final TextTransfer textTransfer;
	private final FileTransfer fileTransfer;
	
	private ArrayList<String> resultFilesPathList;
	private ArrayList<Integer> selectedFilesIndexList;
	
	private String resultType;
	private String resultFolderPath = null;
	
	public PlotViewer(String workspace){
		this.resultFolderPath = workspace;
		
		this.resultFilesPathList = new ArrayList<String>();
		this.selectedFilesIndexList = new ArrayList<Integer>();
		
		
		this.display = Display.getDefault();
		this.shell = new Shell(this.display,SWT.CLOSE | SWT.TITLE);
		InitComponent();
		
		this.target = new DropTarget(this.list_resultFiles,DND.DROP_MOVE | DND.DROP_COPY | DND.DROP_DEFAULT);
		textTransfer = TextTransfer.getInstance();
		fileTransfer = FileTransfer.getInstance();
		Transfer[] types = new Transfer[] {fileTransfer, textTransfer};
		target.setTransfer(types);
		this.DNDListener();
	}
	
	public void InitComponent(){
		shell.setSize(314, 421);
		shell.setText("Post Viewer");
		shell.setLayout(new FormLayout());
		
		Label lbl_chartViewer = new Label(shell, SWT.NONE);
		FormData fd_lbl_chartViewer = new FormData();
		fd_lbl_chartViewer.top = new FormAttachment(0, 10);
		fd_lbl_chartViewer.left = new FormAttachment(0, 10);
		lbl_chartViewer.setLayoutData(fd_lbl_chartViewer);
		lbl_chartViewer.setText("* Plot Viewer");
		
		Label lbl_description = new Label(shell, SWT.NONE);
		FormData fd_lbl_description = new FormData();
		fd_lbl_description.top = new FormAttachment(lbl_chartViewer, 5);
		fd_lbl_description.left = new FormAttachment(lbl_chartViewer, 10, SWT.LEFT);
		lbl_description.setLayoutData(fd_lbl_description);
		lbl_description.setText("Select result files.");
		/*
		final Label lbl_resultType = new Label(shell, SWT.NONE);
		FormData fd_lbl_resultType = new FormData();
		fd_lbl_resultType.top = new FormAttachment(lbl_description, 10);
		fd_lbl_resultType.left = new FormAttachment(0,10);
		lbl_resultType.setLayoutData(fd_lbl_resultType);
		lbl_resultType.setText("Result Type");
		//*/
		/*
		final Combo combo_postType = new Combo(shell, SWT.READ_ONLY);
		combo_postType.setItems(new String[] {"","Path", "History"});
		FormData fd_combo_postType = new FormData();
		fd_combo_postType.top = new FormAttachment(lbl_resultType, -2,SWT.TOP);
		fd_combo_postType.left = new FormAttachment(lbl_resultType, 10);
		fd_combo_postType.right = new FormAttachment(100, -10);
		combo_postType.setLayoutData(fd_combo_postType);
		combo_postType.select(0);
		combo_postType.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resultType = combo_postType.getText();
				//System.out.println(resultType);
				
				resultFilesPathList.clear();
				list_resultFiles.removeAll();
				
				//ArrayList<String> historyResult = new ArrayList<String>();
				//ArrayList<String> pathResult = new ArrayList<String>();
				
				if(resultFolderPath.equals("emptyHRolling-3d")){
					
				}else{
					//ArrayList<File> fList = null;
					//fList = (ArrayList<File>)myUtil.getDirFileList(resultFolderPath);
					for(File f : myUtil.getDirFileList(resultFolderPath)){
						String fileName = myUtil.getFileName(f.getAbsolutePath());
						if(resultType.equals("History")){
							if(ParserDefault.splitLineData(fileName,"\\.").get(0).toLowerCase().contains("history")){
								// History
								//historyResult.add(f.getAbsolutePath());
								resultFilesPathList.add(f.getAbsolutePath());
								list_resultFiles.add(f.getAbsolutePath());
							}
						}else if(resultType.equals("Path")){
							if(ParserDefault.splitLineData(fileName,"\\.").get(0).toLowerCase().contains("inc")){
								// Path
								//pathResult.add(f.getAbsolutePath());
								resultFilesPathList.add(f.getAbsolutePath());
								list_resultFiles.add(f.getAbsolutePath());
							}
						}
					}
					list_resultFiles.update();
				}
			}
		});
		//*/
		list_resultFiles = new List(shell, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.MULTI);
		list_resultFiles.setToolTipText("Add or Drag & Drop result file");
		FormData fd_list_resultFiles = new FormData();
		fd_list_resultFiles.top = new FormAttachment(lbl_description, 10);
		fd_list_resultFiles.left = new FormAttachment(0, 10);
		fd_list_resultFiles.right = new FormAttachment(100, -10);
		fd_list_resultFiles.bottom = new FormAttachment(lbl_description, 250, SWT.BOTTOM);
		list_resultFiles.setLayoutData(fd_list_resultFiles);
		list_resultFiles.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selectedFilesIndexList.clear();
				int[] selectedItems = list_resultFiles.getSelectionIndices();
				for(int i=0;i<selectedItems.length;i++){
					selectedFilesIndexList.add(selectedItems[i]);
				}
				//System.out.println(selectedFilesIndexList);
			}
		});
		
		
		Button btn_add = new Button(shell, SWT.NONE);
		FormData fd_btn_add = new FormData();
		fd_btn_add.top = new FormAttachment(list_resultFiles, 6);
		fd_btn_add.left = new FormAttachment(0, 10);
		fd_btn_add.right = new FormAttachment(0, 145);
		btn_add.setLayoutData(fd_btn_add);
		btn_add.setText("Add ");
		btn_add.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd = new FileDialog(shell, SWT.OPEN | SWT.MULTI);
				fd.setText("Select result Files");
				if(resultFolderPath.equals("emptyHRolling-3d")){
					fd.setFilterPath(System.getProperty("user.dir"));
				}else{
					fd.setFilterPath(resultFolderPath);
				}
				String [] filterName = {"csv files","All files"};
				String [] filterExt = {"*.csv","*.*"};
				fd.setFilterNames(filterName);
				fd.setFilterExtensions(filterExt);
				String selected = fd.open();
				String [] files;
				if(selected != null){
					files = fd.getFileNames();
					
					for(int i=0;i<files.length;i++){
						File f = new File(files[i]);
						String path = fd.getFilterPath()+File.separator+f.getName();
						//System.out.println(path);
						resultFilesPathList.add(path);
						list_resultFiles.add(path);
					}
					list_resultFiles.update();
				}
			}
		});
		
		
		Button btn_delete = new Button(shell, SWT.NONE);
		FormData fd_btn_delete = new FormData();
		fd_btn_delete.right = new FormAttachment(list_resultFiles, 0, SWT.RIGHT);
		fd_btn_delete.top = new FormAttachment(list_resultFiles, 6);
		fd_btn_delete.left = new FormAttachment(btn_add, 6);
		fd_btn_delete.right = new FormAttachment(100,-10);
		btn_delete.setLayoutData(fd_btn_delete);
		btn_delete.setText("Delete");
		btn_delete.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				/*
				for(int index : selectedFilesIndexList){
					System.out.println(index + " : "+resultFilesPathList.get(index));
				}
				*/
				for(int i = selectedFilesIndexList.size();i>0;i--){
					//System.out.println("index : "+selectedFilesIndexList.get(i-1));
					//System.out.println("delete : "+resultFilesPathList.get(selectedFilesIndexList.get(i-1)));
					list_resultFiles.remove(resultFilesPathList.get(selectedFilesIndexList.get(i-1)));
					resultFilesPathList.remove(resultFilesPathList.get(selectedFilesIndexList.get(i-1)));
					
				}
				
				list_resultFiles.update();
				list_resultFiles.deselectAll();
				selectedFilesIndexList.clear();
				//System.out.println(resultFilesPathList);
			}
		});
		
		Button btn_showPlot = new Button(shell, SWT.NONE);
		FormData fd_btn_showPlot = new FormData();
		fd_btn_showPlot.top = new FormAttachment(btn_add, 10);
		fd_btn_showPlot.bottom = new FormAttachment(100, -10);
		fd_btn_showPlot.left = new FormAttachment(btn_delete, 0, SWT.LEFT);
		fd_btn_showPlot.right = new FormAttachment(list_resultFiles, 0, SWT.RIGHT);
		btn_showPlot.setLayoutData(fd_btn_showPlot);
		btn_showPlot.setText("Show Plot");
		btn_showPlot.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println("TYPE : "+resultType);
				/*
				for(String path : resultFilesPathList){
					System.out.println(path);
				}
				*/
				try{
					PostPlotManager_ver2 postMgr = new PostPlotManager_ver2(resultFilesPathList);
					postMgr.running();
				}catch (Exception e2){
					
				}
				
				
			}
		});
		
		
		/*
		Button btn_close = new Button(shell, SWT.NONE);
		FormData fd_btn_close = new FormData();
		fd_btn_close.top = new FormAttachment(combo_postType, 14);
		fd_btn_close.bottom = new FormAttachment(100, -10);
		fd_btn_close.right = new FormAttachment(100, -10);
		fd_btn_close.left = new FormAttachment(btn_showPlot, 5);
		btn_close.setLayoutData(fd_btn_close);
		btn_close.setText("Close");
		btn_close.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				display.dispose();
				
		});
		*/
	}
	
	public void DNDListener(){
		this.target.addDropListener(new DropTargetListener(){

			@Override
			public void dragEnter(DropTargetEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("dragEnter");
				if(event.detail == DND.DROP_DEFAULT){
					event.detail = DND.DROP_COPY;
				}else{
					event.detail = DND.DROP_NONE;
				}
				
				for (int i = 0; i < event.dataTypes.length; i++) {
					if (fileTransfer.isSupportedType(event.dataTypes[i])){
						event.currentDataType = event.dataTypes[i];
						if (event.detail != DND.DROP_COPY) {
							event.detail = DND.DROP_NONE;
						}
						break;
					}
				}
			}

			@Override
			public void dragLeave(DropTargetEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("dragLeave");
			}

			@Override
			public void dragOperationChanged(DropTargetEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("dragOperationChanged");
				if(event.detail == DND.DROP_DEFAULT){
					if( (event.operations & DND.DROP_COPY) != 0){
						event.detail = DND.DROP_COPY;
					} else { 
						event.detail = DND.DROP_NONE;
					}
				}
				
				if(fileTransfer.isSupportedType(event.currentDataType)){
					if (event.detail != DND.DROP_COPY) {
						event.detail = DND.DROP_NONE;
					}
				}
			}

			@Override
			public void dragOver(DropTargetEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("dragOver");
				event.feedback = DND.FEEDBACK_SELECT | DND.FEEDBACK_SCROLL;
				 if(textTransfer.isSupportedType(event.currentDataType)){
					 Object o = textTransfer.nativeToJava(event.currentDataType);
					 String t = (String) o;
					 if(t != null){
						 //System.out.println("dragLeave : " +t);
					 }
				 }
			}

			@Override
			public void drop(DropTargetEvent event) {
				// TODO Auto-generated method stub
				// System.out.println("drop");

				if (textTransfer.isSupportedType(event.currentDataType)) {
					String text = (String) event.data;
					list_resultFiles.add(text);
				}

				if (fileTransfer.isSupportedType(event.currentDataType)) {
					String[] files = (String[]) event.data;

					for (int i = 0; i < files.length; i++) {
						list_resultFiles.add(files[i]);
						resultFilesPathList.add(files[i]);
					}
					list_resultFiles.update();
				}
			}

			@Override
			public void dropAccept(DropTargetEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("dropAccept");
			}
			
		});
	}
	
	/**
	 * Open the window.
	 */
	public void open() {
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	

}
