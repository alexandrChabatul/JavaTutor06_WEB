package com.cheb.servlets;

import java.io.IOException;
import com.cheb.exception.ServiceException;
import com.cheb.service.ServiceProvider;
import com.cheb.util.JspHelper;
import com.cheb.util.UrlPath;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(UrlPath.BOOKS)
public class BooksServlet extends HttpServlet {

	private static final long serialVersionUID = -5484921227547366069L;
	private static final int BOOKS_PER_PAGE = 10;
	private final ServiceProvider provider = ServiceProvider.getInstance();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			int numOfPages = (int) Math.ceil((double) provider.getUserRoleService().countAllBook() / BOOKS_PER_PAGE);

			int currentPage = getPage(req, numOfPages);

			var books = provider.getUserRoleService().findAllBooks((currentPage * BOOKS_PER_PAGE - BOOKS_PER_PAGE),
					BOOKS_PER_PAGE);
			req.setAttribute("books", books);
			req.setAttribute("numOfPages", numOfPages);
			req.setAttribute("currentPage", currentPage);
			req.getRequestDispatcher(JspHelper.getPath("books")).forward(req, resp);
		} catch (ServiceException e) {
			req.setAttribute("error", e);
			req.getRequestDispatcher(JspHelper.getPath("books")).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getParameter("name") != null) {
			try {
				var name = req.getParameter("name");
				
				int numOfPages = (int) Math.ceil((double) provider.getUserRoleService().countAllBookByName(name) / BOOKS_PER_PAGE);
				int currentPage = getPage(req, numOfPages);
				var books = provider.getUserRoleService().findBooksByName(name,
						(currentPage * BOOKS_PER_PAGE - BOOKS_PER_PAGE), BOOKS_PER_PAGE);
				req.setAttribute("name", name);
				req.setAttribute("books", books);
				req.setAttribute("numOfPages", numOfPages);
				req.setAttribute("currentPage", currentPage);
				req.getRequestDispatcher(JspHelper.getPath("books")).forward(req, resp);
			} catch (ServiceException e) {
				req.setAttribute("error", e);
				req.getRequestDispatcher(JspHelper.getPath("books")).forward(req, resp);
			}
		} else if (req.getParameter("author") != null) {
			try {
				var author = req.getParameter("author");
				
				int numOfPages = (int) Math.ceil((double) provider.getUserRoleService().countAllBookByAuthor(author) / BOOKS_PER_PAGE);
				int currentPage = getPage(req, numOfPages);
				var books = provider.getUserRoleService().findBooksByAuthor(author,
						(currentPage * BOOKS_PER_PAGE - BOOKS_PER_PAGE), BOOKS_PER_PAGE);
				req.setAttribute("author", author);
				req.setAttribute("books", books);
				req.setAttribute("numOfPages", numOfPages);
				req.setAttribute("currentPage", currentPage);
				req.getRequestDispatcher(JspHelper.getPath("books")).forward(req, resp);
			} catch (ServiceException e) {
				req.setAttribute("error", e);
				req.getRequestDispatcher(JspHelper.getPath("books")).forward(req, resp);
			}
		} else {
			doGet(req, resp);
		}
	}

	private int getPage(HttpServletRequest req, int numOfPages) {
		int page = 1;
		if (req.getParameter("page") != null) {
			try {
				page = Integer.parseInt(req.getParameter("page")) > 0 ? Integer.parseInt(req.getParameter("page")) : 1;
				page = page > numOfPages ? numOfPages : page;
			} catch (NumberFormatException e) {
			}
		}
		return page;
	}
}
