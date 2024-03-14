package com.magic.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.magic.EmployeesVO;
import com.magic.dao.EmployeesDAO;


@WebServlet("/mypage.do")
public class MyPageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   //header에서 로그인 했을 때 '마이페이지' 누르면 여기로 옴
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		EmployeesVO emp = (EmployeesVO)session.getAttribute("loginUser");
		
		if(emp != null) {
			request.getRequestDispatcher("employees/mypage.jsp").forward(request, response);
		}else { //로그인부터 하고 하라는 것
			response.sendRedirect("login.do");
		}
	}

	//mypage.jsp 에서 post로 보내옴. 
	//보내주는 값을 받아서 DB로 업데이트
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("utf-8");
		EmployeesVO vo = new EmployeesVO();
		
		vo.setId(request.getParameter("id"));
		vo.setPass(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setLev(request.getParameter("lev"));
		vo.setGender(Integer.parseInt(request.getParameter("gender")));
		vo.setPhone(request.getParameter("phone"));
		
		//DAO를 통해 DB연결
		EmployeesDAO eDao = EmployeesDAO.getInstance();
		
		HttpSession session = request.getSession();
		
		String url = null;
		
		//updateEmployee 메소드를 DAO에 만들어서 이 기능을 작동시켜서 result에 담는다.
		int result = eDao.updateEmployee(vo);
		
		//여기 위에까지 하고 updateEmployee 메소드를 만들면 DB에 입력이 됨
		if(result == 1) { //업데이트 완료 시 
			vo = eDao.getMember(vo.getId());
			request.setAttribute("message", "회원 정보가 수정되었습니다."); //joinsucess에서 사용
			request.setAttribute("member", vo);  //joinsucess에서 사용
			
			session.setAttribute("loginUser", vo);
			
			//관리자로 로그인 했는데도 불구하고 위에 로그인(관리자로 로그인 후 사용가능)가 떠있기 때문에 이를 추가한다.
			result = eDao.userCheck(vo.getId(), vo.getPass(), vo.getLev());                                                                                     
			
			request.setAttribute("result", result);
			
			url = "employees/joinsucess.jsp";
		}
		//업데이트 성공하고 나면 밑에(url)로 이동해라.
		request.getRequestDispatcher(url).forward(request, response);
	}

}
