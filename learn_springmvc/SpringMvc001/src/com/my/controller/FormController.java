package com.my.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.mvc.SimpleFormController;

public class FormController extends SimpleFormController {

	@Override
	protected void doSubmitAction(Object command) throws Exception {
		System.out.println(command);
		super.doSubmitAction(command);
	}
	
	
	@Override
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd"), true));
	}
}
