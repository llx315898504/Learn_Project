package com.rl.ecps.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.rl.ecps.model.TsPtlUser;

/**
 * 登录拦截器
 * 
 * @author 86150
 *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		TsPtlUser user = (TsPtlUser) request.getSession().getAttribute("user");
		//若用户信息为空，说明session失效或者用户没有登录，跳转到登录页面
		if (user==null) {
			//获取上下文根路劲
			String path=request.getContextPath();
			//跳转到登录页面
			response.sendRedirect(path+"/user/toLogin.do");
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
