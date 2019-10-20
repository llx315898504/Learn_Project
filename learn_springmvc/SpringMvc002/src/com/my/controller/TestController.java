package com.my.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/test")
//@RequestMapping�����Լ�����������������ͬ�������Ĳ�ͬ��ķ���
public class TestController {
	
	/**
	 * ��ת����ʼ��ҳ��
	 * ����ֵ��string����ModelAndView��viewName������ͼ������ǰ׺�ͺ�׺֮���·��
	 * @RequestMapping��ָ�����������ӳ��
	 * @return
	 */
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("=================springmvc");
		return "index";
	}
	
	/**
	 * ��HttpServletRequest��ʽ���ղ���
	 * HttpServletRequest���ڲ����б�ֱ�Ӷ������ʹ��
	 * @param req
	 * @return
	 */
	@RequestMapping("/revParam.do")
	public String revParam(HttpServletRequest req){
		String name=req.getParameter("name");
		System.out.println(name);
		return "index";
	}
    
	/**
	 * �Բ����б����ʽ���ղ���
	 * ע�⣺����ֱ���ڷ����Ĳ����б��ֶ���Ҫ���յĲ����������ı������������е�ʵ��������һ�£�
	 * �Ҳ������������ͱ�����ת��
	 * @param name
	 * @param gender
	 * @param address
	 * @param birthday
	 * @return
	 */
	@RequestMapping("/revParam1.do")
	public String revParam1(String name,Integer gender,String address,Date birthday){
		System.out.println(name);
		System.out.println(gender);
		System.out.println(address);
		System.out.println(birthday);
		return "index";
	}
	
	
	
	/**
	 * ��ѡ�����Ľ���
	 * ��ѡ�����ڲ����б���ʹ�����������գ�Ҫ����������������������һ��
	 * @RequestMapping:method����ָ������ʽ��
	 * ���ָ����һ��ָ��������ʽ����ֻ�������ַ�ʽ������
	 * @param fav
	 * @return
	 */
	@RequestMapping(value="/revParam2.do",method=RequestMethod.POST)
	public String revParam2(String fav[]){
		for (String f : fav) {
			System.out.println(f);
		}
		return "index";
	}
	
	/**
	 * ��ת��formҳ��
	 * @return
	 */
	@RequestMapping("/toForm.do")
	public String toForm(){
		return "form";
	}
	
	/**
	 * ʵ�����Ľ���
	 * ע�⣺�����Զ���ʵ�����ֱ���ڲ����б��ж���Ҫ���յĶ��󼴿ɣ�����Ҫ���ı�������
	 * ���Ǳ���Ҫ��ʵ�����е����ԺͲ��������ֶ�Ӧ�ϲ��ܽ��յ�ֵ��
	 * @param fav
	 * @return
	 */
	@RequestMapping("/revParam3.do")
	public String revParam3(Person person){
		System.out.println(person);
		return "index";
	}
	
	
	/**
	 * ��ʱ�����͵����Ա༭��
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor
				(new SimpleDateFormat("YYYY-MM-dd"), true));
	}
	
	
	
	/**
	 * ��ModelAndView����ʽ�������ݣ�������ʹ��
	 * Ҫע�⣺map�ڲ����б������ڽ��ղ����������ڷ��ظ�ҳ��ļ���
	 * @return
	 */
	@RequestMapping("/returnResult.do")
	public ModelAndView returnResult(){
		Person person=new Person();
		person.setId(1);
		person.setName("lisi");
		person.setGender(1);
		person.setAddress("beijing");
		person.setBirthday(new Date());
		Map<String, Person> map=new HashMap<String, Person>();
		map.put("person", person);
		return new ModelAndView("returnpage",map);
	}
	
	/**
	 * Ҫע�⣺map�ڲ����б������ڽ��ղ����������ڷ��ظ�ҳ��ļ���,Ҳ������ʹ��
	 * @param map
	 * @return
	 */
	@RequestMapping("/returnResult1.do")
	public String returnResult1(Map<String, Person> map){
		Person person=new Person();
		person.setId(1);
		person.setName("lisi");
		person.setGender(1);
		person.setAddress("beijing");
		person.setBirthday(new Date());
		map.put("person", person);
		return "returnpage";
	}
	
	/**
	 * �ڲ����б���ʹ��Model
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping("/returnResult2.do")
	public String returnResult2(Model model){
		Person person=new Person();
		person.setId(1);
		person.setName("lisi");
		person.setGender(1);
		person.setAddress("beijing");
		person.setBirthday(new Date());
		model.addAttribute("person", person);
		return "returnpage";
	}
	
	
	/**
	 * ajax�첽���÷�ʽһ
	 * ʹ��ajax�ķ���ֵ��void������ʹ��String
	 * �ڲ����б���HttpservletResponse��Ϊ�˻��printWriter,
	 * HttpservletResponse�������ñ����ʽ
	 * @param name
	 * @param response
	 */
	@RequestMapping(value="/ajax.do",method=RequestMethod.POST )
	public void ajax(String name,HttpServletResponse  response){
		String result="Hello "+name;
		try {
			System.out.println(result);
			response.getWriter().write(result);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * ajax�첽���÷�ʽ��
	 * ʹ��ajax�ķ���ֵ��void������ʹ��String
	 * �ڲ����б���������printwriter,���ʺ���UTF-8����ʹ��printwrite������ʹ��HttpServletResponse
	 * @param name
	 * @param response
	 */
	@RequestMapping(value="/ajax1.do",method=RequestMethod.POST )
	public void ajax1(String name,PrintWriter out){
		String result="Hello "+name;
			System.out.println(result);
			out.write(result);
	}
	
	/**
	 * ��ת��ajaxҳ��
	 * 
	 * @param name
	 * @param response
	 */
	@RequestMapping("/toAjax.do")
	public String toAjax(){
		return "ajax";
	}
	
	/**
	 * controller֮�ڵ��ض���
	 * �﷨��redirect�����ϵ�ǰcontroller��Ҫ�ض���ķ�����requestMapping��ӳ�䷽������
	 * ������ǰ����"/"����Ҫ���Ǽ�.do
	 * 
	 * @return
	 */
	@RequestMapping("/redirectForm.do")
	public String redirectForm(){
		return "redirect:toForm.do";
	}
	
	
	/**
	 * controller֮����ض���
	 * �﷨��redirect�������"/"�������Ŀ��Ŀ¼��ʼ���ټ���Ҫ�ض�������
	 * ��RequestMapping��ֵ���ټ��Ϸ�����RequestMapping��ֵ
	 * ������ǰ��"/"����Ҫ���Ǽ�.do
	 * 
	 * @return
	 */
	@RequestMapping("/redirectForm1.do")
	public String redirectForm1(){
		return "redirect:/test1/toForm.do";
	}
}
