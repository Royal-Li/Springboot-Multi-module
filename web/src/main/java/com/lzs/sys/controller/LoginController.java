package com.lzs.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lzs.sys.entity.SysResource;
import com.lzs.sys.entity.SysUser;
import com.lzs.sys.service.SysResourceService;

@Controller
public class LoginController {

	@Autowired
	SysResourceService sysResourceService;
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login() {

		logger.info("后台  /login");
		
		return "/login";
	}

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap model, HttpServletRequest request) {
		
		logger.info("后台 /login 登录");
		try {
			Subject subject = SecurityUtils.getSubject();
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			subject.login(token);
			return "/index";
		} catch (AuthenticationException e) {
			model.put("message", e.getMessage());
		}
		
		return "/login";
	}
	
	@ResponseBody
	@RequestMapping("/findMenu")
	public List<SysResource> findMenu() {
		
		logger.info("后台 /findMenu查询菜单");
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		List<SysResource> List =  sysResourceService.findBySysUserId(user.getId());
		
		return List;
		
	}
}
