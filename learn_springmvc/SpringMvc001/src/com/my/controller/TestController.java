package com.my.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class TestController extends AbstractController {

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest arg0,
			HttpServletResponse arg1) throws Exception {
		//ModelAndView之中的viewName指的是视图解析器前缀和后缀之间的路径
		System.out.println("===============hello springmvc");
		return new ModelAndView("index");
	}
}

