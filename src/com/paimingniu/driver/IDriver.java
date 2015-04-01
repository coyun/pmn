package com.paimingniu.driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;



/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
public class IDriver extends Driver{

	public void start() {
		if(driver==null){
		String abspath = getAbspath() + "idriver.exe";
		System.setProperty("webdriver.ie.driver", abspath);
		InternetExplorerDriverService service = InternetExplorerDriverService.createDefaultService();
		
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		
		capabilities.setCapability(InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");
		
		capabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
		
		capabilities.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
		
		capabilities.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
	
//		capabilities.setCapability(InternetExplorerDriver.FORCE_CREATE_PROCESS, true);  
//		capabilities.setCapability(InternetExplorerDriver.IE_SWITCHES, "-minimize");
		try{
		driver = new InternetExplorerDriver(service, capabilities);
		driver.manage().window().setSize(new Dimension(600, 400)); 
		}catch(Exception e){}
		
		}
	}
	
	public static void main(String[] args) {
		Driver mc = new IDriver();
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
