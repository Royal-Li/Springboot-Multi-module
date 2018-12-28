package com.lzs.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice extends ResponseEntityExceptionHandler {

	/*
	 * @ExceptionHandler(Exception.class) ResponseEntity<?>
	 * handleControllerException(HttpServletRequest request, Throwable ex) {
	 * HttpStatus status = getStatus(request); return new ResponseEntity<>(new
	 * CustomErrorType(status.value(), ex.getMessage()), status); }
	 */

	@ExceptionHandler(Exception.class)
	public ModelAndView handleControllerException(HttpServletRequest request, Throwable ex) {
		HttpStatus status = getStatus(request);
		ex.printStackTrace();
		/*Map<String, Object> map = new HashMap<>();*/
		/*map.put("msg", "error");
		request.setAttribute("javax.servlet.error.status_code", 400);*/
		ModelAndView model = new ModelAndView("/error/500");
		model.addObject("msg",ex.toString());
		return model;

	}

	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
