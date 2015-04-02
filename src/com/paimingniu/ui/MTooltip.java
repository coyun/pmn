package com.paimingniu.ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.control.Control;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Mar 17, 2015
 * @version V1.0
 */
public class MTooltip extends Tooltip {

	private static MTooltip m = new MTooltip();

	public static void show(final Stage stage, final Control control, String msg) {

		Point2D p = control.localToScene(1.0, 18.0);
		m.setText(msg);
		control.setTooltip(m);

		control.getTooltip().show(
				control,
				p.getX() + control.getScene().getX()
						+ control.getScene().getWindow().getX(),
				p.getY() + control.getScene().getY()
						+ control.getScene().getWindow().getY());
		control.getScene().setOnKeyReleased(new EventHandler<Event>() {
			public void handle(Event event) {
				control.getTooltip().hide();
			}
		});
		control.getScene().setOnMouseClicked(new EventHandler<Event>() {
			public void handle(Event event) {
				control.getTooltip().hide();
			}

		});
		control.focusedProperty().addListener(new ChangeListener<Object>() {
			@Override
			public void changed(ObservableValue<?> observable, Object oldValue,
					Object newValue) {
				control.getTooltip().hide();
			}

		});

		stage.getScene().setOnMouseExited(new EventHandler<Event>() {
			public void handle(Event event) {
				control.getTooltip().hide();
				stage.getScene().setOnMouseExited(null);
			}
		});

	}

}
