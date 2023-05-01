

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servletproject1.Data;
import servletproject1.Service;


@WebServlet("/Registration_Action")
public class Registration_Action extends HttpServlet {
	
	public void init(ServletConfig sc) throws ServletException
	{
		
	}
	public void doPost(HttpServletRequest req,HttpServletResponse res) throws ServletException,IOException
	{
		String fn=req.getParameter("fn");
		String ln=req.getParameter("ln");
		String mn=req.getParameter("mn");
		String gender=req.getParameter("gender");
		String lang=req.getParameter("lang");
		String email=req.getParameter("email");
		String un=req.getParameter("un");
		String pw=req.getParameter("cpw");
		String img=req.getParameter("img");
	
		Data d=new Data();
		d.setFirstname(fn);
		d.setLastname(ln);
		d.setMobilenumber(mn);
		d.setGender(gender);
		d.setLang(lang);
		d.setEmail(email);
		d.setUsername(un);
		d.setPassword(pw);
		
		Service s=new Service();
		int i=s.save(d);
		PrintWriter out=res.getWriter();
		if(i!=0)
		{
			out.println("<h2>Registration Success</h2>");
		}else
		{
			out.println("<h2>Registration Failed</h2>");
		}
		
	}
	public void destroy()
	{
		
	}

	
	
}
