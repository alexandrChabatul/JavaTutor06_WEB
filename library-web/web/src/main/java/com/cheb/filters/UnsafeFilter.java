package com.cheb.filters;

import java.io.IOException;

import com.cheb.dto.UserDto;
import com.cheb.entity.Role;
import com.cheb.util.UrlPath;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebFilter(value = {
		UrlPath.CREATE_BOOK,
		UrlPath.SENDLER,
		UrlPath.SENDLER_RESULT
})
public class UnsafeFilter implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		var user = (UserDto) ((HttpServletRequest) request).getSession().getAttribute("user");
		if (user != null && user.getRole() == Role.ADMIN  ){
			chain.doFilter(request, response);
		} else {
			((HttpServletResponse) response).sendRedirect(((HttpServletRequest) request).getContextPath() + "/books");
		}
		
		
	}

}
