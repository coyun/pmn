package com.paimingniu.driver;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * 操作集合
 * 
 * @author yangkeyun
 *
 */

public class Operation {
	
	private static String initWindow;
	private WebDriver driver;
	private Actions action;

	public Operation(WebDriver driver) {
		this.driver = driver;
		action = new Actions(this.driver);
		initWindow = getCurrentWindowId();
	}
	
	/**
	 * 清空cookies
	 */
	public void deleteAllCookies(){
		this.driver.manage().deleteAllCookies();
	}
	

	/**
	 * 获取当前焦点窗口驱动
	 * 
	 * @return
	 */
	public String getCurrentWindowId() {
		return this.driver.getWindowHandle();
	}

	/**
	 * 切换到目标窗口驱动
	 * 
	 * @param id
	 */
	public WebDriver switchToWindow(String id) {
		this.driver = driver.switchTo().window(id);
		return this.driver;
	}

	/**
	 * 返回初始化窗口
	 */
	public WebDriver switchToInitWindow() {
		this.driver = driver.switchTo().window(initWindow);
		return this.driver;
	}

	/**
	 * 获取本对象下的驱动
	 * 
	 * @return
	 */
	public WebDriver getDriver() {
		return driver;
	}

	/**
	 * 给本对象驱动赋值
	 * 
	 * @param driver
	 */
	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 鼠标移动到指定元素位置
	 * 
	 * @param we
	 */
	public boolean moveTo(WebElement we) {
		try {
			action.moveToElement(we).perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 鼠标移动到指定元素位置且单价元素
	 * 
	 * @param we
	 */
	public boolean actionDo(WebElement we) {
		try {
			action.moveToElement(we);
			waitTime(((int) Math.random() * 216 + 19));
			action.click().perform();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 停留时间
	 * 
	 * @param times
	 */
	public void waitTime(int times) {
		try {
			Thread.sleep(times);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 校验元素是否存在
	 * 
	 * @param selector
	 * @return
	 */
	public boolean isWebElementExist(By selector) {
		try {
			WebElement txtbox = driver.findElement(selector);
			return (txtbox != null);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 向对象输入数据
	 * 
	 * @param by
	 * @param value
	 * @param times
	 *            输入数据前等待的时间
	 */
	public boolean sendKeys(By by, String value, int times) {
		waitTime(times);
		try {
			WebElement we = driver.findElement(by);
			moveTo(we);
			we.sendKeys(value);
			return true;
		} catch (Exception e) {
			
			return false;
		}
	}

	/**
	 * 回车键
	 * 
	 * @param by
	 * @param times
	 *            按下回车键前等待的时间
	 */
	public boolean enter(By by, int times) {
		waitTime(times);
		try {
			driver.findElement(by).sendKeys(Keys.ENTER);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 点击指定元素
	 * 
	 * @param by
	 * @param times
	 *            点击前等待的时间
	 */
	public boolean click(By by, int times) {
		try {
			waitTime(times);
			actionDo(driver.findElement(by));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 点击包含某个关键字的元素
	 * 
	 * @param by
	 * @param text
	 * @param times
	 *            点击前等待的时间
	 * @return
	 */
	public boolean clickElementContainingText(By by, String text, int times) {
		List<WebElement> elementList = driver.findElements(by);
		for (WebElement e : elementList) {
			if (e.getText().contains(text)) {
				waitTime(times);
				actionDo(e);
				return true;
			}
		}
		return false;
	}

	/**
	 * 链接单击和文本指定包含有关键字的元素
	 * 
	 * @param key
	 * @param text
	 * @param times
	 * @return
	 */
	public boolean clickHrefContKeyAndContText(String key, String text,
			int times) {
		try {
			WebElement we = driver.findElement(By.xpath("//a[contains(@href,'"
					+ key + "')]"));
			we = we.findElement(By.linkText(text));
			
			waitTime(times);
			actionDo(we);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 单击指定文本内容的链接元素
	 * 
	 * @param text
	 * @param times
	 * @return
	 */
	public boolean clickHrefByText(String text, int times) {
		try {
			List<WebElement> weList =  driver.findElements(By.tagName("a"));
			for(WebElement we:weList){
				System.out.println(we+"====");
				if(text.equals(we)){
					waitTime(times);
					actionDo(we);
					return true;
				}
			}
			
		} catch (Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
		
		return false;
	}

	/**
	 * 判断是否存在包含指定关键字的链接
	 * 
	 * @param key
	 * @return
	 */
	public boolean isHrefContainsKey(String key) {
		try {
			WebElement we = driver.findElement(By.xpath("//a[contains(@href,'"
					+ key + "')]"));
			return (we != null);
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * 点击包含有指定关键字的链接元素
	 * 
	 * @param key
	 * @param times
	 *            点击前等待的时间
	 * @return
	 */
	public boolean clickHrefContainsKey(String key, int times) {
		WebElement we = driver.findElement(By.xpath("//a[contains(@href,'"
				+ key + "')]"));
		if (we != null) {
			waitTime(times);
			try {
				actionDo(we);
				return true;
			} catch (Exception e) {
				return false;
			}
		}
		return false;
	}

	/**
	 * 获取链接中包含有指定关键字的元素对象
	 * 
	 * @param key
	 * @return
	 */
	public WebElement getHrefContainsKey(String key) {
		WebElement we = driver.findElement(By.xpath("//a[contains(@href,'"
				+ key + "')]"));
		return we;
	}

	/**
	 * 获取元素链接
	 * 
	 * @param by
	 * @return
	 */
	public String getLinkUrl(By by) {
		try {
			return driver.findElement(by).getAttribute("href");
		} catch (Exception e) {
			return "";
		}
	}

	/**
	 * 获取元素包括的文本内容
	 * 
	 * @param by
	 * @return
	 */
	public String getWebText(By by) {
		try {
			return driver.findElement(by).getText();
		} catch (Exception e) {
			return "";
		}
	}

}
