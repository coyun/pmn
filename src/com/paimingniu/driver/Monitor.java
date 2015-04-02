package com.paimingniu.driver;

/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
public class Monitor {

	private boolean isRun = true;
	private static final int SLEEP_TIME = 1200;

	public void start(final Driver driver) {

		new Thread(new Runnable() {
			public void run() {
				isRun = true;
				while (isRun) {
					// if (driver.getHandles().size() == 0) {
					// isRun = false;
					// driver.stop();
					// }
					try {
						Thread.sleep(SLEEP_TIME);
					} catch (InterruptedException e) {
					}
				}
			}
		}).start();
	}

}
