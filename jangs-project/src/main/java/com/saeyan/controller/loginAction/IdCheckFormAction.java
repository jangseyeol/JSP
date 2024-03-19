package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IdCheckFormAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url = "/login/idCheckPass.jsp";
		
		request.getRequestDispatcher(url).forward(request, response);
	}

}
