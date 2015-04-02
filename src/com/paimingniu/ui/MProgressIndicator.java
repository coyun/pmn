package com.paimingniu.ui;

import javafx.scene.control.ProgressIndicator;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Mar 19, 2015
 * @version V1.0
 */
public class MProgressIndicator extends ProgressIndicator {

	public MProgressIndicator() {
		super.setVisible(false);
	}

	/**
	 * 设置元素的xy坐标
	 * 
	 * @param x
	 * @param y
	 */
	public void setLayout(double x, double y) {
		setLayoutX(x);
		setLayoutY(y);
	}

	/**
	 * 元素在指定位置显示
	 * 
	 * @param x
	 * @param y
	 */
	public void showTo(double x, double y) {
		setVisible(true);
		setLayoutX(x);
		setLayoutY(y);
	}

	/**
	 * 显示元素
	 */
	public void show() {
		setVisible(true);

	}

	/**
	 * 隐藏元素
	 */
	public void hide() {
		setVisible(false);
	}
}
