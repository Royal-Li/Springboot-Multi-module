///**
// * @date 1:43:32 PM
// * LogUtils.java
// * Administrator
// * TODO
// */
//package com.lzs.sys.util;
//
//import java.util.Date;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.apache.log4j.Logger;
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.annotation.AfterReturning;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;
//
//import com.qs.education.bean.beans.EducationAdminResponse;
//import com.qs.education.bean.commons.CommonDate;
//import com.qs.education.bean.dao.IEducationLogDao;
//import com.qs.education.bean.entity.EducationLog;
//
///**
// * @class 记录http请求到数据库其中包括：http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
// * @author Brooks
// * @description 
// * @date Oct 18, 2018
// */
//
//@Aspect
//@Component
//public class LogUtils {
//	private final static Logger logger = Logger.getLogger(LogUtils.class);
//	@Autowired
//	private IEducationLogDao ieducationLog;
//	 /**
//     * @Before 在方法执行之前执行
//     * */
//    @Before("execution(public * com.qs.education.controller..*(..))")
//    public void log(JoinPoint joinPoint){
//    	 logger.info("doBefore");
//         //记录http请求
//         ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//         HttpServletRequest request = attributes.getRequest();
//         //从request中获取http请求的url/请求的方法类型／响应该http请求的类方法／IP地址／请求中的参数
//         //url
//         String url = request.getRequestURI();
//         logger.info("url :"+url);
//
//         //method
//         String method = request.getMethod();
//         logger.info("method :"+method);
//
//         //ip
//         String ip = request.getRemoteAddr();
//         logger.info("ip :"+ip);
//
//         //类方法
//         String class_method = joinPoint.getSignature().getDeclaringTypeName()+
//                 "."+joinPoint.getSignature().getName();
//         logger.info("class_method :"+class_method);
//         //参数
//         String params = "";
//         Map<String,String[]> maps = request.getParameterMap();
//         for (Map.Entry<String, String[]> entry : maps.entrySet()) {
//        	 String innerParams = "";
//        	 for (String parVal : entry.getValue()) {
//        		 if(!"".equals(parVal)){
//        			 innerParams += parVal + ";";
// 				}
//			}
//        	params += entry.getKey() + " : " + innerParams;
//         }
//         EducationAdminResponse admin = (EducationAdminResponse) request.getSession().getAttribute("ADMIN");
//         Long operationId = admin != null ? admin.getId() : 0;
// 		logger.info("args: "+params);
// 		logger.info("opration id: "+operationId);
// 		if(operationId != 0){
// 			EducationLog educationLog = new EducationLog();
// 	 		educationLog.setClass_method(class_method);
// 	 		educationLog.setIp(ip);
// 	 		educationLog.setMethod(method);
// 	 		educationLog.setOperation_id(operationId);
// 	 		educationLog.setParams(params);
// 	 		educationLog.setUrl(url);
// 	 		educationLog.setOperation_date(CommonDate.getInstance(new Date(CommonDate.LONG)).toString());
// 	 		ieducationLog.saveEducationLog(educationLog);
// 		}
//
//    }
//
//    @AfterReturning(returning = "rvt",pointcut = "execution(public * com.qs.education.controller..*(..))") 
//    public void doAfterReturning(Object rvt) { 
//        //记录http请求
//        ServletRequestAttributes attributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//    	 @SuppressWarnings("unused")
//		EducationAdminResponse admin = (EducationAdminResponse) request.getSession().getAttribute("ADMIN");
//    	logger.info("return : "+rvt);
//    }
//}
