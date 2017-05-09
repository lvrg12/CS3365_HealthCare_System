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
	
	public String getPermission()
	{
		try
		{
			//query database
			resultSet = statement.executeQuery("SELECT * FROM permission_table");
			resultSet.next();
			String permission = resultSet.getString(1);
			return permission;
			
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			return "";
		}
		
	}
	
	//adds a new account with given values to database
	//note: throws an error if given SSN and date already exist
	public void addTreatmentRecord(String patient_name, String date, String weight, String height, String blood_pressure, String reason_for_visit, String treatment, String prescription)
	{
		String values = "('"+patient_name+"','"+date+"','"+weight+"',\""+height+"\",'"+blood_pressure+"','"+reason_for_visit+"','"+treatment+"','"+prescription+"')";
		try
		{
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
			
			//adding record for reports_table
			resultSet = statement.executeQuery("SELECT doctor FROM schedule_table WHERE patient_name = '"+patient_name+"' AND date = '"+date+"'");
			resultSet.next();
			String doctor = resultSet.getString(1);
			
			if(doctorRecordExists(doctor,date))
			{
				statement.execute("UPDATE reports_table SET num_patients = num_patients+1 WHERE doctor = '"+doctor+"' AND date = '"+date+"'");
			}
			else //updating record for reports_table
			{
				statement.execute("INSERT INTO reports_table VALUES ('"+date+"','"+doctor+"',"+1+","+0+")");
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//staff
	//adds a new account with given values to database
	//note: throws an error if given SSN and date already exist
	public void addTreatmentRecord(String patient_name, String date)
	{
		try
		{
			//looking for requested doctor
			resultSet = statement.executeQuery("SELECT doctor FROM schedule_table WHERE patient_name = '"+patient_name+"' AND date = '"+date+"';");
			resultSet.next();
			String doctor = resultSet.getString(1);
			String values = "('"+patient_name+"','"+date+"',null,null,null,null,null,null,'"+doctor+"')";
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
			
			//adding record for reports_table
			if(doctorRecordExists(doctor,date))
			{
				statement.execute("UPDATE reports_table SET num_patients = num_patients+1 WHERE doctor = '"+doctor+"' AND date = '"+date+"'");
			}
			else //updating record for reports_table
			{
				statement.execute("INSERT INTO reports_table VALUES ('"+date+"','"+doctor+"',"+1+","+0+")");
			}
			
			//deleting appointment
			statement.execute("DELETE FROM schedule_table WHERE doctor = '"+doctor+"' AND date = '"+date+"' AND patient_name = '"+patient_name+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//nurse
	public void updateTreatmentRecord(String patient_name, String date, String weight, String height, String bloodPressure,
						String reasonForVisit)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET weight = '"+weight+"', height = \""+height
					+"\", blood_pressure = '"+bloodPressure+"', reason_for_visit = '"+reasonForVisit
					+"' WHERE patient_name = '"+patient_name+"' AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//doctor
	public void updateTreatmentRecord(String patient_name, String date, String treatment, String prescription)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET treatment = '"+treatment
			+"', prescription = '"+prescription+"' WHERE patient_name = '"+patient_name+"' AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}

	public void deleteTreatmentRecord(String patient_name, String date)
	{
		try
		{
			statement.execute("DELETE FROM "+tableName+" WHERE patient_name = '"+patient_name
					+"' AND date = '"+date+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//returns true if a doctor exists for a given date in the reports table
	public boolean doctorRecordExists(String doctor, String date)
	{
		try
		{			
			resultSet = statement.executeQuery("SELECT * FROM reports_table WHERE doctor = '"+doctor
								+"' AND date = '"+date+"';");
			return resultSet.first();
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
			return false;
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