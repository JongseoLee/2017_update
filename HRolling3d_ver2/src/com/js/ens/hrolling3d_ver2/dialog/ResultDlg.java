package com.js.ens.hrolling3d_ver2.dialog;

import javax.swing.SwingWorker;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.js.chartViewer.ChartViewer;
import com.js.chartViewer.PlotViewer;
import com.js.ens.hrolling3d_ver2.FolderTree;
import com.js.ens.hrolling3d_ver2.core.MainController;
import com.js.imageViewer.ImageViewer;
import com.js.util.myUtil;

public class ResultDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ResultDlg(Shell parentShell) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		FormLayout fl_container = new FormLayout();
		fl_container.spacing = 10;
		fl_container.marginWidth = 10;
		fl_container.marginTop = 10;
		fl_container.marginRight = 10;
		fl_container.marginLeft = 10;
		fl_container.marginHeight = 10;
		fl_container.marginBottom = 10;
		container.setLayout(fl_container);
		
		Label lblResult = new Label(container, SWT.NONE);
		lblResult.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblResult = new FormData();
		fd_lblResult.top = new FormAttachment(0);
		fd_lblResult.left = new FormAttachment(0);
		lblResult.setLayoutData(fd_lblResult);
		lblResult.setText("Result");
		
		Button btnChartViewer = new Button(container, SWT.NONE);
		btnChartViewer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(MC.getWorkspace() != null){
						ChartViewer window = new ChartViewer(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_result));
						window.open();
					}else{
						ChartViewer window = new ChartViewer("emptyHRolling-3d");
						window.open();	
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		FormData fd_btnChartViewer = new FormData();
		fd_btnChartViewer.top = new FormAttachment(lblResult, 20);
		fd_btnChartViewer.left = new FormAttachment(0, 40);
		fd_btnChartViewer.right = new FormAttachment(100,-40);
		fd_btnChartViewer.bottom = new FormAttachment(lblResult,100,SWT.BOTTOM);
		btnChartViewer.setLayoutData(fd_btnChartViewer);
		btnChartViewer.setText("Chart Viewer");
		
		Button btnImageViewer = new Button(container, SWT.NONE);
		btnImageViewer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				final SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {

					@Override
					protected String doInBackground() throws Exception {
						// TODO Auto-generated method stub
						if(MC.getWorkspace() != null){
							ImageViewer viewer = new ImageViewer(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_result));
							viewer.setVisible(true);
						}else {
							ImageViewer viewer = new ImageViewer();
							viewer.setVisible(true);
						}
						return "Open Image Viewer";
					}

					protected void done() {
					}

				};

				worker.execute();
			}
		});
		FormData fd_btnImageViewer = new FormData();
		fd_btnImageViewer.top = new FormAttachment(btnChartViewer, 5);
		fd_btnImageViewer.left = new FormAttachment(btnChartViewer, 0, SWT.LEFT);
		fd_btnImageViewer.right = new FormAttachment(btnChartViewer, 0, SWT.RIGHT);
		fd_btnImageViewer.bottom = new FormAttachment(btnChartViewer,85,SWT.BOTTOM);
		btnImageViewer.setLayoutData(fd_btnImageViewer);
		btnImageViewer.setText("Image Viewer");
		
		Button btnPlotViewer = new Button(container, SWT.NONE);
		btnPlotViewer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				try {
					if(MC.getWorkspace() != null){
						PlotViewer window = new PlotViewer(myUtil.setPath(MC.getWorkspace(), FolderTree.folderName_result));
						window.open();
					}else{
						PlotViewer window = new PlotViewer("emptyHRolling-3d");
						window.open();	
					}
					
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnPlotViewer.setText("Plot Viewer");
		FormData fd_btnPlotViewer = new FormData();
		fd_btnPlotViewer.top = new FormAttachment(btnImageViewer, 5);
		fd_btnPlotViewer.left = new FormAttachment(btnChartViewer, 0, SWT.LEFT);
		fd_btnPlotViewer.right = new FormAttachment(btnChartViewer, 0, SWT.RIGHT);
		fd_btnPlotViewer.bottom = new FormAttachment(btnImageViewer, 85, SWT.BOTTOM);
		btnPlotViewer.setLayoutData(fd_btnPlotViewer);
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,	true);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(409, 446);
	}

}
