//회원 인증 처리

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


@WebServlet("/login.do")
public class loginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("loginUser") != null) {
			url = "main.jsp";
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("url");
		
		
		dispatcher.forward(request, response);
	}

	//
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "member/login.jsp";
		//회원 인증이 실패되었을 경우 이동할 login.jsp 페이지를 url 변수에 저장한다.

		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		//login.jsp 로그인 폼에서 입력한 회원의 아이디와 암호를 얻어 와서 변수에 저장한다.
		
		MemberDAO mDao = MemberDAO.getInstance();
		int result = mDao.userCheck(userid,pwd);
		//입력받은 아이디와 암호로 회원 인증 여부를 물어보기 위해서 회원 가입 시 회원 정보를 member 테이블에 저장해 두었기 때문에 
		//member 테이블에 존재하는 아이디인지를 확인해야 한다.
		//MemberDAO의 userCheck()메소드에서는 아이디에 해당되는 회원이 존재하는지를 조회한다.
		//userCheck()메소드를 호출하여 데이터베이스에 등록된 회원인지 검사하여 결과값을 얻어온다.
		
		
		//-1: 비밀번호 X
		// 0: 아이디 X
		// 1: 로그인 성공
		
		
		
		if(result == 1) {//결과가 1이면 회원 인증에 성공한 것이기에 회원 정보를 얻어온다.
			MemberVO vo = mDao.getMember(userid);
			
			//로그인 인증 처리된 회원 정보는 다른 사이트에 갔다 돌아와도 다시 로그인하지 않아도 될 수 있도록 하기 위해서 세션에 등록해 두어야 한다.
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", vo);
			//세션 객체를 생성하여 세션에 회원 정보를 저장해둔다.
			
			request.setAttribute("message", "로그인 성공했습니다.");
			//jsp에 보낼 메시지를 요청 객체에 저장해 둔다.
			
			url = "member/main.jsp";
			//회원 인증이 되었을 경우 회원 테이블에서 회원 정보를 얻어와 이를 MemberVO 객체에 저장해 둔 상태이다.
			//웹사이트를 사용하다보면 게시글을 올리거나 상품을 주문하게 될 경우 회원정보가 필요하게 되는데 이럴 때마다 회원 정보를 데이터베이스에서 얻어온다면 번거로울 것이다.
			// 그래서 로그인하면서 얻어온 회원 정보를 세션에 저장해 두면 어느 페이지에서든지 세션에 저장된 회원 정보를 얻어올 수 있어서 편리하다.
			// 그래서 로그인 인증 처리가 끝나면 회원 정보를 세션에 저장해 두곤 한다.
			//인증에 성공해야만 이동 가능한 main.jsp 페이지로 가도록 main.jsp를 url 변수에 저장한다.
			
		}else if(result == 0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다..");
			//userCheck 메소드의 리턴 값이 0이면 비밀번호가 맞지 않는 것이다. "비밀번호가 맞지 않습니다."란 메시지를 요청 객체에 저장하여 login.jsp페이지로 이동한다.
			
		}else if(result == -1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
			//userCheck메소드의 리턴 값이 -1이면 아이디가 맞지 않는 것이다. "존재하지 않는 회원입니다"란 메시지를 요청 객체에 저장하여 login.jsp페이지로 이동한다.
		}
		request.getRequestDispatcher(url).forward(request, response);
		
	}

}
