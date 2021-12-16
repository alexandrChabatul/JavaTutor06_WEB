package com.cheb.servlets;

import java.io.IOException;
import java.util.List;
import com.cheb.dto.CreateBookDto;
import com.cheb.exception.ServiceException;
import com.cheb.exception.ValidationException;
import com.cheb.service.ServiceProvider;
import com.cheb.util.JspHelper;
import com.cheb.util.UrlPath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(UrlPath.CREATE_BOOK)
public class CreateBookServlet extends HttpServlet {

	private static final long serialVersionUID = 3455074672300550159L;
	private static final String FAIL_MESSAGE = "Something go wrong. Please try again or try latter.";
	
	private final ServiceProvider provider = ServiceProvider.getInstance();
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("types", List.of("PAPER", "ELECTRONIC"));
		req.getRequestDispatcher(JspHelper.getPath("createBook"))
		.forward(req, resp);	
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			var book = provider.getAdminRoleService().addBook(
					CreateBookDto.builder()
						.name(req.getParameter("title"))
						.author(req.getParameter("author"))
						.type(req.getParameter("type"))
						.build()				
					);
			req.getSession().setAttribute("book", book);
			resp.sendRedirect(req.getContextPath() + UrlPath.SENDLER);
		} catch (ValidationException e) {
			req.setAttribute("errors", e.getErrors());
			doGet(req, resp);
		} catch (ServiceException e) {
			req.setAttribute("service", FAIL_MESSAGE);
			doGet(req, resp);
		}
		
	}
	
}
