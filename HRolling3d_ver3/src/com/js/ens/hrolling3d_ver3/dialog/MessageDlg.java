package com.js.ens.hrolling3d_ver3.dialog;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;

public class MessageDlg extends Dialog {
	private Text textMessage;
	private String msg = null;
	

	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public MessageDlg(Shell parentShell,String msg) {
		super(parentShell);
		setShellStyle(SWT.SHELL_TRIM | SWT.BORDER);
		this.msg = msg;
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
		
		Label lblMessageDialog = new Label(container, SWT.NONE);
		lblMessageDialog.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblMessageDialog = new FormData();
		fd_lblMessageDialog.top = new FormAttachment(0);
		fd_lblMessageDialog.left = new FormAttachment(0);
		lblMessageDialog.setLayoutData(fd_lblMessageDialog);
		lblMessageDialog.setText("Message Dialog");
		
		textMessage = new Text(container, SWT.BORDER | SWT.READ_ONLY | SWT.H_SCROLL | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI);
		textMessage.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		FormData fd_textMessage = new FormData();
		fd_textMessage.top = new FormAttachment(lblMessageDialog, 5);
		fd_textMessage.left = new FormAttachment(lblMessageDialog, 0, SWT.LEFT);
		fd_textMessage.right = new FormAttachment(100,0);
		fd_textMessage.bottom = new FormAttachment(100,0);
		textMessage.setLayoutData(fd_textMessage);
		textMessage.setText(msg);
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,
				true);
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(600, 450);
	}
}
