package servletproject1;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Service {

	
	public static Connection mycon()
	{
		Connection con=null;
		try {
			FileInputStream fis=new FileInputStream("C:\\Users\\home\\eclipse-workspace\\servletproject1\\src\\main\\java\\servletproject1\\JDBC.properties");
			Properties p=new Properties();
			p.load(fis);
			Class.forName(p.getProperty("driver"));
			con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("password"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;		
	}



    public int save(Data d)
    {
	int i=0;
	try
	{
		Connection con=Service.mycon();
		PreparedStatement pst=con.prepareStatement("insert into mvc2project values (?,?,?,?,?,?,?,?)");
		pst.setString(1, d.getFirstname());
		pst.setString(2, d.getLastname());
		pst.setString(3, d.getMobilenumber());
		pst.setString(4, d.getGender());
		pst.setString(5, d.getLang());
		pst.setString(6, d.getEmail());
		pst.setString(7, d.getUsername());
		pst.setString(8, d.getPassword());
		i=pst.executeUpdate();
		return i;
	}
	catch (Exception e) 
	{
		// TODO: handle exception
		e.printStackTrace();
		return i;
	  }

    }
    public int update(Data d)
	{
		int i=0;
		try
		{
			Connection con=Service.mycon();
			PreparedStatement pst=con.prepareStatement("update mvc2project set password=? where password=?");
			pst.setString(1, d.getNewpassword());
			pst.setString(2, d.getOldpassword());
			i=pst.executeUpdate();
			return i;
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return i;
		}
	}
	public Vector validation(Data d)
	{
		Vector v=new Vector();
		
		try
		{
			Connection con=Service.mycon();
			PreparedStatement pst=con.prepareStatement("select username,password from mvc2project where username=?");
			pst.setString(1, d.getUn());
			ResultSet rs= pst.executeQuery();
			
			RowSetFactory rsf= RowSetProvider.newFactory();
			CachedRowSet crs=rsf.createCachedRowSet();
			crs.populate(rs);
			pst.close();
			for(;crs.next();) {
				d.setUsername(crs.getString("username"));
				d.setPassword(crs.getString("password"));
				v.add(d);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return v;
	}
	
	public Vector fetch()
	{
		Vector v=new Vector();
		try
		{
			FileInputStream fis=new FileInputStream("C:\\Users\\home\\eclipse-workspace\\servletproject1\\src\\main\\java\\servletproject1\\JDBC.properties");
			Properties p=new Properties();
			p.load(fis);
			Class.forName(p.getProperty("driver"));
			RowSetFactory rsf= RowSetProvider.newFactory();
			JdbcRowSet jrs= rsf.createJdbcRowSet();
			jrs.setUrl(p.getProperty("url"));
			jrs.setUsername(p.getProperty("username"));
			jrs.setPassword(p.getProperty("password"));
			jrs.setCommand("select * from mvc2project ");
			jrs.execute();
			Data d=new Data();
			for(;jrs.next();)
			{
				d.setFirstname(jrs.getString("FIRSTNAME"));
				d.setLastname(jrs.getString("LASTNAME"));
				d.setMobilenumber(jrs.getString("mobilenumber"));
				d.setGender(jrs.getString("GENDER"));
				d.setLang(jrs.getString("LANG"));
				d.setEmail(jrs.getString("EMAIL"));
				d.setUsername(jrs.getString("USERNAME"));
				d.setPassword(jrs.getString("PASSWORD"));
				v.add(d);
			}
			
			
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return v;
	}
	public int delete(Data d) {
		int i=0;
		try {
			Connection con=Service.mycon();
			PreparedStatement pst=con.prepareStatement("delete mvc2project where username=?");
			pst.setString(1, d.getUn());
			i=pst.executeUpdate();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return i;
	}

 }
