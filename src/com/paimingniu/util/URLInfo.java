package com.paimingniu.util;

import java.io.InputStream;
import java.util.Properties;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月3日
 * @version V1.0
 */
public class URLInfo {
	
	private static Properties inLoad() {

		Properties prop = new Properties();// 属性集合对象
		try {
			InputStream in = URLInfo.class
					.getResourceAsStream("URLInfo.properties");
			prop.load(in);// 将属性文件流装载到Properties对象中
			in.close();// 关闭流
		} catch (Exception e) {
			//e.printStackTrace();
		}
		return prop;
		
	}
	
	public static String getRUL() {
		return (String) inLoad().get("url");
	}

}
