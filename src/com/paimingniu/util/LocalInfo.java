package com.paimingniu.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date 2015年4月2日
 * @version V1.0
 */
public class LocalInfo {

	/**
	 * 获取ip
	 * 
	 * @return
	 */
	public static String getIp() {
		try {
			
			return InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			// e.printStackTrace();
		}
		return null;
	}

	/**
	 * 获取mac
	 * 
	 * @return
	 */
	public static String getMac() {

		try {
			
			byte[] mac = NetworkInterface.getByInetAddress(InetAddress.getLocalHost()).getHardwareAddress();
			StringBuffer sb = new StringBuffer("");
			for (int i = 0; i < mac.length; i++) {
				// 字节转换为整数
				int temp = mac[i] & 0xff;
				String str = Integer.toHexString(temp);
				if (str.length() == 1) {
					sb.append("0" + str);
				} else {
					sb.append(str);
				}
			}
			return sb.toString().toUpperCase();
		} catch (Exception e) {
			// e.printStackTrace();
		}
		return null;
	}

}
