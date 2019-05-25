 package hostelmanagementsystem;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/TestServlet")
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Resource(name="jdbc/web_student_tracker")
	private DataSource datasource;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/plain");
		
		Connection myConn=null;
		PreparedStatement pst=null;
		ResultSet myRs=null;
		
		try {
			myConn=datasource.getConnection();
			String sql="select * from authenticating WHERE USER_NAME=? AND PASSWORD=?";
			pst=myConn.prepareStatement(sql);
			
			pst.setString(1, uname);
			pst.setString(2, passw);
			myRs=pst.executeQuery();
			 
			
			while( myRs.next()){
				String email=myRs.getString("email");
				String name=myRs.getString("first_name");
				out.println(email);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

}
