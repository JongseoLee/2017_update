package com.js.ens.hrolling3d_ver2.fileMenuHandler;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;

import com.js.ens.hrolling3d_ver2.core.MainController;

public class File_ExportHandler extends AbstractHandler implements IHandler {
	private MainController MC = MainController.getInstance();
	public File_ExportHandler() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		// TODO Auto-generated method stub
		MC.ExportProject();
		return null;
	}

}
