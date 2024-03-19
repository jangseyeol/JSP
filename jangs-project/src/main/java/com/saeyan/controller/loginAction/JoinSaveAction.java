package com.saeyan.controller.loginAction;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.LoginDAO;
import com.saeyan.dto.BoardVO;
import com.saeyan.dto.LoginVO;

public class JoinSaveAction implements LoginAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//post 방식으로 요청되었을 경우 파라미터로 받아온 한글깨짐을 방지하기 위해 doPost메소드의 첫 번째 파라미터인 HttpServletRequest에 대해 setCharacterEncording()
		//메소드를 호출하여 인코딩 방식을 지정해야 합니다. setCharacterEncoding()메소드는 getParrameter9)메소드보다 반드시 먼저 호출되어야 합니다.
		response.setContentType("text/html; charset=utf-8");
		//결과로 출력되는 html문서의 인코딩 방식을 지정해 주는 문장이다.
		
		LoginVO vo = new LoginVO();
		
		vo.setId(request.getParameter("id"));
		vo.setPass(request.getParameter("pwd"));
		vo.setName(request.getParameter("name"));
		vo.setLev(request.getParameter("lev"));
		vo.setGender(Integer.parseInt(request.getParameter("gender")));
		vo.setPhone(request.getParameter("phone"));
		
		LoginDAO.getInstance().insertMember(vo); //insertBoard 메소드를  이용해서 db저장
		
		response.sendRedirect("LoginServlet?command=login_start");
		//new BoardListAction().excute(request, response);
		//이 요청에 따라 게시글 리스트 화면을 표시한다.
	}

}
