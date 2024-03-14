package com.ezen.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ezen.dao.MovieDAO;
import com.ezen.dto.MovieVO;


@WebServlet("/moviedelete.do")
public class MovieDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		response.setContentType("text/html; charset=utf-8");
		
		int code = Integer.parseInt(request.getParameter("code"));
		
		MovieVO vo = MovieDAO.getinstance().getOneMovie(code);
		
		request.setAttribute("movie", vo);
		
		request.getRequestDispatcher("movie/movieDelete.jsp").forward(request, response);

	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		int code = Integer.parseInt(request.getParameter("code"));
		
		MovieDAO.getinstance().deleteMovie(code);
		
		//response.sendRedirect("movie/movieList.jsp"); 스타일 서식 적용 안됨
		response.sendRedirect("movieList.do");
	}

}
