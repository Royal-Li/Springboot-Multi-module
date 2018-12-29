package com.lzs.sys.config.listener;


import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;

@WebListener
public class MyHttpSessionListener implements HttpSessionListener {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public void sessionCreated(HttpSessionEvent se) {

		logger.info("创建了一个Session");
		HttpSessionListener.super.sessionCreated(se);
		
	}
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		
		logger.info("销毁了一个Session");
		HttpSessionListener.super.sessionDestroyed(se);
		
	}
	
	//@webListener 要在启动类加上 @ServletComponentScan  
	//加上weblistener 就不用再MyWebConfig里面配置了
}
