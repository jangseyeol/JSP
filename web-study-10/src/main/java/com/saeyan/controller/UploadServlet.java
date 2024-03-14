package com.saeyan.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


@WebServlet("/upload.do")
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	//앞에서 포스트 방식으로 전송하였음
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//응답
		response.setContentType("text/html; charset=utf-8");
		
		//출력객체생성
		PrintWriter out = response.getWriter();
		
		// 먼저, c 드라이브 밑에 upload 폴더 만들기
		//밑에가 저장경로임
		//여기를 바꿔주면 다운받는 경로가 바뀐다.
		String savePath = "upload";
		
		//최대 업로드 파일 크기 5MB 로 제한
		int uploadFileSizeLimit = 5*1024*1024; //1MB = 1024 Byte
			
		String encType = "utf-8";
		
		//실제경로찾아내기
		ServletContext context = getServletContext();
		System.out.println("context : " + context);
		
		//저장이 어디에 되는지? 
		String uploadFilePath = context.getRealPath(savePath);
		System.out.println("uploadFilePath :" + uploadFilePath); // 서버 상 실제 폴더(저장경로)
		
		
		try {
			//MultipartRequest 는 cos 폴더에 들어있는 것
			MultipartRequest multi = new MultipartRequest(
						request, //reqest 객체
						uploadFilePath, //실제 저장경로
						uploadFileSizeLimit, //크기
						encType, //utf-8 로 하겠다.
						new DefaultFileRenamePolicy() //파일을 전송했을 때 같은 이름이 존재할 때(중복) 새로운 이름을 부여하겠다.
					);
					
			//업로드 된 파일의 이름을 얻기
			
			String fileName = multi.getFilesystemName("uploadFile"); 
			System.out.println();
			
			if(fileName == null) {
				System.out.println("파일 업로드 되지 않았음");
			}else {
				//출력(앞에 upload.jsp에서 설정된 것들)
				//MultipartRequest 클래스의 getParameter() 메소드로 모든 요청에 대한 처리를 한다.
				out.println("<br>글쓴이 : " + multi.getParameter("name"));
				out.println("<br>제목 : " + multi.getParameter("title"));
				out.println("<br>파일명 : " + fileName);//multi.getFilesystemName("uploadFile");과 같음
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
