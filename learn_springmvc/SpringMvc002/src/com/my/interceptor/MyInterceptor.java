package com.my.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	/**
	 * 最终拦截
	 * 执行时机：在视图解析器解析完成页面之后
	 * 主要应用运行时页面运行状态的监控，页面异常的收集
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("=============最终拦截被执行===========");
		//模拟打印异常信息
		System.out.println("-----------------------------------");
		arg3.printStackTrace();
		System.out.println("-----------------------------------");
	}

	/**
	 * 后置拦截
	 * 执行时机：在controller执行完成，视图解析器解析之前
	 * 主要应用是后置增强
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView mv) throws Exception {
		System.out.println("===========后置拦截被执行===========");
		Map<String, Object> map= mv.getModel();
		map.put("test", "append something");
	}
	
	/**
	 * 前置拦截
	 * 执行时机：在controller执行之前
	 * 返回值：boolean类型，如果返回TRUE表示放行；返回FALSE表示拦截
	 * 主要应用：权限控制，管理
	 * Object：被拦截controller的实例
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("===============前置拦截被执行===================");
		return true;
	}

}
