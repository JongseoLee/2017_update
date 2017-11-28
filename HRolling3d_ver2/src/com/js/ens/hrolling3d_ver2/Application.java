package com.js.ens.hrolling3d_ver2;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.eclipse.equinox.app.IApplication;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import com.js.ens.hrolling3d_ver2.core.LicenseCheck;
import com.js.util.myUtil;

/**
 * This class controls all aspects of the application's execution
 */
public class Application implements IApplication {

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#start(org.eclipse.equinox.app.IApplicationContext)
	 */
	
	private Logger log = Logger.getLogger(Application.class);
	
	
	public Object start(IApplicationContext context) {
		
		Display display = PlatformUI.createDisplay();
		
		System.setProperty("LogPath.ens", FolderTree.folderPath_Log);
		PropertyConfigurator.configure(FolderTree.filePath_LogProperties);
		log.info("Start HRolling-3d-ver2");
		/* */
		// Using License 
		LicenseCheck engine = new LicenseCheck();
		try {
			int returnCode = 0;
			if(engine.running()){
				//License OK
				log.info("License is OK..."+myUtil.getCurrentDate());
				returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			}else{
				//LIcense Error
				log.error("License is Error..."+myUtil.getCurrentDate());
				JOptionPane.showMessageDialog(null,"License Error" , "HRolling-3d_ver2", JOptionPane.ERROR_MESSAGE);
				returnCode = IApplication.EXIT_OK;
			}
			
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			
			return IApplication.EXIT_OK;
		}catch(Exception e){
			e.printStackTrace();
			log.error("License is Error..."+myUtil.getCurrentDate());
			JOptionPane.showMessageDialog(null, e.getMessage(), "HRolling-3d", JOptionPane.ERROR_MESSAGE);
			return IApplication.EXIT_OK;
		}finally {
			display.dispose();
		}
		// */
		/* 
		// Without Checking License
		try {
			int returnCode = PlatformUI.createAndRunWorkbench(display, new ApplicationWorkbenchAdvisor());
			if (returnCode == PlatformUI.RETURN_RESTART) {
				return IApplication.EXIT_RESTART;
			}
			return IApplication.EXIT_OK;
		} finally {
			display.dispose();
		}
		// */
	}

	/* (non-Javadoc)
	 * @see org.eclipse.equinox.app.IApplication#stop()
	 */
	public void stop() {
		if (!PlatformUI.isWorkbenchRunning())
			return;
		final IWorkbench workbench = PlatformUI.getWorkbench();
		final Display display = workbench.getDisplay();
		display.syncExec(new Runnable() {
			public void run() {
				if (!display.isDisposed())
					workbench.close();
			}
		});
	}
}
