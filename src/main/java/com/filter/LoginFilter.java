package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.User;

public class LoginFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest re = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = re.getSession();
		User user = (User) session.getAttribute("user");
		String path = re.getRequestURI();
		if(path.contains("loginOut") || path.contains("login") || path.contains("getRandomCode")) {
			chain.doFilter(request, response);
			return;
		}
		if(user == null) {
			RequestDispatcher rd = re.getRequestDispatcher("/WEB-INF/views/error/error.jsp");
			rd.forward(re, res);
		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		
	}

}
