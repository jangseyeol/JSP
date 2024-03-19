package com.saeyan.controller.loginAction;


public class LoginActionFactory {
	private static LoginActionFactory instance = new LoginActionFactory();
	
	private LoginActionFactory() {
		
	}
	
	public static LoginActionFactory getInstance() {
		return instance;
	}
	
	public LoginAction getLoginAction(String command) {
		//getAction 메소드는 command를 받아서 거기에 맞는 액션을 리턴한다.
		LoginAction loginAction = null;
		
		System.out.println("LoginActionFactory :" + command);
		
		//조건식 나열될 것임
		//action 구현 클래스
		
		//login_success
		if(command.equals("login_start")) {
			//객체생성
			loginAction = new LoginStartFormAction();

		}else if(command.equals("login_success")) {
			//객체생성
			loginAction = new LoginSuccessAction();

		}else if(command.equals("join_form")) {
			//객체생성
			loginAction = new JoinFormAction();

		}else if(command.equals("join_save_form")) {
			//객체생성
			loginAction = new JoinSaveAction();

		}else if(command.equals("id_check_form")) {
			//객체생성
			loginAction = new IdCheckFormAction();

		}else if(command.equals("id_check_pass")) {
			//객체생성
			loginAction = new IdCheckPassAction();
		}
		return loginAction;
	}
	
}
