package com.paimingniu.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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
	

		try{
		driver = new InternetExplorerDriver(service, capabilities);
		driver.manage().window().setSize(new Dimension(900,700)); 
		}catch(Exception e){}
		
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		Driver mc = new IDriver();
		mc.start();
		
		mc.driver.get("http://www.taobao.com");
		
		Thread.sleep(2000);
		
		WebElement q = mc.driver.findElement(By.id("q"));
		if(q!=null)q.sendKeys("男装");
		
		Thread.sleep(2000);
		
		WebElement btn = mc.driver.findElement(By.className("btn-search"));
		if(btn!=null)btn.click();
		
		Thread.sleep(2000);
		
		
		WebElement link = mc.driver.findElement(By.xpath("//a[contains(@href,'42690338601')]"));
		if(link!=null){
			JavascriptExecutor js = (JavascriptExecutor) mc.driver;
			js.executeScript("arguments[0].target=''",link);
			link.click();
		}
		
		long h= (Long) ((JavascriptExecutor) mc.driver).executeScript("return document.body.scrollHeight", "");
		
		((JavascriptExecutor) mc.driver).executeScript("window.scrollTo(0,200);return true", "");
		
		System.out.println(h+"=================");
		
		Thread.sleep(2000);
		
		System.out.println(mc.getHandles().size());
		
			Thread.sleep(5000);
			mc.stop();
		
	}

}
