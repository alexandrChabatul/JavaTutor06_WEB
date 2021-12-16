package com.cheb.servlets;

import java.io.IOException;

import com.cheb.util.UrlPath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		var language = req.getParameter("lang");
		req.getSession().setAttribute("lang", language);
		
		var prevPage = req.getHeader("Referer");
		var page = prevPage != null ? prevPage : UrlPath.BOOKS;
		resp.sendRedirect(page + "?lang=" + language);
	}
	
}
