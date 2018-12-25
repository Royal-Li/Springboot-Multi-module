/**
 * @date 4:15:26 PM
 * LocaleMessageSourceUtils.java
 * Administrator
 * TODO
 */
package com.lzs.sys.util;

import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;


/**
 * @class LocaleMessageSourceUtils
 * @author Brooks
 * @description 
 * @date Oct 26, 2018
 */
public class LocaleMessageSourceUtils {
	@Resource
	private MessageSource messageSource;
	
	public void add(){
		
	}
	/**
     *
     * @param code ：对应messages配置的key.
     * @param args : 数组参数.
     * @param defaultMessage : 没有设置key的时候的默认值.
     * @return
     */
	public String getMessage(String code,Object[] args,String defaultMessage){
		//这里使用比较方便的方法，不依赖request.
       Locale locale = LocaleContextHolder.getLocale();
       return messageSource.getMessage(code, args, defaultMessage, locale);
	}
	
}
