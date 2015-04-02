package com.paimingniu.ui.job;

import java.util.HashMap;
import java.util.Map;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.WindowEvent;

import com.google.inject.Inject;
import com.paimingniu.ui.DialogController;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.MProgressIndicator;
import com.paimingniu.ui.module.ScreensModule;
import com.paimingniu.util.HttpUtil;

public class JobController implements DialogController {

	@Inject
	ScreensModule mscreen;

	private FXMLDialog dialog;

	@FXML
	BorderPane borderPane;
	@FXML
	TabPane tabPane;

	private final MProgressIndicator mps = new MProgressIndicator();

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	private Map<String, Object> ControllerMap = new HashMap<String, Object>();

	public void initialize() {

		borderPane.getChildren().add(mps);
	
		mscreen.setMparent(dialog);
		
		
		dialog.setOnShown(new EventHandler<WindowEvent>(){

			@Override
			public void handle(WindowEvent event) {
				HttpUtil.closs();
			}
			
		});

		tabPane.getSelectionModel().clearSelection();
		tabPane.getSelectionModel().selectedItemProperty()
				.addListener(new ChangeListener<Tab>() {
					@Override
					public void changed(
							ObservableValue<? extends Tab> observable,
							Tab oldValue, Tab newValue) {
					
						if (newValue.getContent() == null) {

							Parent parent = (Parent) ControllerMap.get(newValue
									.getId());
							if (parent == null) {
								parent = mscreen.jobTableParent(
										newValue.getId(), dialog);
								ControllerMap.put(newValue.getId(), parent);
							}
							newValue.setContent(parent);

						} else {
							// Content is already loaded. Update it if
							// necessary.
							Parent root = (Parent) newValue.getContent();
							// Optionally get the controller from Map and
							// manipulate the content
							// via its controller.
						}

					}
				});

	}
}
