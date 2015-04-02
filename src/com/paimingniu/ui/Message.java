package com.paimingniu.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Message {

	public enum Response {
		NO, YES, CANCEL
	};

	private static Response buttonSelected = Response.CANCEL;

	private static ImageView icon = new ImageView();

	static class Dialog extends Stage {
		public Dialog(String title, Stage owner, Scene scene, String iconFile) {
			setTitle(title);
			initStyle(StageStyle.UTILITY);
			initModality(Modality.APPLICATION_MODAL);
			initOwner(owner);
			setResizable(false);
			setScene(scene);
			icon.setImage(new Image(getClass().getResourceAsStream(iconFile)));
		}

		public void showDialog() {
			sizeToScene();
			centerOnScreen();
			showAndWait();
		}
	}

	static class IMessage extends Text {
		public IMessage(String msg) {
			super(msg);
			setTranslateX(10);
			setTranslateY(10);
			setWrappingWidth(200);
		}
	}

	/**
	 * 
	 * @param Stage
	 * @param message
	 * @param title
	 * @return
	 */
	public static Response showConfirm(Stage owner, String message, String title) {
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color: lightblue");
		Scene scene = new Scene(vb);
		final Dialog dial = new Dialog(title, owner, scene, "confirm48.png");
		vb.setPadding(new Insets(10, 10, 10, 10));
		vb.setSpacing(10);
		Button yesButton = new Button("是");
		yesButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dial.close();
				buttonSelected = Response.YES;
			}
		});
		Button noButton = new Button("否");
		noButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dial.close();
				buttonSelected = Response.NO;
			}
		});
		BorderPane bp = new BorderPane();
		HBox buttons = new HBox();
		buttons.setAlignment(Pos.CENTER);
		buttons.setSpacing(10);
		buttons.getChildren().addAll(yesButton, noButton);
		bp.setCenter(buttons);
		HBox msg = new HBox();
		msg.setSpacing(5);
		msg.getChildren().addAll(icon, new IMessage(message));
		vb.getChildren().addAll(msg, bp);
		dial.showDialog();

		return buttonSelected;
	}

	/**
	 * 
	 * @param Stage
	 * @param message
	 * @param title
	 */
	public static void showInfo(Stage owner, String message, String title) {
		showMessageDialog(owner, new IMessage(message), title, "info48.png");
	}

	/**
	 * 
	 * @param Stage
	 * @param message
	 * @param title
	 */
	public static void showError(Stage owner, String message, String title) {
		showMessageDialog(owner, new IMessage(message), title, "error48.png");
	}

	private static void showMessageDialog(Stage owner, Node message,
			String title, String imgUrl) {
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color: lightblue");
		Scene scene = new Scene(vb);
		final Dialog dial = new Dialog(title, owner, scene, imgUrl);
		vb.setPadding(new Insets(10, 10, 10, 10));
		vb.setSpacing(10);
		Button okButton = new Button("确定");
		okButton.setAlignment(Pos.CENTER);
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dial.close();
			}
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(okButton);
		HBox msg = new HBox();
		msg.setSpacing(5);
		msg.getChildren().addAll(icon, message);
		vb.getChildren().addAll(msg, bp);
		dial.showDialog();

	}

	public static void showInfoAfterClossParent(final Stage owner,
			String message, String title) {
		VBox vb = new VBox();
		vb.setStyle("-fx-background-color: lightblue");
		Scene scene = new Scene(vb);
		final Dialog dial = new Dialog(title, owner, scene, "info48.png");
		vb.setPadding(new Insets(10, 10, 10, 10));
		vb.setSpacing(10);
		Button okButton = new Button("确定");
		okButton.setAlignment(Pos.CENTER);
		okButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				dial.close();
				owner.close();
			}
		});
		BorderPane bp = new BorderPane();
		bp.setCenter(okButton);
		HBox msg = new HBox();
		msg.setSpacing(5);
		msg.getChildren().addAll(icon, new IMessage(message));
		vb.getChildren().addAll(msg, bp);
		dial.showDialog();

	}

}