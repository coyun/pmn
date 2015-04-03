package com.paimingniu.main;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;

import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.paimingniu.driver.DriverModule;
import com.paimingniu.driver.Monitor;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.module.ScreensModule;

public class Main extends Application {

	public static FXMLDialog login;

	@Override
	public void start(Stage primaryStage) {

		List<Module> mList = new ArrayList<Module>();
		mList.add(new DriverModule());
		mList.add(new Module() {
			public void configure(Binder binder) {
				binder.bind(Monitor.class).in(Scopes.SINGLETON);
			}
		});
		Injector inj = Guice.createInjector(mList);

		ScreensModule screens = inj.getInstance(ScreensModule.class);
		screens.setPrimaryStage(primaryStage);
		screens.loginDialog().show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
