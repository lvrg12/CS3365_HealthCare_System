package hrs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PatientAccount //handler
{
	//database variables
	private static final String DATABASE_URL = "jdbc:mysql://localhost/hcs_schema_test?useSSL=true";
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private String tableName;
	TreatmentRecord treatmentRecord;
	PaymentRecord paymentRecord;
	
	//constructor initializes connection, statement, resultSet, and the table name
	PatientAccount()
	{
		tableName = "patient_account_table_test";
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, "root", "YES");
			statement = connection.createStatement();
			resultSet = null;
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
		treatmentRecord = new TreatmentRecord(connection);
		paymentRecord = new PaymentRecord(connection);
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
			statement.execute("UPDATE "+tableName+" SET patientName = '"+patientName+"' WHERE SSN = "+SSN+";");
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
			statement.execute("UPDATE "+tableName+" SET phoneNumber = '"+phoneNumber+"' WHERE SSN = "+SSN+";");
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
			treatmentRecord.close();
			paymentRecord.close();
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}