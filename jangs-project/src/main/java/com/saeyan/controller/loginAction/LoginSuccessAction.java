package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.LoginDAO;
import com.saeyan.dto.LoginVO;

public class LoginSuccessAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		String pass = request.getParameter("pass");
		String url = null;

		
		LoginVO lVo = LoginDAO.getInstance().getMember(id);
		
		System.out.println("lVo : " + lVo);

		if(lVo.getPass().equals(pass)) {
			url = "/board/boardList.jsp";		
			}else {
				response.sendRedirect("LoginServlet?command=login_start");		
				request.setAttribute("message", "비밀번호가 틀립니다.");
			}
		request.getRequestDispatcher(url).forward(request, response);
	}

}
