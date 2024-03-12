package com.magic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.magic.EmployeesVO;
import com.magic.dao.EmployeesDAO;


@WebServlet("/custom.do")
public class CustomJoinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "employees/customJoin.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		EmployeesVO vo = new EmployeesVO();
		
		vo.setId(request.getParameter("id"));
		vo.setPass(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setLev(request.getParameter("lev"));
		vo.setGender(Integer.parseInt(request.getParameter("gender")));
		vo.setPhone(request.getParameter("phone"));
		
		//DAO 접속
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		//insertEmployee 메소드 생성, 사용
		eDao.insertEmployee(vo);
		
		request.setAttribute("member", vo);
		request.setAttribute("message", "회원 가입에 성공했습니다.");
		
		request.getRequestDispatcher("employees/joinsucess.jsp").forward(request, response);
	}

}
