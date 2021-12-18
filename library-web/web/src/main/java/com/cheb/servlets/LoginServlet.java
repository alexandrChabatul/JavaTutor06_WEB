package com.cheb.servlets;

import java.io.IOException;

import com.cheb.dto.UserDto;
import com.cheb.exception.ServiceException;
import com.cheb.service.ServiceProvider;
import com.cheb.util.JspHelper;
import com.cheb.util.UrlPath;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@WebServlet(UrlPath.LOGIN)
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 8851140932199929399L;
	private final ServiceProvider provider = ServiceProvider.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspHelper.getPath("login")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			provider.getUserService().login(req.getParameter("email"), req.getParameter("password"))
					.ifPresentOrElse(user -> onLoginSucces(user, req, resp), () -> onLoginFail(req, resp));
		} catch (ServiceException e) {
			resp.sendRedirect(req.getContextPath() + "/login?error&service");
		}

	}

	@SneakyThrows
	private void onLoginFail(HttpServletRequest req, HttpServletResponse resp) {
		resp.sendRedirect(req.getContextPath() + "/login?error&email=" + req.getParameter("email"));
	}

	@SneakyThrows
	private void onLoginSucces(UserDto user, HttpServletRequest req, HttpServletResponse resp) {
		req.getSession().setAttribute("user", user);
		resp.sendRedirect(req.getContextPath() + "/books");
	}

}
