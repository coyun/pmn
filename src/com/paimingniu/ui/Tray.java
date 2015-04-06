package com.paimingniu.ui;

import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javafx.application.Platform;
import javafx.stage.Stage;

import javax.imageio.ImageIO;

import com.paimingniu.image.IMG;
import com.paimingniu.util.HttpUtil;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月3日
 * @version V1.0
 */
public class Tray {

	private TrayIcon trayIcon;

	public void enableTray(final Stage stage) {

		PopupMenu popupMenu = new PopupMenu();

		java.awt.MenuItem openItem = new java.awt.MenuItem("显示");
		java.awt.MenuItem hideItem = new java.awt.MenuItem("最小化");
		java.awt.MenuItem quitItem = new java.awt.MenuItem("退出");

		ActionListener acl = new ActionListener() {

			public void actionPerformed(java.awt.event.ActionEvent e) {

				java.awt.MenuItem item = (java.awt.MenuItem) e.getSource();
				Platform.setImplicitExit(false); // 多次使用显示和隐藏设置false

				if (item.getLabel().equals("退出")) {

					SystemTray.getSystemTray().remove(trayIcon);

					HttpUtil.closs();
					Platform.exit();

					return;
				}

				if (item.getLabel().equals("显示")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							stage.show();
						}
					});
				}

				if (item.getLabel().equals("最小化")) {
					Platform.runLater(new Runnable() {
						@Override
						public void run() {
							stage.hide();
						}
					});
				}

			}

		};

		// 双击事件方法
		MouseListener sj = new MouseListener() {

			public void mouseReleased(MouseEvent e) {
			}

			public void mousePressed(MouseEvent e) {
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {

				Platform.setImplicitExit(false); // 多次使用显示和隐藏设置false

				if (e.getClickCount() == 1) {

					if (!stage.isShowing()) {
						Platform.runLater(new Runnable() {
							@Override
							public void run() {
								stage.show();
							}
						});
					}
				}

				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						stage.setIconified(false);
						stage.requestFocus();
						stage.toFront();
					}
				});

			}
		};

		openItem.addActionListener(acl);
		quitItem.addActionListener(acl);
		hideItem.addActionListener(acl);

		popupMenu.add(openItem);
		popupMenu.add(hideItem);
		popupMenu.add(quitItem);

		try {

			SystemTray tray = SystemTray.getSystemTray();

			BufferedImage image = ImageIO.read(IMG.class
					.getResourceAsStream("niu-16x16.png"));

			trayIcon = new TrayIcon(image, "排名牛", popupMenu);

			trayIcon.setImageAutoSize(true);

			trayIcon.setToolTip("排名牛\n正在拼命工作中");
			tray.add(trayIcon);

			trayIcon.addMouseListener(sj);

		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

}
