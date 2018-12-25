///**
// * @date 9:39:45 AM
// * ScheduledTask.java
// * Administrator
// * TODO
// */
//package com.lzs.sys.util;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.qs.education.bean.service.IEducationAdminService;
//
///**
// * @class 定时任务
// * @author Brooks
// * @description 
// * @date Nov 2, 2018
// */
///*@Component*/
//public class ScheduledTask {
//	private final Logger logger = Logger.getLogger(this.getClass());
//	/**
// 	 *每秒去验证，验证码发送超过一分钟自动删除
//	 * @author Brooks
//	 * @description 
//	 * @date Oct 17, 2018
//	 */
//    @Scheduled(fixedRate = 5000)
//    public void deleteOvertimeSMS(){
//        logger.info("deleteOvertimeSMS："+System.currentTimeMillis());
//        
//    }
//}
