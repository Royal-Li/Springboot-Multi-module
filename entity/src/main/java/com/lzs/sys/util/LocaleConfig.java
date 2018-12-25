/**
 * @date 4:01:51 PM
 * LanguageUtils.java
 * Administrator
 * TODO
 */
package com.lzs.sys.util;

import java.util.Locale;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * @class 语言解析器
 * @author Brooks
 * @description 
 * @date Oct 26, 2018
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class LocaleConfig extends WebMvcConfigurerAdapter {

	/**
	 * 设置系统的默认语言
	 * @author Brooks
	 * @description 
	 * @date Oct 26, 2018
	 */
    @Bean
    public SessionLocaleResolver localeResolver() {
        SessionLocaleResolver slr = new SessionLocaleResolver();
        // 默认语言
        slr.setDefaultLocale(Locale.CHINA);
        return slr;
    }
	/**
	 * 添加切换语言过滤器
	 * @author Brooks
	 * @description 
	 * @date Oct 26, 2018
	 */
    @Bean
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        // 参数名
        lci.setParamName("lang");
        return lci;
    }
	/**
	 * 添加以上过滤器并注册到spring mvc中
	 * @author Brooks
	 * @description 
	 * @date Oct 26, 2018
	 */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
