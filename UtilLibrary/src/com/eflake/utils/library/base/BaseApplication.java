package com.eflake.utils.library.base;

import android.app.Application;

public class BaseApplication extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		setUpUtils();
	}

	private void setUpUtils() {
		
	}
}
