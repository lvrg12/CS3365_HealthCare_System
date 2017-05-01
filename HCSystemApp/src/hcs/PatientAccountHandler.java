package hcs;
import java.sql.*;

public class PatientAccountHandler //handler
{
	//change this variables to be able
	//to run code in local machine
	private String username = "root";
	private String password = "Abiel.242.";
	
	//database variables
	private static final String DATABASE_URL = "jdbc:mysql://localhost/hcs_schema?useSSL=true";
	private static final String JDBC_DRIVER= "com.mysql.jdbc.Driver";
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private String tableName;
	
	//constructor initializes connection, statement, resultSet, and the table name
	PatientAccountHandler()
	{
		tableName = "patient_account_table";
		try
		{
			//Register JDBC driver
			Class.forName(JDBC_DRIVER);
			
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			resultSet = null;
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		catch(Exception e)
        {
        	//Handle errors for Class.forName
        	e.printStackTrace();
        }
	}
	
	//returns true if an account with given SSN already exists on the the database
	public boolean accountExists(int SSN)
	{
		try
		{			
			resultSet = statement.executeQuery("SELECT * FROM "+tableName+" WHERE SSN = "+SSN);
			return resultSet.first();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			return true;
		}
	}
	
	//adds a new account with given values to database
	//note: throws an error if given SSN already exists
	public void addAccount(String patientName, String address, String phoneNumber, String email, int SSN)
	{
		String values = "('"+patientName+"','"+address+"','"+phoneNumber+"','"+email+"',"+SSN+")";
		try
		{
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the name of patient with the given SSN
	public void setName(String patientName, int SSN)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET patient_name = '"+patientName+"' WHERE SSN = "+SSN+";");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the address of patient with the given SSN
	public void setAddress(String address, int SSN)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET address = '"+address+"' WHERE SSN = "+SSN+";");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the phoneNumber of patient with the given SSN
	public void setPhoneNumber(String phoneNumber, int SSN)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET phone_number = '"+phoneNumber+"' WHERE SSN = "+SSN+";");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the email of patient with the given SSN
	public void setEmail(String email, int SSN)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET email = '"+email+"' WHERE SSN = "+SSN+";");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//prints table for testing purposes
	public void printAll()
	{
	
		//connect to database books and query database
		try
		{
			//query database
			resultSet = statement.executeQuery("SELECT * FROM "+tableName);
			
			//process query results
			ResultSetMetaData metaData = resultSet.getMetaData();
			int numberOfColumns = metaData.getColumnCount();
			System.out.println("Patient Information Table:");
			
			//for(int i = 1; i<=numberOfColumns; i++)
				//System.out.print(metaData.getColumnName(i)  + "\t\t");
			//System.out.println();
			
			System.out.print(metaData.getColumnName(1)  + "\t\t");
			System.out.print(metaData.getColumnName(2)  + "\t\t\t\t");
			System.out.print(metaData.getColumnName(3)  + "\t");
			System.out.print(metaData.getColumnName(4)  + "\t\t\t");
			System.out.println(metaData.getColumnName(5)  + "\t\t");
			
			
			while(resultSet.next())
			{
				for(int i = 1; i<=numberOfColumns; i++)
					System.out.print(resultSet.getObject(i) + "\t");
				System.out.println();
			}
			
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//closes all sql variables handling potential errors/exceptions
	public void close()
	{
		try
		{
			resultSet.close();
			statement.close();
			connection.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}