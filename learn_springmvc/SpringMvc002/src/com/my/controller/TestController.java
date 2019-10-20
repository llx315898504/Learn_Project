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
//@RequestMapping：可以加在类上用于区分相同方法名的不同类的方法
public class TestController {
	
	/**
	 * 跳转到初始化页面
	 * 返回值：string代表ModelAndView中viewName，即视图解析器前缀和后缀之间的路径
	 * @RequestMapping：指定方法和类的映射
	 * @return
	 */
	@RequestMapping("/hello.do")
	public String hello(){
		System.out.println("=================springmvc");
		return "index";
	}
	
	/**
	 * 以HttpServletRequest方式接收参数
	 * HttpServletRequest：在参数列表直接定义可以使用
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
	 * 以参数列表的形式接收参数
	 * 注意：可以直接在方法的参数列表种定义要接收的参数，参数的变量名和请求中的实参名必须一致，
	 * 且参数的数据类型必须能转换
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
	 * 多选参数的接收
	 * 多选可以在参数列表中使用数组来接收，要求数组变量名和请求参数名一致
	 * @RequestMapping:method用来指定请求方式，
	 * 如果指定了一个指定的请求方式，就只能用这种方式来访问
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
	 * 跳转到form页面
	 * @return
	 */
	@RequestMapping("/toForm.do")
	public String toForm(){
		return "form";
	}
	
	/**
	 * 实体对象的接收
	 * 注意：接收自定义实体对象，直接在参数列表中定义要接收的对象即可，不需要关心变量名，
	 * 但是必须要把实体类中的属性和参数的名字对应上才能接收到值。
	 * @param fav
	 * @return
	 */
	@RequestMapping("/revParam3.do")
	public String revParam3(Person person){
		System.out.println(person);
		return "index";
	}
	
	
	/**
	 * 绑定时间类型的属性编辑器
	 * 
	 * @param binder
	 */
	@InitBinder
	public void initBinder(ServletRequestDataBinder binder){
		binder.registerCustomEditor(Date.class, new CustomDateEditor
				(new SimpleDateFormat("YYYY-MM-dd"), true));
	}
	
	
	
	/**
	 * 以ModelAndView的形式返回数据，不建议使用
	 * 要注意：map在参数列表不是用于接收参数而是用于返回给页面的集合
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
	 * 要注意：map在参数列表不是用于接收参数而是用于返回给页面的集合,也不建议使用
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
	 * 在参数列表中使用Model
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
	 * ajax异步调用方式一
	 * 使用ajax的返回值是void，不能使用String
	 * 在参数列表定义HttpservletResponse，为了获得printWriter,
	 * HttpservletResponse可以设置编码格式
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
	 * ajax异步调用方式二
	 * 使用ajax的返回值是void，不能使用String
	 * 在参数列表中来定义printwriter,不适合在UTF-8编码使用printwrite，而是使用HttpServletResponse
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
	 * 跳转到ajax页面
	 * 
	 * @param name
	 * @param response
	 */
	@RequestMapping("/toAjax.do")
	public String toAjax(){
		return "ajax";
	}
	
	/**
	 * controller之内的重定向
	 * 语法：redirect：加上当前controller中要重定向的方法的requestMapping的映射方法名，
	 * 方法名前不加"/"，不要忘记加.do
	 * 
	 * @return
	 */
	@RequestMapping("/redirectForm.do")
	public String redirectForm(){
		return "redirect:toForm.do";
	}
	
	
	/**
	 * controller之间的重定向
	 * 语法：redirect：后面加"/"代表从项目根目录开始，再加上要重定向类上
	 * 的RequestMapping的值，再加上方法上RequestMapping的值
	 * 方法名前加"/"，不要忘记加.do
	 * 
	 * @return
	 */
	@RequestMapping("/redirectForm1.do")
	public String redirectForm1(){
		return "redirect:/test1/toForm.do";
	}
}
