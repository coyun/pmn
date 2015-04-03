package com.paimingniu.ui;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import com.paimingniu.util.DESUtil;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年3月21日
 * @version V1.0
 */
public class LoginInfo {

	private final static String KEY = "PAIMINGNIU_KEY_888888";

	public static Properties inLoad() {

		Properties prop = new Properties();// 属性集合对象

		try {

			InputStream in = LoginInfo.class
					.getResourceAsStream("loginInfo.properties");
			prop.load(in);// 将属性文件流装载到Properties对象中
			in.close();// 关闭流

		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

	public static void setInfo(String email, String p) {

		Properties prop = new Properties();// 属性集合对象
		prop.setProperty("email", email);
		prop.setProperty("password", p);

		try {

			FileOutputStream fos = new FileOutputStream(new File(
					LoginInfo.class.getResource("loginInfo.properties")
							.getPath()).getPath());
			prop.store(fos, "Copyright (c) paimingniu ");
			fos.flush();
			fos.close();// 关闭流

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void setInfo(String email, String p, boolean isSelected) {

		Properties prop = new Properties();// 属性集合对象
		prop.setProperty("email", email);

		try {
			if (p != null && !"".equals(p.trim())) {
				String encryptData = DESUtil.encrypt(p, KEY);
				prop.setProperty("password", encryptData);
			}
		} catch (Exception e) {
			isSelected = false;
			// e1.printStackTrace();
		}

		prop.setProperty("isselected", "" + isSelected);

		try {

			FileOutputStream fos = new FileOutputStream(new File(
					LoginInfo.class.getResource("loginInfo.properties")
							.getPath()).getPath());
			prop.store(fos, "Copyright (c) paimingniu ");
			fos.flush();
			fos.close();// 关闭流

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static String getEmail() {

		return (String) inLoad().get("email");
	}

	public static String getPassword() {
		String psw = (String) inLoad().get("password");
		try {
			if (psw != null && !"".equals(psw.trim())) {
				psw = DESUtil.decrypt(psw, KEY);
			}
		} catch (Exception e) {
			psw="";
			// e.printStackTrace();
		}
		return psw;
	}

	public static boolean isSelected() {
		String b = (String) inLoad().get("isselected");
		return (b == null ? false : b.equals("true"));
	}

}
