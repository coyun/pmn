package com.paimingniu.driver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;



/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
public class CDriver extends Driver {

	public void start() {
		if (driver == null) {
			
			String abspath = getAbspath() + "cdriver.exe";
			
			System.setProperty("webdriver.chrome.driver", abspath);
			
			ChromeOptions options = new ChromeOptions();
			
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
		try{	
			driver = new ChromeDriver(capabilities);
		}catch(Exception e){}
		
		}
	}

	public static void main(String[] args) {
		Driver mc = new CDriver();
		mc.start();
		System.out.println(mc.getHandles().size());
		try {
			Thread.sleep(5000);
			mc.stop();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
