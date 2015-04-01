package com.paimingniu.driver;

import java.util.HashSet;
import java.util.Set;

import org.openqa.selenium.WebDriver;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
public abstract class Driver {

	protected WebDriver driver = null;

	public String getAbspath() {
		String abspath = System.getProperty("user.dir") + "/libs/bin/";
		return abspath;
	}

	public WebDriver getDriver() {
		return this.driver;
	}

	public void killDriver() {
		try {
			Runtime.getRuntime().exec(
					"wmic process where name=\"idriver.exe\" call terminate");
		} catch (Exception e) {
		}
		try {
			Runtime.getRuntime().exec(
					"wmic process where name=\"cdriver.exe\" call terminate");
		} catch (Exception e) {
		}
	}

	public Set<String> getHandles() {
		try {
			return driver.getWindowHandles();
		} catch (Exception e) {}
		return new HashSet<String>();
	}

	public void stop() {
		if (driver != null) {

			for (String k : getHandles()) {
				try {
					driver.switchTo().window(k);
				} catch (Exception e) {
				}
				try {
					driver.close();
				} catch (Exception e) {
				}
			}

			try {
				driver.quit();
			} catch (Exception e) {
			}
			driver = null;
			killDriver();// 硬编码kill 进程
		}

	}

	public abstract void start();

}
