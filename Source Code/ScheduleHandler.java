package hcs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

public class ScheduleHandler
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
	ScheduleHandler()
	{
		tableName = "schedule_table";
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
	
	//returns true if an appointment with given doctor,date,halfHour exists
	public boolean appointmentExists(String doctor, String date, String halfHour)
	{
		try
		{			
			resultSet = statement.executeQuery("SELECT * FROM "+tableName+" WHERE doctor = '"+doctor
								+"' AND date = '"+date+"' AND half_hour = '"+halfHour+"';");
			return resultSet.first();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			return false;
		}
	}
	
	//adds a new appointment with given values to database
	//note: throws an error if given doctor,date,halfHour already exists
	public void createAppointment(String doctor, String date, String halfHour, String patientName)
	{
		String values = "('"+doctor+"','"+date+"','"+halfHour+"','"+patientName+"')";
		double invoice_amt = generateInvoiceAmount(), copay_amt = generateCopayAmount();
		double total = invoice_amt+copay_amt;

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
	public void changeAppointment(String old_doctor, String old_date, String old_time, String patient_name, String new_doctor, String new_date, String new_time)
	{
		createAppointment(new_doctor,new_date,new_time,patient_name);
		cancelAppointment(old_doctor,old_date,old_time,patient_name);
	}
	
	//deletes appointment
	public void cancelAppointment(String doctor, String date,String halfHour,String patient_name)
	{
		try
		{
			statement.execute("DELETE FROM "+tableName+" WHERE doctor = '"+doctor
								+"' AND date = '"+date+"' AND half_hour = '"+halfHour+"';");
			//statement.execute("UPDATE reports_table SET num_patients = num_patients-1 WHERE doctor = '"+doctor+"' AND date = '"+date+"'");
			//statement.execute("DELETE FROM payment_table WHERE patient_name = '"+patient_name+"' AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//charges for no show appointments
	public void chargeNoShow(String date)
	{
		try
		{
			resultSet = statement.executeQuery("SELECT * FROM schedule_table WHERE date = '"+date+"'");
			ArrayList<String> patient_names = new ArrayList<String>();
			while(resultSet.next())
			{
				patient_names.add(resultSet.getString(4));
			}
			
			resultSet = null;
			
			for(String name : patient_names)
			{
				String values = "('"+name+"','"+date+"','Invoice','Unpaid',25,null,null,null)";
				statement.execute("INSERT INTO payment_table VALUES "+values);
			}
			
			statement.execute("DELETE FROM schedule_table WHERE date = '"+date+"';");
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
	
	public static int generateInvoiceAmount()
	{
		int rand = (new Random()).nextInt(9000) + 1000;
		return rand;
	}
	
	public static int generateCopayAmount()
	{
		int rand = (new Random()).nextInt(90) + 10;
		return rand;
	}
}