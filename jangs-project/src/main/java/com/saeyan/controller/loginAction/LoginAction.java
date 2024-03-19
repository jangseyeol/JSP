package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface LoginAction {
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException;
	// 요청 파라미터를 동일한 메소드로 처리하도록 하기 위한 추상 메소드를 정의한다.
}
