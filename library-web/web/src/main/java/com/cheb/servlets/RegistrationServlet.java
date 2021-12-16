package com.cheb.servlets;

import java.io.IOException;
import java.util.List;
import com.cheb.dto.CreateUserDto;
import com.cheb.exception.ServiceException;
import com.cheb.exception.ValidationException;
import com.cheb.service.ServiceProvider;
import com.cheb.util.UrlPath;
import com.cheb.util.JspHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(UrlPath.REGISTRATION)
public class RegistrationServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private static final ServiceProvider provider = ServiceProvider.getInstance();
	private static final String FAIL_MESSAGE = "Something go wrong. Please try again or try latter.";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("roles", List.of("USER", "ADMIN"));		
		req.getRequestDispatcher(JspHelper.getPath("registration"))
			.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		CreateUserDto userDto = CreateUserDto.builder()
				.name(req.getParameter("name"))
				.email(req.getParameter("email"))
				.password(req.getParameter("password"))
				.role(req.getParameter("role"))
				.build();
		try {
			provider.getUserService().registration(userDto);
			resp.sendRedirect(req.getContextPath() + UrlPath.LOGIN);
		} catch (ValidationException e) {
			req.setAttribute("errors", e.getErrors());
			doGet(req, resp);
		} catch (ServiceException e) {
			req.setAttribute("fail", FAIL_MESSAGE);
			doGet(req, resp);
		}

		
		
	}

}
