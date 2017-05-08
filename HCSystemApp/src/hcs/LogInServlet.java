package hcs;
import java.sql.*;
import java.util.Arrays;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/LogInServlet")
public class LogInServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	//JDBC driver name and database URL
    static final String JDBC_DRIVER="com.mysql.jdbc.Driver";
	
    String DATABASE_URL = "jdbc:mysql://localhost/hcs_schema?useSSL=true";
	Connection connection;
	Statement statement;
	ResultSet resultSet;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		String url = "web/HCS.jsp";
		String query = request.getQueryString();
		
		//Retrieving data from jsp file
		if(request.getParameter("username")!=null)
		{
			url = url + "?"+ query;
			String[] username = request.getParameter("username").split("[@._]");
			username[1] = username[0].substring(0, 1).toUpperCase() + username[0].substring(1)
							+ " " + username[1].substring(0, 1).toUpperCase() + username[1].substring(1);

	        try
	        {        	
	        	//Register JDBC driver
				Class.forName(JDBC_DRIVER);

				//Open a connection
				connection = DriverManager.getConnection(DATABASE_URL, "root", "Abiel.242.");
				statement = connection.createStatement();
				resultSet = null;
				 
				//Execute SQL query
				statement.execute("UPDATE permission_table SET user_type = '"+username[0]+"', name = '"+username[1]+"'");
				
				//Clean-up environment
				statement.close();
				connection.close();
			}
	        catch(SQLException se)
	        {
	        	//Handle errors for JDBC
	        	se.printStackTrace();
	        }
	        catch(Exception e)
	        {
	        	//Handle errors for Class.forName
	        	e.printStackTrace();
	        }
			response.sendRedirect(url);
		}
		else
		{
			response.sendRedirect("web/HCSLogIn.jsp");
		}
	}
}
