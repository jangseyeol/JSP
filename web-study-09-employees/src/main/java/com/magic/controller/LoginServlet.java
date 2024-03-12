package com.magic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.dao.EmployeesDAO;
import com.magic.dto.EmployeesVO;

@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("employees/login.jsp");
		dis.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String admin = request.getParameter("admin");
		
		EmployeesDAO mDao = EmployeesDAO.getInstance();
		String url = "employees/login.jsp";
		
		//-1:비밀범호 X , 0 :아디디X, 1:로그인 성공
		int result = mDao.userCheck(id, pwd, admin);
		
		HttpSession session = request.getSession();
		
		EmployeesVO vo = mDao.getMember(id);
		
		if(result == 2 || result ==3) {
			session.setAttribute("emp", vo);
			session.setAttribute("loginUser", vo);
			session.setAttribute("result", result);
			request.setAttribute("message", "로그인 성공했습니다.");
			url = "employees/main.jsp";
		}else {
			request.setAttribute("message", "로그인 실패했습니다.");
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
		
	}
}
