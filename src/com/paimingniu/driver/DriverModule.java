package com.paimingniu.driver;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.Scopes;
import com.google.inject.name.Names;


/**
 * @description
 * @author 杨克运
 * @email coyun@qq.com
 * @date Feb 27, 2015
 * @version V1.0
 */
public class DriverModule implements Module {

	public void configure(Binder binder) {
		binder.bind(Driver.class).annotatedWith(Names.named("idriver"))
				.to(IDriver.class).in(Scopes.SINGLETON);
		binder.bind(Driver.class).annotatedWith(Names.named("cdriver"))
				.to(CDriver.class).in(Scopes.SINGLETON);
	}

}
