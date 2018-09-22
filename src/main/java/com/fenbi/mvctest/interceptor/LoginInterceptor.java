package com.fenbi.mvctest.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.AdminRole;
import com.fenbi.mvctest.service.AdminRoleService;

public class LoginInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	public AdminRoleService adminRoleService;
	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("loginAdmin");
		if(admin == null) {
			response.setContentType("application/json");
			PrintWriter writer = response.getWriter();
			writer.println("{\"status\":2,\"msg\":\"没有登录\",\"data\":null}");
			writer.close();
			return false;
		}
		return true;
	}


}
