package com.lzs.sys.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

public class BaseController {
	@Autowired
	protected HttpServletRequest request;

	@Autowired
	protected HttpServletResponse response;

	/**
	 * 带参重定向
	 *
	 * @param path
	 * @return
	 */
	protected String redirect(String path) {
		return "redirect:" + path;
	}

	/**
	 * 不带参重定向
	 *
	 * @param response
	 * @param path
	 * @return
	 */
	protected String redirect(HttpServletResponse response, String path) {
		try {
			response.sendRedirect(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @Description 获取分页请求 默认升序排序
	 * @author Jason
	 * @date Jan 2, 2019
	 * @return
	 */
	protected PageRequest getPageRequest() {
		int page = 0;
		int size = 10;
		int offset = 0;
		offset = Integer.parseInt(request.getParameter("offset"));
		size = Integer.parseInt(request.getParameter("limit"));
		page = offset / size;
		Sort sort = new Sort(Direction.ASC);
		// PageRequest 过时了
		// PageRequest pageRequest = new PageRequest(page, size, sort);
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return pageRequest;
	}

	/**
	 * 
	 * @Description
	 * @author Jason
	 * @date Jan 2, 2019
	 * @param sortOrder 排序规则
	 * @param sortName 排序列
	 * @return
	 */
	protected PageRequest getPageRequest(String sortOrder, String sortName) {
		int page = 0;
		int size = 10;
		int offset = 0;
		Sort sort = null;
		try {
			if (sortOrder.equalsIgnoreCase("desc")) {
				sort = new Sort(Direction.DESC, sortName);
			} else {
				sort = new Sort(Direction.ASC, sortName);
			}
			offset = Integer.parseInt(request.getParameter("offset"));
			size = Integer.parseInt(request.getParameter("limit"));
			page = offset / size;
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageRequest pageRequest = PageRequest.of(page, size, sort);
		return pageRequest;
	}

}
