package com.paimingniu.ui;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;

import com.paimingniu.image.IMG;

public class FXMLDialog extends Stage {

	public FXMLDialog(String title, DialogController controller, URL fxml,
			Window owner) {
		this(title, controller, fxml, owner, StageStyle.DECORATED);
	}

	public FXMLDialog(String title, final DialogController controller,
			URL fxml, Window owner, StageStyle style) {
		super(style);
		initOwner(owner);
		setTitle(title);
		getIcons().add(new Image(IMG.class.getResourceAsStream("niu-60x60.png")));
		initModality(Modality.WINDOW_MODAL);
		FXMLLoader loader = new FXMLLoader(fxml);
		try {
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> aClass) {
					return controller;
				}
			});
			controller.setDialog(this);
			setScene(new Scene((Parent) loader.load()));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public static Parent getParent(final DialogController controller,
			FXMLDialog dialog, URL fxml) {

		FXMLLoader loader = new FXMLLoader(fxml);
		try {
			loader.setControllerFactory(new Callback<Class<?>, Object>() {
				@Override
				public Object call(Class<?> aClass) {
					return controller;
				}
			});
			controller.setDialog(dialog);
			return (Parent) loader.load();
		} catch (IOException e) {
			// throw new RuntimeException(e);
		}
		return null;

	}

}
