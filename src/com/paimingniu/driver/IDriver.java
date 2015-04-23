package com.paimingniu.driver;

import java.awt.AWTException;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;

import sun.org.mozilla.javascript.internal.JavaScriptException;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
public class IDriver extends Driver {

	public void start() {
		if (driver == null) {
			String abspath = getAbspath() + "idriver.exe";
			System.setProperty("webdriver.ie.driver", abspath);
			InternetExplorerDriverService service = InternetExplorerDriverService
					.createDefaultService();

			DesiredCapabilities capabilities = DesiredCapabilities
					.internetExplorer();

			capabilities
					.setCapability(
							InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
							true);

			capabilities.setCapability(
					InternetExplorerDriver.INITIAL_BROWSER_URL, "about:blank");

//			capabilities.setCapability(CapabilityType.VERSION, "8");
//
//			capabilities.setCapability(
//					InternetExplorerDriver.IGNORE_ZOOM_SETTING, false);
//
//			capabilities.setCapability(
//					InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
//
			capabilities.setCapability(
					InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);

			try {
				driver = new InternetExplorerDriver(service, capabilities);
				driver.manage().window().setSize(new Dimension(900, 700));
//				Point p=new Point(0,10000);
//				driver.manage().window().setPosition(p);
			} catch (Exception e) {
			}

		}
	}

	public void m1() {

	}

	public void openTab(String url) {
		String script = "var d=document,a=d.createElement('a');a.target='_blank';a.href='%s';a.innerHTML='.';d.body.appendChild(a);return a";
		Object element = trigger(String.format(script, url));
		if (element instanceof WebElement) {
			WebElement anchor = (WebElement) element;

			Actions oAction = new Actions(driver);

			oAction.moveToElement(anchor).keyDown(Keys.CONTROL).click(anchor)
					.keyUp(Keys.CONTROL).perform();

			// anchor.click();
			trigger("var a=arguments[0];a.parentNode.removeChild(a);", anchor);
		} else {
			throw new JavaScriptException(element, "Unable to open tab", 1);
		}
	}

	public void trigger(String script, WebElement element) {
		((JavascriptExecutor) driver).executeScript(script, element);
	}

	public Object trigger(String script) {
		return ((JavascriptExecutor) driver).executeScript(script);
	}

	public static void main(String[] args) throws InterruptedException,
			AWTException {
		IDriver mc = new IDriver();
		mc.start();
		String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN); 
		
		

		// Perform Ctrl + Tab to focus on new Tab window
		new Actions(mc.driver).sendKeys(Keys.chord(Keys.CONTROL, Keys.RETURN)).perform();

		System.out.println(selectLinkOpeninNewTab);
		//mc.driver.navigate().to("http://www.baidu.com");
		//mc.driver.findElement(By.cssSelector("Body")).sendKeys(selectLinkOpeninNewTab);
		System.out.println(mc.driver.getWindowHandles().size());
//		 ((JavascriptExecutor) mc.driver)
//		 .executeScript(
//		 "var div=document.createElement('a'); div.setAttribute('id','totarget'); div.href='http://www.baidu.com';  document.body.appendChild(div);return true",
//		 mc.driver.findElement(By.tagName("html")));
//		 WebElement cc = mc.driver.findElement(By.id("totarget"));
//		 cc.sendKeys(selectLinkOpeninNewTab);
//		//
//		//mc.openTab("http://www.baidu.com");
//		// //Object es = ((JavascriptExecutor)
//		// mc.driver).executeScript("return document.getElementById('totarget')","");
//		// ((JavascriptExecutor)
//		// mc.driver).executeScript("arguments[0].click();",cc);
//		//
//
//		String winHandleBefore2 = mc.driver.getWindowHandle();
//		System.out.println(winHandleBefore2);
//		Thread.sleep(5000);
//
//		// Robot r = new Robot();
//		// r.keyPress(KeyEvent.VK_CONTROL);
//		// r.keyPress(KeyEvent.VK_T);
//		// r.keyRelease(KeyEvent.VK_CONTROL);
//		// r.keyRelease(KeyEvent.VK_T);
//		// Thread.sleep(5000);
//		mc.driver.get("http://www.taobao.com");
//
//		String winHandleBefore = mc.driver.getWindowHandle();
//		System.out.println(winHandleBefore);
//		Thread.sleep(2000);
//		// window.open("","_newtab")
//		// mc.driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL +
//		// "t");//opens new tab
//		// Store the current window handle
//
//		Thread.sleep(2000);
//
//		WebElement q = mc.driver.findElement(By.id("q"));
//		if (q != null)
//			q.sendKeys("男装");
//
//		Thread.sleep(2000);
//
//		WebElement btn = mc.driver.findElement(By.className("btn-search"));
//		if (btn != null)
//			btn.click();
//
//		Thread.sleep(2000);
//
//		WebElement link = mc.driver.findElement(By
//				.xpath("//a[contains(@href,'42690338601')]"));
//		if (link != null) {
//			// JavascriptExecutor js = (JavascriptExecutor) mc.driver;
//			// js.executeScript("arguments[0].target=''", link);
//			link.click();
//		}
//
//		long h = (Long) ((JavascriptExecutor) mc.driver).executeScript(
//				"return document.body.scrollHeight", "");
//		int y = 0;
//		for (int i = 0; i < 5; i++) {
//			int y2 = Math.round(h);
//			((JavascriptExecutor) mc.driver).executeScript("window.scrollTo("
//					+ y + "," + y2 + ");return true", "");
//			y = y2;
//			Thread.sleep(Math.round(2000));
//		}
//
//		System.out.println(h + "=================");
//
//		Thread.sleep(2000);

		System.out.println(mc.getHandles().size());

		Thread.sleep(5000);
		mc.stop();

	}

}
