package com.twbh.board.controller.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.twbh.common.dao.BoardDAO;
import com.twbh.common.dto.BoardVO;

public class BoardUpdateAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		BoardVO bVo = new BoardVO();
		
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		//업로드 경로 
		String savePath = "C:/Users/User/Desktop/jsp_servlet_prj/TWBH/WebContent/upload";
		//업로드 파일 크기 5mb 제한
		int uploadFileSizeLimit = 5 * 1024*1024;
		String enType = "UTF-8";
		
		MultipartRequest multi = new MultipartRequest(
				request, savePath,uploadFileSizeLimit,enType,new DefaultFileRenamePolicy());
		System.out.println("멀티 제목: "+multi.getParameter("title"));
		//System.out.println("리퀘스트 네임"+request.getParameter("name"));
		
		
		bVo.setNum(Integer.parseInt(multi.getParameter("num")));
		bVo.setName(multi.getParameter("name"));
		bVo.setEmail(multi.getParameter("email"));
		bVo.setTitle(multi.getParameter("title"));
		bVo.setContent(multi.getParameter("content"));
		bVo.setPictureUrl(multi.getFilesystemName("pictureUrl"));
		System.out.println(bVo.toString());
		
		
		BoardDAO bDao = BoardDAO.getInstance();
		bDao.updateBoard(bVo);
		
		new BoardListAction().execute(request, response);
	}
}