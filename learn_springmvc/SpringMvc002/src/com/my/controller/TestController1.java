package com.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.my.model.Person;

@Controller
@RequestMapping("/test1")
//@RequestMapping�����Լ�����������������ͬ�������Ĳ�ͬ��ķ���
public class TestController1 {
	
	/**
	 * ��ת��formҳ��
	 * @return
	 */
	@RequestMapping("/toForm.do")
	public String toForm(){
		return "form";
	}
	
	/**
	 * ��ת��formҳ��
	 * @return
	 */
	@RequestMapping("/test2/toForm1.do")
	public String toForm1(){
		return "form";
	}
	
	/**
	 * ��ת��formҳ��
	 * @return
	 */
	@RequestMapping("/test2/toForm2.do")
	public String toForm2(){
		return "form";
	}
}