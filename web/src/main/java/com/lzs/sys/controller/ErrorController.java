package com.lzs.sys.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 404 500 页面跳转
 * 
 * @class ErrorController
 * @author Jason
 * @description
 * @date Dec 28, 2018 3:02:05 PM
 */
@Controller
public class ErrorController {

	private static Logger logger = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value = { "/500" })
	public ModelAndView serverError(ModelAndView model) {
		logger.info("后台 /500 页面出错");
		model.addObject("msg", "后台消息  页面出错了");
		model.setViewName("/error/500");
		return model;
	}
}
