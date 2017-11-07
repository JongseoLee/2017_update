package com.js.ens.hrolling3d_ver3.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
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

import com.js.ens.hrolling3d_ver3.core.MainController;

public class ExportDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ExportDlg(Shell parentShell) {
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
		
		Label lblExport = new Label(container, SWT.NONE);
		lblExport.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblExport = new FormData();
		fd_lblExport.top = new FormAttachment(0);
		fd_lblExport.left = new FormAttachment(0);
		lblExport.setLayoutData(fd_lblExport);
		lblExport.setText("Export");
		
		Label lblDoYouExort = new Label(container, SWT.NONE);
		FormData fd_lblDoYouExort = new FormData();
		fd_lblDoYouExort.top = new FormAttachment(lblExport, 15);
		fd_lblDoYouExort.left = new FormAttachment(10);
		lblDoYouExort.setLayoutData(fd_lblDoYouExort);
		lblDoYouExort.setText("Do you export all data?");
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btn_OK =createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,true);
		btn_OK.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				MC.RunExportProject();
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
		return new Point(416, 234);
	}

}
