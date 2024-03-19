package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.LoginDAO;
import com.saeyan.dto.LoginVO;

public class IdCheckPassAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		
		String url = null;
		
		
		
	}

}
