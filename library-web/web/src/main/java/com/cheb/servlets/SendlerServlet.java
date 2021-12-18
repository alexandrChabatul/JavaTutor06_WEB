package com.cheb.servlets;

import java.io.IOException;
import com.cheb.dto.BookDto;
import com.cheb.exception.ServiceException;
import com.cheb.service.ServiceProvider;
import com.cheb.util.JspHelper;
import com.cheb.util.UrlPath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(UrlPath.SENDLER)
public class SendlerServlet extends HttpServlet {

	private static final long serialVersionUID = 7039347282516057274L;
	private final ServiceProvider provider = ServiceProvider.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(JspHelper.getPath("sendler")).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("answer").equals("true")) {
			try {
				BookDto book = (BookDto) req.getSession().getAttribute("book");
				req.getSession().removeAttribute("book");
				provider.getAdminRoleService().bookNotification(book);
				req.setAttribute("result", "good");
				req.getRequestDispatcher(JspHelper.getPath("sendlerResult")).forward(req, resp);
			} catch (ServiceException e) {
				req.setAttribute("result", "fail");
				doGet(req, resp);
				req.getRequestDispatcher(JspHelper.getPath("sendlerResult")).forward(req, resp);
			}
		} else {
			resp.sendRedirect(req.getContextPath() + UrlPath.BOOKS);
		}
	}

}
