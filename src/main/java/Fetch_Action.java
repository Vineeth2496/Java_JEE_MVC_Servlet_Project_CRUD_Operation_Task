

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servletproject1.Data;
import servletproject1.Service;

@WebServlet("/Fetch_Action")
public class Fetch_Action extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		PrintWriter out=res.getWriter();
		HttpSession hs=req.getSession(false);
		if(hs!= null) 
		{
			//out.println("<marquee>Welcome</marquee>");
			
			Service s=new Service();
			Vector<Data> v=s.fetch();
			out.println(" <table border=\\\"1px\\ border-collapse= collapse; >\r\n"
					+ "      <tr >\r\n"
					+ "        <th>First Name</th>\r\n"
					+ "        <th>Last Name</th>\r\n"
					+ "        <th>mobilenumber</th>\r\n"
					+ "        <th>Gender</th>\r\n"
					+ "        <th>Lang</th>\r\n"
					+ "        <th>Email</th>\r\n"
					+ "        <th>Username</th>\r\n"
					+ "        <th>Password</th>\r\n"
					+ "      </tr>\r\n");
			for(Data d:v) 
			{
				out.println( "<tr>\r\n"

					+ "        <td>"+d.getFirstname()+"</td>\r\n"
					+ "        <td>"+d.getLastname()+"</td>\r\n"
					+ "        <td>"+d.getMobilenumber()+"</td>\r\n"
					+ "        <td>"+d.getGender()+"</td>\r\n"
					+ "        <td>"+d.getLang()+"</td>\r\n"
					+ "        <td>"+d.getEmail()+"</td>\r\n"
					+ "        <td>"+d.getUsername()+"</td>\r\n"
					+ "        <td>"+d.getPassword()+"</td>\r\n"
					+ "      </tr>\r\n");
			}
			
			out.println(" </table>");
			
			RequestDispatcher rd= req.getRequestDispatcher("Logout_Action");
			rd.include(req, res);
			
		}else {
			out.println("Login to fetch data");
			RequestDispatcher rd= req.getRequestDispatcher("Login_Page.html");
			rd.include(req, res);
		}
	}

	
}
