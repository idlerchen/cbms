package com.fenbi.mvctest.interceptor;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fenbi.mvctest.entity.Admin;
import com.fenbi.mvctest.entity.Function;
import com.fenbi.mvctest.service.FunctionService;

public class FunctionInterceptor extends HandlerInterceptorAdapter{
	
	@Autowired
	public FunctionService functionService;

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
		String url = request.getRequestURI();
		url = url.substring(url.indexOf("/")+1);
		url = url.substring(url.indexOf("/"));
		System.out.println(url);
		HttpSession session = request.getSession();
		Admin admin = (Admin) session.getAttribute("loginAdmin");
		List<Function> functions = functionService.selectByadminId(admin.getId());
		for(Function f:functions) {
			if(f.getUrl().contains(url)) {
				return true;
			}
		}
		response.setContentType("application/json");
		PrintWriter writer = response.getWriter();
		writer.println("{\"status\":3,\"msg\":\"没有权限访问\",\"data\":null}");
		writer.close();
		return false;
	}
}
