package com.js.ens.hrolling3d_ver2.dialog;

import java.util.ArrayList;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.js.ens.hrolling3d_ver2.core.MainController;

import org.eclipse.swt.widgets.Text;

public class ApplyDlg extends Dialog {
	private MainController MC = MainController.getInstance();
	private boolean isConsequent = true;
	private boolean isIndividual = false;
	private boolean isCheckedAll = true;
	private boolean isCheckedIndividual = false;
	private boolean isCheckedF1 = false;
	private boolean isCheckedF2 = false;
	private boolean isCheckedF3 = false;
	private boolean isCheckedF4 = false;
	private boolean isCheckedF5 = false;
	private boolean isCheckedF6 = false;
	private boolean isCheckedF7 = false;
	private Text textPlateSectionFile;
	private Text textDummyPlateSectionFile;
	
	private String sectionFilePath = "";
	private String dummySectionFilePath = "";
	
	/**
	 * Create the dialog.
	 * @param parentShell
	 */
	public ApplyDlg(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Create contents of the dialog.
	 * @param parent
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = (Composite) super.createDialogArea(parent);
		container.setLayout(new FormLayout());
		
		Label lblApply = new Label(container, SWT.NONE);
		lblApply.setFont(SWTResourceManager.getFont("Arial", 11, SWT.BOLD));
		FormData fd_lblApply = new FormData();
		fd_lblApply.top = new FormAttachment(0, 10);
		fd_lblApply.left = new FormAttachment(0, 10);
		lblApply.setLayoutData(fd_lblApply);
		lblApply.setText("Apply");
		
		Label lblComment = new Label(container, SWT.NONE);
		FormData fd_lblComment = new FormData();
		fd_lblComment.top = new FormAttachment(lblApply, 15);
		fd_lblComment.left = new FormAttachment(lblApply, 0, SWT.LEFT);
		lblComment.setLayoutData(fd_lblComment);
		lblComment.setText("Please select rolling type.");
		
		Composite compositeTop = new Composite(container, SWT.BORDER);
		compositeTop.setLayout(new FormLayout());
		FormData fd_compositeTop = new FormData();
		fd_compositeTop.top = new FormAttachment(lblComment, -30, SWT.TOP);
		fd_compositeTop.left = new FormAttachment(lblComment, 60);
		fd_compositeTop.right = new FormAttachment(100,-15);
		fd_compositeTop.bottom = new FormAttachment(lblComment, 10);
		compositeTop.setLayoutData(fd_compositeTop);
		
		final Button btnTypeC = new Button(compositeTop, SWT.RADIO);
		FormData fd_btnTypeC = new FormData();
		fd_btnTypeC.top = new FormAttachment(0, 10);
		fd_btnTypeC.left = new FormAttachment(0, 10);
		btnTypeC.setLayoutData(fd_btnTypeC);
		btnTypeC.setText("Consequent");
		btnTypeC.setSelection(true);
		
		final Button btnTypeI = new Button(compositeTop, SWT.RADIO);
		FormData fd_btnTypeI = new FormData();
		fd_btnTypeI.top = new FormAttachment(0, 10);
		fd_btnTypeI.left = new FormAttachment(0, 95);
		btnTypeI.setLayoutData(fd_btnTypeI);
		btnTypeI.setText("Individual");
		btnTypeI.setSelection(false);
		
		/*
		final Button btnTypeC = new Button(compositeTop, SWT.RADIO);
		FormData fd_btnTypeC = new FormData();
		fd_btnTypeC.top = new FormAttachment(0, 10);
		fd_btnTypeC.left = new FormAttachment(0, 10);
		btnTypeC.setLayoutData(fd_btnTypeC);
		btnTypeC.setText("Consequent");
		btnTypeC.setSelection(true);
		
		final Button btnTypeI = new Button(compositeTop, SWT.RADIO);
		FormData fd_btnTypeI = new FormData();
		fd_btnTypeI.top = new FormAttachment(btnTypeC, 0, SWT.TOP);
		fd_btnTypeI.left = new FormAttachment(btnTypeC, 10, SWT.RIGHT);
		btnTypeI.setLayoutData(fd_btnTypeI);
		btnTypeI.setText("Individual");
		btnTypeI.setSelection(false);
		*/
		
		final Button btnAll = new Button(container, SWT.RADIO);
		FormData fd_btnAll = new FormData();
		fd_btnAll.top = new FormAttachment(lblComment, 10);
		fd_btnAll.left = new FormAttachment(lblComment, 10, SWT.LEFT);
		btnAll.setLayoutData(fd_btnAll);
		btnAll.setText("Multiple Rolling");
		btnAll.setSelection(true);
		
		final Button btnIndividual = new Button(container, SWT.RADIO);
		FormData fd_btnIndividual = new FormData();
		fd_btnIndividual.top = new FormAttachment(btnAll, 10);
		fd_btnIndividual.left = new FormAttachment(btnAll, 0, SWT.LEFT);
		btnIndividual.setLayoutData(fd_btnIndividual);
		btnIndividual.setText("Single Rolling");
		btnAll.setSelection(false);
		
		final Composite composite = new Composite(container, SWT.BORDER);
		composite.setLayout(new FormLayout());
		FormData fd_composite = new FormData();
		fd_composite.top = new FormAttachment(btnIndividual, 5);
		fd_composite.left = new FormAttachment(btnIndividual, 10,SWT.LEFT);
		fd_composite.right = new FormAttachment(100, -20);
		fd_composite.bottom = new FormAttachment(100, -10);		
		composite.setLayoutData(fd_composite);
		
		final Button btnF1 = new Button(composite, SWT.RADIO);
		FormData fd_btnF1 = new FormData();
		fd_btnF1.top = new FormAttachment(0, 10);
		fd_btnF1.left = new FormAttachment(0, 10);
		btnF1.setLayoutData(fd_btnF1);
		btnF1.setText("F1");
		btnF1.setSelection(false);
		
		final Button btnF2 = new Button(composite, SWT.RADIO);
		FormData fd_btnF2 = new FormData();
		fd_btnF2.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF2.left = new FormAttachment(btnF1, 10);
		btnF2.setLayoutData(fd_btnF2);
		btnF2.setText("F2");
		btnF2.setSelection(false);

		final Button btnF3 = new Button(composite, SWT.RADIO);
		FormData fd_btnF3 = new FormData();
		fd_btnF3.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF3.left = new FormAttachment(btnF2, 10);
		btnF3.setLayoutData(fd_btnF3);
		btnF3.setText("F3");
		btnF3.setSelection(false);
		
		final Button btnF4 = new Button(composite, SWT.RADIO);
		FormData fd_btnF4 = new FormData();
		fd_btnF4.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF4.left = new FormAttachment(btnF3, 10);
		btnF4.setLayoutData(fd_btnF4);
		btnF4.setText("F4");
		btnF4.setSelection(false);
		
		final Button btnF5 = new Button(composite, SWT.RADIO);
		FormData fd_btnF5 = new FormData();
		fd_btnF5.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF5.left = new FormAttachment(btnF4, 10);
		btnF5.setLayoutData(fd_btnF5);
		btnF5.setText("F5");
		btnF5.setSelection(false);
		
		final Button btnF6 = new Button(composite, SWT.RADIO);
		FormData fd_btnF6 = new FormData();
		fd_btnF6.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF6.left = new FormAttachment(btnF5, 10);
		btnF6.setLayoutData(fd_btnF6);
		btnF6.setText("F6");
		btnF6.setSelection(false);
		
		final Button btnF7 = new Button(composite, SWT.RADIO);
		FormData fd_btnF7 = new FormData();
		fd_btnF7.top = new FormAttachment(btnF1, 0, SWT.TOP);
		fd_btnF7.left = new FormAttachment(btnF6, 10);
		btnF7.setLayoutData(fd_btnF7);
		btnF7.setText("F7");
		btnF7.setSelection(false);
		
		Label lblPlateSectionFile = new Label(composite, SWT.NONE);
		FormData fd_lblPlateSectionFile = new FormData();
		fd_lblPlateSectionFile.top = new FormAttachment(btnF1, 5);
		fd_lblPlateSectionFile.left = new FormAttachment(0, 10);
		lblPlateSectionFile.setLayoutData(fd_lblPlateSectionFile);
		lblPlateSectionFile.setText("Plate Section File");
		
		textPlateSectionFile = new Text(composite, SWT.BORDER);
		FormData fd_textPlateSectionFile = new FormData();
		fd_textPlateSectionFile.right = new FormAttachment(0, 308);
		fd_textPlateSectionFile.top = new FormAttachment(lblPlateSectionFile, 5);
		fd_textPlateSectionFile.left = new FormAttachment(lblPlateSectionFile, 0,SWT.LEFT);
		textPlateSectionFile.setLayoutData(fd_textPlateSectionFile);
		
		
		final Button btnExplorer1 = new Button(composite, SWT.NONE);
		FormData fd_btnExplorer1 = new FormData();
		fd_btnExplorer1.top = new FormAttachment(textPlateSectionFile, -2, SWT.TOP);
		fd_btnExplorer1.left = new FormAttachment(textPlateSectionFile, 3);
		btnExplorer1.setLayoutData(fd_btnExplorer1);
		btnExplorer1.setText("...");
		
		Label lblDummyPlateSection = new Label(composite, SWT.NONE);
		FormData fd_lblDummyPlateSection = new FormData();
		fd_lblDummyPlateSection.top = new FormAttachment(textPlateSectionFile, 5);
		fd_lblDummyPlateSection.left = new FormAttachment(lblPlateSectionFile, 0,SWT.LEFT);
		lblDummyPlateSection.setLayoutData(fd_lblDummyPlateSection);
		lblDummyPlateSection.setText("Dummy Plate Section File");
		
		textDummyPlateSectionFile = new Text(composite, SWT.BORDER);
		FormData fd_textDummyPlateSectionFile = new FormData();
		fd_textDummyPlateSectionFile.right = new FormAttachment(btnF7, 0, SWT.RIGHT);
		fd_textDummyPlateSectionFile.top = new FormAttachment(lblDummyPlateSection, 5);
		fd_textDummyPlateSectionFile.left = new FormAttachment(lblPlateSectionFile, 0,SWT.LEFT);
		textDummyPlateSectionFile.setLayoutData(fd_textDummyPlateSectionFile);
		
		final Button btnExplorer2 = new Button(composite, SWT.NONE);
		btnExplorer2.setText("...");
		FormData fd_btnExplorer2 = new FormData();
		fd_btnExplorer2.top = new FormAttachment(textDummyPlateSectionFile, -2, SWT.TOP);
		fd_btnExplorer2.left = new FormAttachment(btnExplorer1, 0, SWT.LEFT);
		btnExplorer2.setLayoutData(fd_btnExplorer2);
		
		
		isConsequent = true;
		isIndividual = false;
		btnTypeC.setSelection(true);
		btnTypeI.setSelection(false);
		
		isCheckedAll = true;
		isCheckedIndividual = false;
		composite.setEnabled(false);
		btnF1.setSelection(false);
		btnF2.setSelection(false);
		btnF3.setSelection(false);
		btnF4.setSelection(false);
		btnF5.setSelection(false);
		btnF6.setSelection(false);
		btnF7.setSelection(false);
		btnF1.setEnabled(false);
		btnF2.setEnabled(false);
		btnF3.setEnabled(false);
		btnF4.setEnabled(false);
		btnF5.setEnabled(false);
		btnF6.setEnabled(false);
		btnF7.setEnabled(false);
		
		
		
		isCheckedF1 = false;
		isCheckedF2 = false;
		isCheckedF3 = false;
		isCheckedF4 = false;
		isCheckedF5 = false;
		isCheckedF6 = false;
		isCheckedF7 = false;
		
		
		//////////////////////////////////////////////////////////////////////
		btnExplorer1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog  dlg = new FileDialog (btnExplorer1.getShell(),SWT.OPEN);
				dlg.setText("Select plate section file");
				
				//String [] extNames = {"RTL(*.rtl)"};
				//String [] extType = {"*.rtl"};
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path == null){
					return;
				}else {
					textPlateSectionFile.setText(path);
					sectionFilePath = path;
				}
			}
		});
		
		btnExplorer2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog  dlg = new FileDialog (btnExplorer2.getShell(),SWT.OPEN);
				dlg.setText("Select dummy plate section file");
				
				//String [] extNames = {"RTL(*.rtl)"};
				//String [] extType = {"*.rtl"};
				String [] extNames = {"All(*.*)"};
				String [] extType = {"*.*"};
				
				dlg.setFilterNames(extNames);
				dlg.setFilterExtensions(extType);
				
				dlg.setFilterNames(extNames);
				String path = dlg.open();
				if (path == null){
					return;
				}else {
					textDummyPlateSectionFile.setText(path);
					dummySectionFilePath = path;
				}
			}
		});
		
		btnTypeC.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				isConsequent = btnTypeC.getSelection();
				isIndividual = btnTypeI.getSelection();
				/*
				if(isConsequent){
					MC.setApplyType("Consequent");
				}else{
					MC.setApplyType("Individual");
				}
				*/
				
			}
		});
		
		btnTypeI.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				isConsequent = btnTypeC.getSelection();
				isIndividual = btnTypeI.getSelection();
				/*
				if(isIndividual){
					MC.setApplyType("Individual");
				}else{
					MC.setApplyType("Consequent");
				}
				*/
			}
		});
		
		btnAll.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedAll = btnAll.getSelection();
				if(isCheckedAll){
					isCheckedIndividual = false;
					composite.setEnabled(false);
					btnF1.setSelection(false);
					btnF2.setSelection(false);
					btnF3.setSelection(false);
					btnF4.setSelection(false);
					btnF5.setSelection(false);
					btnF6.setSelection(false);
					btnF7.setSelection(false);
					btnF1.setEnabled(false);
					btnF2.setEnabled(false);
					btnF3.setEnabled(false);
					btnF4.setEnabled(false);
					btnF5.setEnabled(false);
					btnF6.setEnabled(false);
					btnF7.setEnabled(false);
					isCheckedF1 = false;
					isCheckedF2 = false;
					isCheckedF3 = false;
					isCheckedF4 = false;
					isCheckedF5 = false;
					isCheckedF6 = false;
					isCheckedF7 = false;
					textPlateSectionFile.setEnabled(false);
					textDummyPlateSectionFile.setEnabled(false);
					btnExplorer1.setEnabled(false);
					btnExplorer2.setEnabled(false);
					
				}else{
					isCheckedIndividual = true;
					composite.setEnabled(true);
					btnF1.setEnabled(true);
					btnF2.setEnabled(true);
					btnF3.setEnabled(true);
					btnF4.setEnabled(true);
					btnF5.setEnabled(true);
					btnF6.setEnabled(true);
					btnF7.setEnabled(true);
					btnExplorer1.setEnabled(true);
					btnExplorer2.setEnabled(true);
					textPlateSectionFile.setEnabled(true);
					textDummyPlateSectionFile.setEnabled(true);
					btnExplorer1.setEnabled(true);
					btnExplorer2.setEnabled(true);
				}
			}
		});
		
		btnIndividual.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedIndividual = btnIndividual.getSelection();
				if(isCheckedIndividual){
					isCheckedAll = false;
					composite.setEnabled(true);
					btnF1.setEnabled(true);
					btnF2.setEnabled(true);
					btnF3.setEnabled(true);
					btnF4.setEnabled(true);
					btnF5.setEnabled(true);
					btnF6.setEnabled(true);
					btnF7.setEnabled(true);
					textPlateSectionFile.setEnabled(true);
					textDummyPlateSectionFile.setEnabled(true);
					btnExplorer1.setEnabled(true);
					btnExplorer2.setEnabled(true);
				}else{
					isCheckedAll = true;
					composite.setEnabled(false);
					btnF1.setSelection(false);
					btnF2.setSelection(false);
					btnF3.setSelection(false);
					btnF4.setSelection(false);
					btnF5.setSelection(false);
					btnF6.setSelection(false);
					btnF7.setSelection(false);
					btnF1.setEnabled(false);
					btnF2.setEnabled(false);
					btnF3.setEnabled(false);
					btnF4.setEnabled(false);
					btnF5.setEnabled(false);
					btnF6.setEnabled(false);
					btnF7.setEnabled(false);
					isCheckedF1 = false;
					isCheckedF2 = false;
					isCheckedF3 = false;
					isCheckedF4 = false;
					isCheckedF5 = false;
					isCheckedF6 = false;
					isCheckedF7 = false;
					textPlateSectionFile.setEnabled(false);
					textDummyPlateSectionFile.setEnabled(false);
					btnExplorer1.setEnabled(false);
					btnExplorer2.setEnabled(false);
				}
			}
		});
		
		btnF1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		
		btnF2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		
		btnF3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		
		btnF4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		
		btnF5.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		
		btnF6.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		
		btnF7.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				isCheckedF1 = btnF1.getSelection();
				isCheckedF2 = btnF2.getSelection();
				isCheckedF3 = btnF3.getSelection();
				isCheckedF4 = btnF4.getSelection();
				isCheckedF5 = btnF5.getSelection();
				isCheckedF6 = btnF6.getSelection();
				isCheckedF7 = btnF7.getSelection();
			}
		});
		return container;
	}

	/**
	 * Create contents of the button bar.
	 * @param parent
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		Button btnOk = createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		btnOk.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<Boolean> result = new ArrayList<Boolean>();
				if(isCheckedAll){
					result.add(true);
					result.add(true);
					result.add(true);
					result.add(true);
					result.add(true);
					result.add(true);
					result.add(true);
					System.out.println("All Checked");
				}else{
					if(isCheckedIndividual)	
						System.out.println("Individual Checked");
					
					if(isCheckedF1){
						System.out.println("F1 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
					if(isCheckedF2){
						System.out.println("F2 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
					if(isCheckedF3){
						System.out.println("F3 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
					if(isCheckedF4){
						System.out.println("F4 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
					if(isCheckedF5){
						System.out.println("F5 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
					if(isCheckedF6){
						System.out.println("F6 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
					if(isCheckedF7){
						System.out.println("F7 Checked");
						result.add(true);
					}else{
						result.add(false);
					}
				}
				RunApplyResult(result);
				
				
			}
		});
		
		createButton(parent, IDialogConstants.CANCEL_ID,	IDialogConstants.CANCEL_LABEL, false);
		
	}

	/**
	 * Return the initial size of the dialog.
	 */
	@Override
	protected Point getInitialSize() {
		return new Point(402, 361);
	}
	
	private void RunApplyResult(ArrayList<Boolean> result){
		if(isConsequent){
			MC.setApplyType(MC.ApplyType_Consequent);
		}else{
			MC.setApplyType(MC.ApplyType_Individual);
		}
		
		if(isCheckedIndividual){
			MC.setRunType(MC.RunType_Single);
			MC.setSectionFilePath(sectionFilePath);
			MC.setDummySectionFilePath(dummySectionFilePath);
		}else{
			MC.setRunType(MC.RunType_Multiful);
			MC.setSectionFilePath(null);
			MC.setDummySectionFilePath(null);
		}
		
		MC.RunApplyResult(result);
		
		
	}
}
