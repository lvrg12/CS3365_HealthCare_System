package hcs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentHandler
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
	public PaymentHandler()
	{
		tableName = "payment_table";
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
	
	//adds a new payment record with given values to database
	//note: throws an error if given SSN and date already exist
	public void addPaymentRecord(int SSN, String date, String paymentType, boolean isPaid, double amount,
							int paymentRef, int card)
	{
		String values = "("+SSN+",'"+date+"','"+paymentType+"',"+isPaid+","+amount+","+paymentRef+",'"+card+"')";
		try
		{
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the paid of patient with the given SSN, date, paymentType
	public void setIsPaid(boolean isPaid, int SSN, String date, String paymentType)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET is_paid = "+isPaid+" WHERE SSN = "+SSN
								+" AND date = '"+date+"' AND payment_type = '"+paymentType+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the amount of patient with the given SSN, date, paymentType
	public void setAmount(double amount, int SSN, String date, String paymentType)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET amount = "+amount+" WHERE SSN = "+SSN
								+" AND date = '"+date+"' AND payment_type = '"+paymentType+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the paymentRef of patient with the given SSN, date, paymentType
	public void setPaymentRef(int paymentRef, int SSN, String date, String paymentType)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET payment_ref = "+paymentRef+" WHERE SSN = "+SSN
								+" AND date = '"+date+"' AND payment_type = '"+paymentType+"';");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//sets the card number of patient with the given SSN, date, paymentType
	public void setCard(String card, int SSN, String date, String paymentType)
	{
		try
		{
			statement.execute("UPDATE "+tableName+" SET card = '"+card+"' WHERE SSN = "+SSN
								+" AND date = '"+date+"' AND payment_type = '"+paymentType+"';");
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