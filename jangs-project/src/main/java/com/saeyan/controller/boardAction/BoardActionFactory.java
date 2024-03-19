package com.saeyan.controller.boardAction;

//모든 모델들은 Action 인터페이스의 상속을 받는 구현 객체이어야 하기 때문에 이를 액션 객체라고 부른다.
//컨트롤러에서는 직접 모델(액션 객체)을 생성하지 않는 대신 액션 객체를 생성해 내는 팩토리(공장) 역할을 하는 클래스를 통해서 생성한다.
//객체를 생성해내는 클래스를 따로 설계해서 작업하는 것을 팩토리 패턴이라고 한다.
//액션 객체를 생성할 것이기 때문에 이름을 ActionFactory로 하자.


public class BoardActionFactory {
	private static BoardActionFactory instance = new BoardActionFactory();
	
	private BoardActionFactory() {
		
	}
	
	public static BoardActionFactory getInstance() {
		return instance;
	}
	
	public BoardAction getBoardAction(String command) {
		//getAction 메소드는 command를 받아서 거기에 맞는 액션을 리턴한다.
		BoardAction boardAction = null;
		
		System.out.println("BoardActionFactory :" + command);
		
		//조건식 나열될 것임
		//action 구현 클래스
		
		//board_delete
		if(command.equals("board_list")) {
			//객체생성
			boardAction = new BoardListAction();

		}else if(command.equals("board_write_form")) {
			//객체생성
			boardAction = new BoardWriteActionForm();

		
		}else if(command.equals("board_write")) {
			//객체생성
			boardAction = new BoardWriteAction(); 
		
		}else if(command.equals("board_view")) {
			//객체생성
			boardAction = new BoardViewAction(); 
		}else if(command.equals("board_check_pass_form")) {
			//객체생성
			boardAction = new BoardCheckPassFormAction(); 
		}else if(command.equals("board_check_pass")) {
			//객체생성
			boardAction = new BoardCheckPassAction(); 
		}else if(command.equals("board_update_form")) {
			//객체생성
			boardAction = new BoardUpdateFormAction(); 
		}else if(command.equals("board_update")) {
			//객체생성
			boardAction = new BoardUpdateAction(); 
		}else if(command.equals("board_delete")) {
			//객체생성
			boardAction = new BoardDeleteAction(); 
		}
		
		
		return boardAction;
	}
	
}
