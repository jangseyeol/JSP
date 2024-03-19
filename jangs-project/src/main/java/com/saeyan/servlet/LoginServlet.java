package com.saeyan.servlet;


import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.controller.boardAction.BoardAction;
import com.saeyan.controller.boardAction.BoardActionFactory;
import com.saeyan.controller.loginAction.LoginAction;
import com.saeyan.controller.loginAction.LoginActionFactory;


@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		LoginActionFactory laf = LoginActionFactory.getInstance();
		LoginAction loginAction = laf.getLoginAction(command);
		
		if(loginAction != null) {
			loginAction.execute(request, response);
		}
	}
	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		doGet(request, response);
	}

}
