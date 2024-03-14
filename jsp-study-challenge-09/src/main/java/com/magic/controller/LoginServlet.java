package com.magic.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.EmployeesVO;
import com.magic.dao.EmployeesDAO;


@WebServlet("/login.do")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	   HttpSession session = request.getSession();
	   EmployeesVO vo = (EmployeesVO)session.getAttribute("loginUser");
	   
	   if(vo != null) {
		   request.getRequestDispatcher("employees/main.jsp").forward(request, response);
	   }else {
	   RequestDispatcher dis = request.getRequestDispatcher("employees/login.jsp");
		dis.forward(request, response);
	   }
   }

//login.jsp에서 login.do 로 post 방식으로 id, pwd, lev 를 넘어오는 것을 받아줘야 함
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//한글 깨지지 않도록 하기 위해
		request.setCharacterEncoding("utf-8");
		
		//login.jsp id, pwd, lev 받아오기
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String lev = request.getParameter("lev");
		
		//DB에 이런거 있는 지 물어보기 - > 이 일을 전담하는 DAO 클래스 만들기 
		//dao 폴더 employeesDAO 클래스 만듬
		// eDao를 통해 EmployeesDAO 객체를 사용하기로 함
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		
		String url = "employees/login.jsp";
		/*
		 * result -1 : 아이디 틀림
		 * result 0  : 비밀번호 틀림
		 * result 1 : 등급 상이
		 * result 2  : 아이디/비밀번호/A
		 * result 3  : 아이디/비밀번호/B
		 */
		
		//위에 조건대로 DB에 post된 자료들이 있는지 그리고 맞는지 물어보기 위해서 userCheck 메소드 를 사용하기로 함
		//employeeDAO 에 메소드 만들어짐
		// eDao 를 통해 사용
		int result = eDao.userCheck(id, pwd, lev);
		
		if(result == 2 || result ==3) {
			
			//getMember 메소드를 사용해서 DB정보 가져오기
			//vo 클래스를 통해 가져옴
			EmployeesVO vo = eDao.getMember(id);
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", vo);
			session.setAttribute("result", result); //result에는 2 아니면 3
			url = "employees/main.jsp"; //main.jsp 는 로그인 했을 때 나오는 화면
			
			
		}else {
			if(result == 1) {
				request.setAttribute("message", "레벨 등급을 확인하세요.");
			}else if (result == -1) {
				request.setAttribute("message", "존재하지 않는 아이디입니다.");
			}else if (result == 0) {
				request.setAttribute("message", "비밀번호가 틀립니다");
			}
		}
		
		RequestDispatcher dis= request.getRequestDispatcher(url);
		dis.forward(request, response);
	}

}
