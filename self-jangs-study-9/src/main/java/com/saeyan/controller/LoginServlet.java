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
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		
		HttpSession session =request.getSession();
		
		if(session.getAttribute("loginUser") != null) {
			url = "member/main.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

	//로그인을 위해서 입력받은 아이디와 암호로 인증을 처리하기 위해서는 데이터베이스에 이러한 회원 정보가 존재하는지 살펴보아야 한다.
	//이를 위한 메소드는 userCheck()와 getMember()이다.
	//userCheck()는 member 테이블에서 아이디와 암호를 비교해서 해당 아이디가 존재하지 않으면 -1을, 아이디만 일치하고 암호가 다르면 0을, 모두 일치하면 1을 리턴한다.
	//getMember()는 member테이블에서 아이디로 해당 회원을 찾아 회원 정보를 MemberVO 객체로 가져온다.
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String url = "member/login.jsp";
		//회원 인증이 실패했을 때 이동할 login.jsp를 url변수에 저장한다.
		String userid = request.getParameter("userid");
		//login.jsp 로그인 폼에서 입력한 회원의 아이디를 얻어와서 변수 userid에 저장한다.
		String pwd = request.getParameter("pwd");
		//login.jsp 로그인 폼에서 입력한 회원의 비밀번호를 얻어와서 변수 pwd에 저장한다.
		
		MemberDAO mDao = MemberDAO.getinstance();
		//입력받은 아이디와 암호로 회원 인증 여부를 물어보기 위해서 회원 가입시 회원 정보를 member 테이블에 저장해 두었기 때문에 member테이블에 존재하는 아이디인지 확인하기 위해
		//MemberDAO를 사용하기 위해 객체생성을 한다.
		int result = mDao.userCheck(userid, pwd);
		//MemberDAO 의 userCheck() 메소드에서 아이디에 해당하는 회원이 존재하는지를 조회한다.
		//여기에서 얻은 값을 result에 가져온다.
		
		if(result == 1) {
			//resutl == 1이라는 것은 아이디와 비밀번호가 일치한다는 것
			MemberVO mVo = mDao.getMember(userid);
			//getMember 메소드를 통해 userid가 위에서 입력이 된 것을 통해 모든 정보를 가지고 와서 VO객체의 매개변수인 mVo에 저장한다.
			HttpSession session = request.getSession();
			//session 객체를 얻어서 session 변수에 저장한다.
			//로그인 인증 처리된 회원 정보는 다른 사이트에 갔다 돌아와도 다시 로그인하지 않도록 세션에 등록해 두어야 한다. 회원정보를 세션에 저장해둔다.
			session.setAttribute("loginUser", mVo);
			//setAttribute()는 (String name, Object value)로 구성되며, 저장한 값을 식별하기 위한 값이 string name이며, 세션에 저장하는 값은 Object형이므로 어떠한 자료 형태라도 저장할 수 있다.
			//loginUser라는 문구를 쓰게 되면 mVo의 정보들이 들어있기 때문에 사용할수있다.
			request.setAttribute("message", "회원 가입에 성공했습니다.");
			//jsp페이지에 보낼 메시지를 요청 객체에 저장해 둔다.
			url = "member/main.jsp";
			//회원 인증이 되었을 경우 회원 테이블에서 회원 정보를 얻어와 이를 MemberVO 객체에 저장해 둔 상태이다.(getMember메소드를 통해)
			//웹사이트를 사용하다보면 게시글을 올리거나 상품을 주문하게 될 경우 회원 정보가 필요하게 되는데 이럴 때마다 회원 정보를 데이터베이스에서 얻어온다면 번거로울 것이다.
			//그래서 로그인하면서 얻어온 회원 정보를 세션에 저장해 두면 어느 페이지에서든 세션에 저장된 회원 정보를 얻어올 수 있어서 편리하다.
			//그래서 로그인 인증 처리가 끝나면 회원 정보를 세션에 저장해 두곤 한다. 
			//인증에 성공해야만 이동 가능한 main.jsp 페이지로 가도록 main.jsp 를 url변수에 저장한다.
			
		}else if(result == 0) {
			request.setAttribute("message", "비밀번호가 맞지 않습니다.");
			
		}else if(result == -1) {
			request.setAttribute("message", "존재하지 않는 회원입니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}

}
