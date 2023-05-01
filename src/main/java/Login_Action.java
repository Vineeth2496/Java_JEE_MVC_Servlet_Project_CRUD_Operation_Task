

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servletproject1.Data;
import servletproject1.Service;

@WebServlet("/Login_Action")
public class Login_Action extends HttpServlet {
	
	public void init(ServletConfig sc) throws ServletException
	{
		
	}
	public void doGet(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		String un=req.getParameter("un");
		String pw=req.getParameter("pw");
		
		PrintWriter out=res.getWriter();
		
		Data d=new Data();
		
		d.setUn(un);
		d.setPw(pw);
		
		Service s=new Service();
		
		Vector<Data> v=s.validation(d);
		String username="",password="";
		for(Data dd:v)
		{
			username=d.getUsername();
			password=d.getPassword();
		}
		if(username.equals(d.getUn()) && password.equals(d.getPw()) ){
			//out.println("<h2>Login success</h2>");
			HttpSession hs=req.getSession();
			hs.setAttribute("username", d.getUn());
			hs.setAttribute("password", d.getPw());
			RequestDispatcher rd= req.getRequestDispatcher("Fetch_Action");
			rd.include(req, res);
		} 
		else {
			out.println("<html>");
			out.println("<h2>Incorrect Username/Password</h2>");
			out.println("</html>");

			RequestDispatcher rd=req.getRequestDispatcher("Login_Page.html");
		    rd.include(req, res);
		}
	}
	public void destroy()
	{
		
	}
}
