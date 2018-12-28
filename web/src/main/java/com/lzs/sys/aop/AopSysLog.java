/**
 * @date 1:43:32 PM
 * LogUtils.java
 * Administrator
 * TODO
 */
package com.lzs.sys.aop;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.lzs.sys.entity.SysLog;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.repository.SysLogRepository;
import com.lzs.sys.util.DateUtil;

/**
 * @class 记录http请求到数据库其中包括：http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
 * @author Brooks
 * @description
 * @date Oct 18, 2018
 */

@Aspect
@Component
public class AopSysLog {
	private final static Logger logger = LoggerFactory.getLogger(AopSysLog.class);
	
	//直接对dao层进行操作
	@Autowired
	private SysLogRepository sysLogRepository;

	/**
	 * @Before 在方法执行之前执行
	 */
	@Before("execution(public * com.lzs.sys.controller..*(..))")
	public void log(JoinPoint joinPoint) {
		logger.info("doBefore");
		// 记录http请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		// 从request中获取http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
		// url
		String url = request.getRequestURI();
		logger.info("url :" + url);

		// method
		String method = request.getMethod();
		logger.info("method :" + method);

		// ip
		String ip = request.getRemoteAddr();
		logger.info("ip :" + ip);

		// 类方法
		String class_method = joinPoint.getSignature().getDeclaringTypeName() + "."
				+ joinPoint.getSignature().getName();
		logger.info("class_method :" + class_method);
		// 参数
		String params = "";
		Map<String, String[]> maps = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : maps.entrySet()) {
			String innerParams = "";
			for (String parVal : entry.getValue()) {
				if (!"".equals(parVal)) {
					innerParams += parVal + ";";
				}
			}
			params += entry.getKey() + " : " + innerParams;
		}
		/*SysUser loginUser = (SysUser) SecurityUtils.getSubject().getPrincipal();*/
		SysUser loginUser = (SysUser) request.getSession().getAttribute("ADMIN");
		Long operationId = (long) (loginUser != null ? loginUser.getId() : 0);
		logger.info("args: " + params);
		logger.info("opration id: " + operationId);
		if (operationId != 0) {
			SysLog sysLog = new SysLog();
			sysLog.setClass_method(class_method);
			sysLog.setIp(ip);
			sysLog.setMethod(method);
			sysLog.setOperation_id(operationId);
			sysLog.setParams(params);
			sysLog.setUrl(url);
			sysLog.setOperation_date(DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss"));
			sysLogRepository.save(sysLog);
		}

	}

	@AfterReturning(returning = "result", pointcut = "execution(public * com.lzs.sys.controller..*(..))")
	public void doAfterReturning(Object result) {
		// 记录http请求
		/*ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();*/
		//@SuppressWarnings("unused")
		/*SysUser loginUser = (SysUser) request.getSession().getAttribute("ADMIN");*/
		//SysUser loginUser = (SysUser) SecurityUtils.getSubject().getPrincipal();
		logger.info("return : " + result);
	}
}
