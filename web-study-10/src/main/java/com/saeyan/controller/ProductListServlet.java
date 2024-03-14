package com.saeyan.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.ProductDAO;
import com.saeyan.dto.ProductVO;

@WebServlet("/productList.do")
public class ProductListServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	//전체 데이터 목록을 보여주기 위해
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//DB자료 ProductDAO 의 getInstance에서 가지고 있어서 객체 생성
		//productList.do 요청에 의해서는 상품 리스트를 출력해야 하기 때문에 데이터베이스에 상품 정보를 얻어 와야 한다.
		//그래서 데이터베이스 처리를 담당하고 있는 ProductDAO 객체를 얻어오기 위해 ProductDAO 클래스의 정적(static) 메소드인 getInstance()를 호출한다.
		ProductDAO pDao = ProductDAO.getInstance();
		
		//list 에 목록 넣기
		//ProductDAO 객체로 상품 정보를 최근 등록된 순으로 얻어오는 selectAllProducts() 메소드를 호출하여
		//List<ProductVO>객체에 저장해 둔다.
		List<ProductVO> list = pDao.selectAllProducts(); // ctrl + 2 누르고 L 있는 거 누르면 이렇게 변환됨
		
		//담아줌
		//서블릿에서 product 테이블의 정보를 얻어온 후에 이를 jsp페이지에 보내야 한다.
		//서블릿에서 jsp페이지로 데이터를 전송하려면 request객체의 속성에 실어 보낸다.
		request.setAttribute("productList", list);
		
		// 화면이동
		//productList.jsp로 포워딩한다. 여기서 작성한 서블릿이 제대로 작동하려면 상품 리스트를 위한 페이지가 필요하다.
		request.getRequestDispatcher("product/productList.jsp").forward(request, response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
