package com.lzs.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.session.HttpServletSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzs.sys.entity.SysResource;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.service.SysResourceService;

/**
 * 登录Controller
 * @class LoginController
 * @author Jason
 * @description
 * @date Dec 29, 2018 4:55:12 PM
 */
@Controller
public class LoginController {

	@Autowired
	SysResourceService sysResourceService;
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {

		logger.info("后台  /login 跳转登录");
		return "/login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model, HttpServletRequest request) {
		
		logger.info("后台 /login 登录");
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			SysUser loginUser = (SysUser) subject.getPrincipal();
			request.getSession().setAttribute("LoginUser", loginUser);
			System.out.println("Session Id 是："+request.getSession().getId());
			return "/main";
		} catch (AuthenticationException e) {
			model.put("message", e.getMessage());
		}
		return "/login";
	}
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView model) {
		logger.info("后台 /index  跳转后台主界面");
		model.setViewName("/index");
		return model;
	}
	
	@ResponseBody
	@RequestMapping("/findMenu")
	public List<SysResource> findMenu() {

		logger.info("后台 /findMenu查询菜单");
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		List<SysResource> List =  sysResourceService.findBySysUserId(user.getId());
		return List;
	}
	
	@RequestMapping("/createSession")
	public void createSession() {
		
		logger.info("后台 /createSession");
	}
	
	@ResponseBody
	/*不能起名/error  basicErrorController 使用/error统一处理错误信息
	@RequestMapping("/error")*/
	@RequestMapping("/makeError")
	public void makeError() {
		
		logger.error("后台 /error");
		int a = 1/0;
	}
}
