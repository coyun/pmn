package com.paimingniu.ui.login;

import java.util.HashMap;
import java.util.Map;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.paimingniu.ui.DialogController;
import com.paimingniu.ui.FXMLDialog;
import com.paimingniu.ui.MProgressIndicator;
import com.paimingniu.ui.MTooltip;
import com.paimingniu.ui.Message;
import com.paimingniu.ui.module.ScreensModule;
import com.paimingniu.util.HttpUtil;
import com.paimingniu.util.StatusEntity;
import com.paimingniu.util.URLInfo;

public class UpdatePasswordController implements DialogController {

	@Inject
	ScreensModule mscreen;

	private FXMLDialog dialog;

	private final MProgressIndicator mps = new MProgressIndicator();

	@FXML
	AnchorPane anchorPane;

	@FXML
	TextField username;

	@FXML
	PasswordField password;

	@FXML
	TextField code;

	@FXML
	Hyperlink codeLink;

	public String getEmail() {
		return username.getText();
	}

	public String getPassword() {
		return password.getText();
	}

	public String getCode() {
		return code.getText();
	}

	public void setDialog(FXMLDialog dialog) {
		this.dialog = dialog;
	}

	public void initialize() {
		anchorPane.getChildren().add(mps);
		mscreen.setMparent(dialog);
	}

	private boolean lock = true;

	@FXML
	public void submit() {

		if ("".equals(getEmail().trim())
				|| !getEmail()
						.matches(
								"^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z0-9A-Z]{2,}$")
				|| getEmail().length() > 30) {
			MTooltip.show(dialog, username, "请输入正确的邮箱地址！");
			return;
		}

		if ("".equals(getPassword()) || getPassword().length() < 6
				|| getPassword().length() > 12) {
			MTooltip.show(dialog, password, "请输入6-12位密码！");
			return;
		}

		if ("".equals(getCode()) || getCode().length() < 4
				|| getCode().length() > 4) {

			MTooltip.show(dialog, code, "请输入验证码！");

			return;
		}

		new Thread(new Runnable() {
			public void run() {

				Platform.runLater(new Runnable() {
					public void run() {
						mps.showTo(200, 135);
					}
				});

				try {

					Map<String, String> map = new HashMap<String, String>();

					map.put("email", getEmail());
					map.put("password", getPassword());
					map.put("code", getCode());

					String reqreturn = HttpUtil.post(60000,
							URLInfo.getRUL()+"user/updatePSW.do",
							map);

					@SuppressWarnings("unchecked")
					StatusEntity<String,?> sentity = new Gson().fromJson(
							reqreturn, StatusEntity.class);

					if (sentity.getCode() == 200) {

						int ret = Integer.valueOf(sentity.getStatus().replace(
								"A", ""));

						switch (ret) {
						case 0:
							Platform.runLater(new Runnable() {
								public void run() {
									Message.showInfo(dialog, "验证码错误！", "信息");
								}
							});
							break;
						case 1:
							Platform.runLater(new Runnable() {
								public void run() {
									Message.showInfo(dialog, "用户邮箱不存在，无法修改密码！",
											"信息");
								}
							});
							break;
						case 2:
							Platform.runLater(new Runnable() {
								public void run() {
									Message.showInfo(dialog, "密码修改失败！ ", "信息");
								}
							});
							break;

						default:
							Platform.runLater(new Runnable() {
								public void run() {
									Message.showInfoAfterClossParent(dialog,
											"密码成功！", "信息");
								}
							});
							break;
						}
					} else {
						Platform.runLater(new Runnable() {
							public void run() {
								Message.showError(dialog,
										"系统出错啦！可能它累了，让他休息一会吧 ):", "错误信息");
							}
						});
					}

				} catch (Exception e) {
					Platform.runLater(new Runnable() {
						public void run() {
							Message.showError(dialog, "网络请求失败!", "错误信息");
						}
					});
					e.printStackTrace();
				}

				Platform.runLater(new Runnable() {
					public void run() {
						mps.hide();
					}
				});
			}

		}).start();

	}

	String codeInfo = "获取验证码";

	@FXML
	public void codeLinkAction() throws InterruptedException {

		String ustr = getEmail();

		if ("".equals(ustr.trim())
				|| !ustr.matches("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-z0-9A-Z]{2,}$")
				|| ustr.length() > 30) {
			MTooltip.show(dialog, username, "请输入正确的邮箱地址！");
			return;
		}

		if (lock) {

			lock = false;

			Task<Void> progressTask = new Task<Void>() {
				@Override
				protected void succeeded() {
					super.succeeded();
					lock = true;
					codeInfo = "重新获取验证码";
					updateMessage("重新获取验证码");
				}

				@Override
				protected Void call() throws Exception {

					updateMessage(codeInfo);
					Platform.runLater(new Runnable() {
						public void run() {
							mps.showTo(190, 95);
						}
					});

					try {

						Map<String, String> map = new HashMap<String, String>();

						map.put("email", getEmail());

						String reqreturn = HttpUtil.post(60000,
								URLInfo.getRUL()+"email/code.do",
								map);

						@SuppressWarnings("unchecked")
						StatusEntity<Boolean,?> sentity = new Gson().fromJson(
								reqreturn, StatusEntity.class);

						if (!(sentity.getCode() == 200 && sentity.getStatus())) {

							Platform.runLater(new Runnable() {
								public void run() {
									mps.hide();
									Message.showInfo(dialog, "验证码获取失败！", "信息");
								}
							});
							lock = true;
							return null;
						}

					} catch (Exception e) {

						Platform.runLater(new Runnable() {
							public void run() {
								mps.hide();
								Message.showError(dialog, "网络请求失败!", "错误信息");
							}
						});
						e.printStackTrace();
						lock = true;
						return null;
					}

					Platform.runLater(new Runnable() {
						public void run() {
							mps.hide();
						}
					});

					int i = 60;
					while (i-- > 0) {
						updateMessage("已发往邮箱[" + i + "]秒");
						Thread.sleep(1000);
					}

					updateMessage("Finish");
					return null;
				}
			};

			codeLink.textProperty().bind(progressTask.messageProperty());
			new Thread(progressTask).start();

		}

	}

}
