package com.lzs.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.lzs.sys.entity.Admin;
import com.lzs.sys.service.AdminService;

/**
 * @class AdminController
 * @author Jason
 * @description
 * @date Dec 17, 2018 3:55:56 PM
 */
@Controller
@RequestMapping(value="/admin")
public class AdminController {

	@Autowired
	AdminService adminService;
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView model) {
		model.setViewName("/admin/list");
		return model;
		
	}
	
	
	@ResponseBody
	@RequestMapping("/getAdmin")
	public Map<String, Object> getAdmin(@RequestParam(value="search")String search, 
			@RequestParam(value="order",required=false, defaultValue="asc")String order, 
			@RequestParam(value="offset", defaultValue="0")Integer offset, 
			@RequestParam(value="limit", defaultValue="10")Integer pageSize) {
		
		System.out.println(search+"|"+order+"|"+offset+"|"+pageSize+"|");
		Page<Admin> page =  adminService.getAllAdmin(order,offset,pageSize);
		Map<String,Object> response = new HashMap<>();
		response.put("total", page.getTotalElements());
		response.put("rows", page.getContent());
		return response;
		
	}
}
