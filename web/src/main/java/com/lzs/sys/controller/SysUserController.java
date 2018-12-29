package com.lzs.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzs.sys.entity.SysUser;
import com.lzs.sys.service.SysRoleService;
import com.lzs.sys.service.SysUserService;

/**
 * @class AdminController
 * @author Jason
 * @description
 * @date Dec 17, 2018 3:55:56 PM
 */
@Controller
@RequestMapping(value="/user")
public class SysUserController {

	@Autowired
	SysUserService sysUserService;
	@Autowired
	SysRoleService sysRoleService;
	
	@RequestMapping("/index")
	public ModelAndView sysUserHtml(ModelAndView model) {
		
		model.setViewName("/user/list");
		return model;
	}
	
	/**
	 * @Description 带条件分页查询
	 * @author Jason
	 * @date Dec 27, 2018
	 * @param search
	 * @param order
	 * @param offset
	 * @param pageSize
	 * @param request
	 * @return
	 */
	@RequiresPermissions(value= {"user:manager"})
	@ResponseBody
	@RequestMapping("/getAdmin")
	public Map<String, Object> getAdmin(@RequestParam(value="search")String search, SysUser user,
			@RequestParam(value="order",required=false, defaultValue="asc")String order, 
			@RequestParam(value="offset", defaultValue="0")Integer offset, 
			@RequestParam(value="limit", defaultValue="10")Integer pageSize,
			HttpServletRequest request) {
		
		
		Page<SysUser> page =  sysUserService.getAllAdmin(user,order,offset,pageSize);
		Map<String,Object> response = new HashMap<>();
		response.put("total", page.getTotalElements());
		response.put("rows", page.getContent());
		return response;
		
	}
	
	
}
