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
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/join.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		//회원 가입 폼에서 입력 받은 한글이 깨지지 않도록 인코딩합니다.
		
		// 회원가입 폼에서 입력한 정보를 얻어옵니다.
		String name = request.getParameter("name");
		String userid = request.getParameter("userid");
		String pwd = request.getParameter("pwd");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String admin = request.getParameter("admin");
		
		//위에 입력받아 가져온 정보를 저장할 객체를 VO객체를 생성합니다.
		MemberVO mVo = new MemberVO();
		//name을 저장합니다.
		mVo.setName(name);
		//userid를 저장합니다.
		mVo.setUserid(userid);
		//pwd를 저장합니다.
		mVo.setPwd(pwd);
		//email을 저장합니다.
		mVo.setEmail(email);
		//phone을 저장합니다.
		mVo.setPhone(phone);
		//admin 정보를 저장합니다.그런데 여기에 setAdmin()은 int형이 들어가야 해서 int형으로 변환시켜줘야합니다.
		mVo.setAdmin(Integer.parseInt(admin));
		
		//MemberVO객체를 전달인자로 주어 MemberDAO객체로 insertMember메소드를 호출합니다.
		MemberDAO mDao = MemberDAO.getinstance();
		int result = mDao.insertMember(mVo);
		
		HttpSession session = request.getSession();
		
		if(result ==1) {
			session.setAttribute("userid", mVo.getUserid());
			//session에 "userid"를 키로 하여 지금 막 회원 가입한 회원의 아이디를 값으로 저장해두어 login.jsp로 제어를 이동하여 로그인 작업을 할 때 아이디를 입력받는 수고를 덜어 줍니다.
			request.setAttribute("message", "회원가입에 성공했습니다.");	
		}else {
			request.setAttribute("message", "회원가입에 실패했습니다.");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("member/login.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
