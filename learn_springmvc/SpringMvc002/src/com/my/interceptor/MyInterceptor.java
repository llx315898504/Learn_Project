package com.my.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class MyInterceptor implements HandlerInterceptor {

	/**
	 * ��������
	 * ִ��ʱ��������ͼ�������������ҳ��֮��
	 * ��ҪӦ������ʱҳ������״̬�ļ�أ�ҳ���쳣���ռ�
	 */
	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		System.out.println("=============�������ر�ִ��===========");
		//ģ���ӡ�쳣��Ϣ
		System.out.println("-----------------------------------");
		arg3.printStackTrace();
		System.out.println("-----------------------------------");
	}

	/**
	 * ��������
	 * ִ��ʱ������controllerִ����ɣ���ͼ����������֮ǰ
	 * ��ҪӦ���Ǻ�����ǿ
	 */
	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView mv) throws Exception {
		System.out.println("===========�������ر�ִ��===========");
		Map<String, Object> map= mv.getModel();
		map.put("test", "append something");
	}
	
	/**
	 * ǰ������
	 * ִ��ʱ������controllerִ��֮ǰ
	 * ����ֵ��boolean���ͣ��������TRUE��ʾ���У�����FALSE��ʾ����
	 * ��ҪӦ�ã�Ȩ�޿��ƣ�����
	 * Object��������controller��ʵ��
	 */
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		System.out.println("===============ǰ�����ر�ִ��===================");
		return true;
	}

}
