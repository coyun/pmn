package com.paimingniu.ui.job;

import com.google.inject.Inject;
import com.paimingniu.ui.DialogController;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.MProgressIndicator;
import com.paimingniu.ui.module.ScreensModule;

public class TaskTableController implements DialogController {

	@Inject
	ScreensModule mscreen;

	private FXMLDialog dialog;

	private final MProgressIndicator mps = new MProgressIndicator();

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	public void initialize() {

		mscreen.setMparent(dialog);
	}

}
