import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class PaymentRecord
{
	//database variables
	private Statement statement;
	private ResultSet resultSet;
	
	private String tableName;
		
	//constructor initializes connection, statement, resultSet, and the table name
	public PaymentRecord(Connection connection)
	{
		tableName = "payment_record_table_test";
		try
		{
			statement = connection.createStatement();
			resultSet = null;
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//adds a new payment record with given values to database
	//note: throws an error if given SSN and date already exist
	public void addPaymentRecord(int SSN, String date, String paymentType, boolean isPaid, double amount,
							int paymentRef)
	{
		String values = "("+SSN+",'"+date+"','"+paymentType+"',"+isPaid+","+amount+","+paymentRef+")";
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
			statement.execute("UPDATE "+tableName+" SET isPaid = "+isPaid+" WHERE SSN = "+SSN
								+" AND date = '"+date+"' AND paymentType = '"+paymentType+"';");
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
								+" AND date = '"+date+"' AND paymentType = '"+paymentType+"';");
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
			statement.execute("UPDATE "+tableName+" SET paymentRef = "+paymentRef+" WHERE SSN = "+SSN
								+" AND date = '"+date+"' AND paymentType = '"+paymentType+"';");
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
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
		}
	}
}