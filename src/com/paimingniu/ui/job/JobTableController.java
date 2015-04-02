package com.paimingniu.ui.job;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import com.google.inject.Inject;
import com.paimingniu.ui.DialogController;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.MProgressIndicator;
import com.paimingniu.ui.module.ScreensModule;

public class JobTableController implements DialogController {

	@Inject
	ScreensModule mscreen;

	private FXMLDialog dialog;

	@FXML
	TextArea textArea;

	private final MProgressIndicator mps = new MProgressIndicator();

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	static int ii = 0;

	public void initialize() {

		mscreen.setMparent(dialog);
//		new Thread(new Runnable() {
//			public void run() {
//				int i = 0;
//				while (i < 100) {
//					i++;
//					ii = i;
//					Platform.runLater(new Runnable() {
//						public void run() {
//
//							textArea.appendText("我是工作日记：正在工作当中。。。" + ii + "\n");
//						}
//					});
//
//					try {
//						Thread.sleep(1000);
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}
//
//				}
//
//			}
//		}).start();
	}

}
