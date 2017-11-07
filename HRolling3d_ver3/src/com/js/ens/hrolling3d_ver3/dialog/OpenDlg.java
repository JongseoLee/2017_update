package com.js.ens.hrolling3d_ver3.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.wb.swt.SWTResourceManager;

import com.js.ens.hrolling3d_ver3.core.MainController;

public class OpenDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	private Text textDBFilePath;
	private String DBFilePath = null;
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public OpenDlg(Shell parentShell) {
		super(parentShell);
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
		
		Label lblOpen = new Label(container, SWT.NONE);
		lblOpen.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblOpen = new FormData();
		fd_lblOpen.top = new FormAttachment(0);
		fd_lblOpen.left = new FormAttachment(0);
		lblOpen.setLayoutData(fd_lblOpen);
		lblOpen.setText("Open");
		
		Label lblSelectLevelerDb = new Label(container, SWT.NONE);
		FormData fd_lblSelectLevelerDb = new FormData();
		fd_lblSelectLevelerDb.top = new FormAttachment(lblOpen, 15);
		fd_lblSelectLevelerDb.left = new FormAttachment(lblOpen, 10, SWT.LEFT);
		lblSelectLevelerDb.setLayoutData(fd_lblSelectLevelerDb);
		lblSelectLevelerDb.setText("Select DB File.");
		
		textDBFilePath = new Text(container, SWT.BORDER);
		textDBFilePath.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				Text text = (Text)e.getSource();
				DBFilePath = text.getText();
			}
		});
		FormData fd_textDBFilePath = new FormData();
		fd_textDBFilePath.top = new FormAttachment(lblSelectLevelerDb, 5);
		fd_textDBFilePath.left = new FormAttachment(lblSelectLevelerDb, 0,SWT.LEFT);
		fd_textDBFilePath.right = new FormAttachment(100,-35);
		textDBFilePath.setLayoutData(fd_textDBFilePath);
		
		final Button buttonExplorer = new Button(container, SWT.NONE);
		buttonExplorer.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog  dlg = new FileDialog (buttonExplorer.getShell(),SWT.OPEN);
				dlg.setText("Select Modulus of elasticity File");
				
				//String [] extNames = {"RTL(*.rtl)"};
				//String [] extType = {"*.rtl"};
				String [] extNames = {"ENS DB(*.ens)"};
				String [] extType = {"*.ens"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path == null){
					return;
				}else {
					textDBFilePath.setText(path);
				}
			}
		});
		FormData fd_buttonExplorer = new FormData();
		fd_buttonExplorer.top = new FormAttachment(textDBFilePath, -2, SWT.TOP);
		fd_buttonExplorer.left = new FormAttachment(textDBFilePath, 5);
		fd_buttonExplorer.right = new FormAttachment(textDBFilePath, 45, SWT.RIGHT);
		buttonExplorer.setLayoutData(fd_buttonExplorer);
		buttonExplorer.setText("...");
		
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btn_OK=createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,true);
		btn_OK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(DBFilePath != null){
					Open();
				}
			}
		});
		createButton(parent, IDialogConstants.CANCEL_ID,
				IDialogConstants.CANCEL_LABEL, false);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(450, 263);
	}
	
	private void Open(){
		MC.RunOpenProject(DBFilePath);
	}
}
