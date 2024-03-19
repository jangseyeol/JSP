package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardWriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//post 방식으로 요청되었을 경우 파라미터로 받아온 한글깨짐을 방지하기 위해 doPost메소드의 첫 번째 파라미터인 HttpServletRequest에 대해 setCharacterEncording()
		//메소드를 호출하여 인코딩 방식을 지정해야 합니다. setCharacterEncoding()메소드는 getParrameter9)메소드보다 반드시 먼저 호출되어야 합니다.
		response.setContentType("text/html; charset=utf-8");
		//결과로 출력되는 html문서의 인코딩 방식을 지정해 주는 문장이다.
		
		BoardVO vo = new BoardVO();
		
		vo.setName(request.getParameter("name"));
		vo.setPass(request.getParameter("pass"));
		vo.setEmail(request.getParameter("email"));
		vo.setTitle(request.getParameter("title"));
		vo.setContent(request.getParameter("content"));
		
		BoardDAO.getInstance().insertBoard(vo); //insertBoard 메소드를  이용해서 db저장
		
		response.sendRedirect("BoardServlet?command=board_list");
		//new BoardListAction().excute(request, response);
		//이 요청에 따라 게시글 리스트 화면을 표시한다.
	}

}
