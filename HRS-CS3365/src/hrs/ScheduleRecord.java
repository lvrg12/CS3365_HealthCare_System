package hrs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class ScheduleRecord //handler
{
	//database variables
	private static final String DATABASE_URL = "jdbc:mysql://localhost/hcs_schema_test?useSSL=true";
	private Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	
	private String tableName;
	
	//constructor initializes connection, statement, resultSet, and the table name
	ScheduleRecord()
	{
		tableName = "schedule_record_table_test";
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
	}
	
	//returns true if an appointment with given doctor,date,halfHour
	public boolean appointmentExists(String doctor, String date, String halfHour)
	{
		try
		{			
			resultSet = statement.executeQuery("SELECT * FROM "+tableName+" WHERE doctor = '"+doctor
								+"' AND date = '"+date+"' AND halfHour = '"+halfHour+"';");
			return resultSet.first();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			return true;
		}
	}
	
	//adds a new appointment with given values to database
	//note: throws an error if given doctor,date,halfHour already exists
	public void createAppointment(String doctor, String date, String halfHour, String patientName)
	{
		String values = "('"+doctor+"','"+date+"','"+halfHour+"','"+patientName+"')";
		try
		{
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//creates a new appointment with given values to database and deletes old appointment
	//note: throws an error if given doctor,date,halfHour already exists
	public void changeAppointment(String doctor, String date, String halfHour, String patientName, String newDoctor, String newDate, String newHalfHour)
	{
		createAppointment(newDoctor,newDate,newHalfHour,patientName);
		cancelAppointment(doctor,date,halfHour);
	}
	
	//deletes appointment
	public void cancelAppointment(String doctor, String date,String halfHour)
	{
		try
		{
			statement.execute("DELETE FROM "+tableName+" WHERE doctor = '"+doctor
								+"' AND date = '"+date+"' AND halfHour = '"+halfHour+"';");
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
			System.out.println("Schedule Information Table:");
			
			//for(int i = 1; i<=numberOfColumns; i++)
				//System.out.print(metaData.getColumnName(i)  + "\t\t");
			//System.out.println();
			
			System.out.print(metaData.getColumnName(1)  + "\t\t");
			System.out.print(metaData.getColumnName(2)  + "\t\t\t\t");
			System.out.print(metaData.getColumnName(3)  + "\t");
			System.out.print(metaData.getColumnName(4)  + "\t\t\t\n");
			
			
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
