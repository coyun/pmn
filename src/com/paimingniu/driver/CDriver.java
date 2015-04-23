package com.paimingniu.driver;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
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

	public static void main(String[] args) throws InterruptedException {
		Driver mc = new CDriver();
		mc.start();
		
		mc.driver.get("http://www.taobao.com");
		
		System.out.println(mc.getHandles().size());
		
		
		
		WebElement q = mc.driver.findElement(By.id("q"));
		if (q != null)
			q.sendKeys("男装");

		Thread.sleep(2000);

		WebElement btn = mc.driver.findElement(By.className("btn-search"));
		if (btn != null)
			btn.click();

		Thread.sleep(2000);

		WebElement link = mc.driver.findElement(By
				.xpath("//a[contains(@href,'42690338601')]"));
		if (link != null) {
			// JavascriptExecutor js = (JavascriptExecutor) mc.driver;
			// js.executeScript("arguments[0].target=''", link);
			link.click();
		}

		long h = (Long) ((JavascriptExecutor) mc.driver).executeScript(
				"return document.body.scrollHeight", "");
		int y = 0;
		for (int i = 0; i < 5; i++) {
			int y2 = Math.round(h);
			((JavascriptExecutor) mc.driver).executeScript("window.scrollTo("
					+ y + "," + y2 + ");return true", "");
			y = y2;
			Thread.sleep(Math.round(2000));
		}

		System.out.println(h + "=================");

		
		
	 
			Thread.sleep(5000);
			mc.stop();
	 
	}

}
