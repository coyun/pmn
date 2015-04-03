package com.paimingniu.ui.login;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.paimingniu.ui.DialogController;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.LoginInfo;
import com.paimingniu.ui.MProgressIndicator;
import com.paimingniu.ui.MTooltip;
import com.paimingniu.ui.Message;
import com.paimingniu.ui.module.ScreensModule;
import com.paimingniu.util.HttpUtil;
import com.paimingniu.util.StatusEntity;

public class LoginController implements DialogController {

	@Inject
	ScreensModule mscreen;

	private FXMLDialog dialog;

	@FXML
	AnchorPane anchorPane;

	@FXML
	TextField username;

	@FXML
	PasswordField password;

	@FXML
	CheckBox checkboxId;

	public String getEmail() {

		return username.getText();
	}

	public String getPassword() {

		return password.getText();

	}

	private final MProgressIndicator mps = new MProgressIndicator();

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	public void initialize() {
		anchorPane.getChildren().add(mps);
		mscreen.setMparent(dialog);

		checkboxId.setSelected(LoginInfo.isSelected());

		username.setText(LoginInfo.getEmail());

		password.setText(LoginInfo.getPassword());

		dialog.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
				new Thread(new Runnable() {
					public void run() {
						HttpUtil.closs();
					}
				}).start();

			}
		});
	}

	@FXML
	public void cancel() {
		Platform.exit();
	}

	@FXML
	public void login() {

		if (getEmail() == null
				|| "".equals(getEmail())
				|| !getEmail()
						.matches(
								"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z0-9A-Z]{2,}$")
				|| getEmail().length() > 30) {
			MTooltip.show(dialog, username, "请输入正确的邮箱地址！");
			return;
		}

		if (getPassword() == null || "".equals(getPassword())
				|| getPassword() != null && !"".equals(getPassword())
				&& (getPassword().length() < 6 || getPassword().length() > 12)) {
			MTooltip.show(dialog, password, "请输入6-12位密码！");
			return;
		}

		if (checkboxId.isSelected()) {
			LoginInfo.setInfo(getEmail(), getPassword(), true);
		} else {
			LoginInfo.setInfo(getEmail(), "", false);
		}

		new Thread(new Runnable() {
			public void run() {

				Platform.runLater(new Runnable() {
					public void run() {
						mps.showTo(183, 155);
					}
				});

				try {

					Map<String, String> map = new HashMap<String, String>();

					map.put("email", getEmail());
					map.put("password", getPassword());

					String reqreturn = HttpUtil.post(60000,
							"http://localhost:8081/testB/user/login.do", map);

					@SuppressWarnings("unchecked")
					StatusEntity<String> sentity = new Gson().fromJson(
							reqreturn, StatusEntity.class);

					if (sentity.getStatus() == 200) {

						int ret = Integer.valueOf(sentity.getNodes().replace(
								"A", ""));

						switch (ret) {
						case 0:
							Platform.runLater(new Runnable() {
								public void run() {
									LoginInfo.setInfo(getEmail(), "", false);
									Message.showInfo(dialog, "用户或密码错误！", "信息");
								}
							});
							break;
						default:
							Platform.runLater(new Runnable() {
								public void run() {

									dialog.close();
									mscreen.jobDialog().show();
								}
							});
							break;
						}
					} else {
						Platform.runLater(new Runnable() {
							public void run() {
								LoginInfo.setInfo(getEmail(), "", false);
								Message.showError(dialog,
										"系统出错啦！可能它累了，让他休息一会吧 ):", "错误信息");
							}
						});
					}

				} catch (Exception e) {
					Platform.runLater(new Runnable() {
						public void run() {
							LoginInfo.setInfo(getEmail(), "", false);
							Message.showError(dialog, "网络请求失败!", "错误信息");
						}
					});
					// e.printStackTrace();
				}

				Platform.runLater(new Runnable() {
					public void run() {
						mps.hide();
					}
				});
			}

		}).start();
	}

	@FXML
	public void fogetPassword() {
		mscreen.updatePasswordDialog().show();
	}

	@FXML
	public void register() {
		mscreen.registerDialog().show();
	}

}