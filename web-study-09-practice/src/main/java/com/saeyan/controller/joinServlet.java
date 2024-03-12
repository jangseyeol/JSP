// 입력된 회원 정보로 회원 가입 처리

package com.saeyan.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.MemberDAO;
import com.saeyan.dto.MemberVO;


@WebServlet("/join.do")
public class joinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    //get 방식은 회원 가입이나 게시글 작성 등의 작업에서 입력 폼을 출력할 때 사용합니다.
	//회원가입 버튼이 클릭되었을 때에 요청되는 것
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dis = request.getRequestDispatcher("member/join.jsp");
		dis.forward(request, response);
	}

	// 원 정보나 게시글 정보를 모두 입력하고 난 후에 데이터베이스에 정보를 저장하기 위한 작업은 post방식으로 요청하여 처리합니다.
	//post방식으로 요청하여 데이터베이스 처리를 하도록
	//여기에 필요한 메소드인 insertMember메소드를 MemberDAO에 저장한다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		request.setCharacterEncoding("utf-8");
		//회원 가입 폼에서 입력 받은 한글이 깨지지 않도록 인코딩한다.
		
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		//폼에서 입력 받은 회원 정보를 얻어온다.
		
		MemberVO vo = new MemberVO();
		//회원 정보를 저장할 객체를 생성한다.
		
		vo.setName(name);
		vo.setUserid(userid);
		vo.setPwd(pwd);
		vo.setEmail(email);
		vo.setPhone(phone);
		vo.setAdmin(Integer.parseInt(admin));
		//MemberVO 객체인 vo에 회원가입 폼에서 입력받은 데이터를 저장한다. 
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.insertMember(vo);
		//MemberVO 객체를 전달인자로 주어 MemberDAO 객체로 insertMember메소드를 호출합니다.
		
		HttpSession session = request.getSession();
		
		if(result == 1) {
			//회원가입 성공
			session.setAttribute("userid", userid);
			//session에 "userid"를 키로 하여 지금 막 회원 가입한 회원의 아이디를 값으로 저장해두어
			//login.jsp로 제어를 이동하여 로그인 작업을 할 때 아이디를 입력받는 수고를 덜어줍니다.
			
			request.setAttribute("message", "회원가입에 성공했습니다.");
		}else {
			//회원 가입 실패
			request.setAttribute("message", "회원가입에 실패했습니다.");
		}
		request.getRequestDispatcher("member/login.jsp").forward(request, response);
		//회원가입에 성공하였으면 로그인 페이지로 이동한다.
		
	}

}
