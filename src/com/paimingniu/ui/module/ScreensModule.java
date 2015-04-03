package com.paimingniu.ui.module;

import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.job.JobController;
import com.paimingniu.ui.job.JobTableController;
import com.paimingniu.ui.login.LoginController;
import com.paimingniu.ui.login.RegisterController;
import com.paimingniu.ui.login.UpdatePasswordController;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
@Singleton
public class ScreensModule {

	private Stage primaryStage;

	public void setPrimaryStage(Stage primaryStage) {
		this.primaryStage = primaryStage;
	}

	private Stage mparent;

	public void setMparent(Stage stage) {
		this.mparent = stage;
	}

	@Inject
	private LoginController login;

	@Inject
	private RegisterController register;

	@Inject
	private UpdatePasswordController updatePassword;

	@Inject
	private JobController job;
	
	@Inject
	private JobTableController jobTable;

	public FXMLDialog loginDialog() {
		return new FXMLDialog("用户登录", login,
				LoginController.class.getResource("login.fxml"), primaryStage,
				StageStyle.UTILITY);
	}

	public FXMLDialog registerDialog() {
		return new FXMLDialog("用户注册", register,
				RegisterController.class.getResource("register.fxml"), mparent,
				StageStyle.UTILITY);
	}

	public FXMLDialog updatePasswordDialog() {
		return new FXMLDialog("密码修改", updatePassword,
				UpdatePasswordController.class
						.getResource("updatePassword.fxml"), mparent,
				StageStyle.UTILITY);
	}

	public FXMLDialog jobDialog() {
		return new FXMLDialog("工作台", job,
				JobController.class.getResource("job.fxml"), primaryStage,
				StageStyle.DECORATED);
	}

	public Parent jobTableParent(String id,FXMLDialog dialog) {
		return FXMLDialog.getParent(jobTable,dialog, JobController.class.getResource(id+".fxml"));
	}

}
