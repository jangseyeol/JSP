package unit01;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   		System.out.println("doGet...");
   		String name = request.getParameter("name");
   		String age = request.getParameter("age");
   		System.out.println("name : "+ name);
   		System.out.println("age : "+ age);
   		
   		response.setContentType("text/html; charset=utf-8;");
   		
   		response.setCharacterEncoding("utf-8");
   		
   		PrintWriter out = response.getWriter();
   		out.println("한글!!");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
