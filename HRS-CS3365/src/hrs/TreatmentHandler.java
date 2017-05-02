package hcs;
import java.sql.*;

public class TreatmentHandler
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
	public TreatmentHandler()
	{
		tableName = "treatment_table";
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
	
	//adds a new account with given values to database
	//note: throws an error if given SSN and date already exist
	public void addTreatmentRecord(int SSN, String date, double weight, double height, int bloodPressure,
							String reasonForVisit, String treatment, String prescription)
	{
		String values = "("+SSN+",'"+date+"',"+weight+","+height+","+bloodPressure+",'"+reasonForVisit
							+"','"+treatment+"','"+prescription+"')";
		try
		{
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the weight of patient with the given SSN and date
	public void setWeight(double weight, int SSN, String date)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET weight = '"+weight+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the height of patient with the given SSN and date
	public void setHeight(double height, int SSN, String date)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET height = '"+height+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the bloodPressure of patient with the given SSN and date
	public void setBloodPressure(int bloodPressure, int SSN, String date)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET blood_pressure = '"
								+bloodPressure+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the reasonForVisit of patient with the given SSN and date
	public void setReasonForVisit(String reasonForVisit, int SSN, String date)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET reason_for_visit = '"
								+reasonForVisit+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the treatment of patient with the given SSN and date
	public void setTreatment(String treatment, int SSN, String date)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET treatment = '"+treatment+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the prescription of patient with the given SSN and date
	public void setPrescription(String prescription, int SSN, String date)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET prescription = '"
								+prescription+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"';");
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
			if(resultSet!=null)
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