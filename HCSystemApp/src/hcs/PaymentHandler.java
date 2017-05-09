package hcs;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

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
	public void createPaymentRecord(String patient_name, String date, String payment_type, double amount)
	{
		String values = "('"+patient_name+"','"+date+"','"+payment_type+"','Unpaid',"+amount+",null,null,null)";
		try
		{			
			statement.execute("INSERT INTO "+tableName+" VALUES "+values);
			
			//updating reports
			resultSet = statement.executeQuery("SELECT doctor FROM treatment_table WHERE patient_name = '"+patient_name+"' AND date = '"+date+"'");
			resultSet.next();
			String doctor = resultSet.getString(1);
			statement.execute("UPDATE reports_table SET income = income+"+amount+" WHERE doctor = '"+doctor+"' AND date = '"+date+"'");
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//adds a new payment record with given values to database
	//note: throws an error if given SSN and date already exist
	public void makeCopayPayment(String patient_name, String date, String card, String receipt_given)
	{
		int payment_ref = generatePaymentRef();
		try
		{
			String sql;
			sql = "UPDATE "+tableName+" SET is_paid = 'Paid',"
					+ " payment_ref = '"+payment_ref+"',"
					+ " receipt_given = '"+receipt_given+"' ,"
					+ "card = '"+card+"' "
					+ "WHERE patient_name = '"+patient_name+"' "
					+ "AND date = '"+date+"' "
					+ "AND payment_type = 'Copay'";
			statement.execute(sql);
			if(receipt_given.equals("on"))
			{
				resultSet = statement.executeQuery("SELECT amount FROM payment_table "
						+ "WHERE patient_name = '"+patient_name+"' AND "
						+ "payment_type = 'Copay' AND date = '"+date+"'");
				resultSet.next();
				String amount = resultSet.getString(1);
				resultSet = null;
				resultSet = statement.executeQuery("SELECT email FROM patient_account_table "
						+ "WHERE patient_name = '"+patient_name+"'");
				resultSet.next();
				String email = resultSet.getString(1);
				sendReceipt(patient_name,date,"Copay",card,payment_ref,amount,email);
			}
		}
		catch(SQLException sqlException)
		{
			sqlException.printStackTrace();
		}
	}
	
	//updates payment record to paid
	public void makeInvoicePayment(String patient_name, String date, String card, String receipt_given)
	{
		int payment_ref = generatePaymentRef();
		try
		{
			String sql;
			sql = "UPDATE "+tableName+" SET is_paid = 'Paid',"
					+ " payment_ref = '"+payment_ref+"',"
					+ " receipt_given = '"+receipt_given+"' ,"
					+ "card = '"+card+"' "
					+ "WHERE patient_name = '"+patient_name+"' "
					+ "AND date = '"+date+"' "
					+ "AND payment_type = 'Invoice'";
			
			statement.execute(sql);
			if(receipt_given.equals("on"))
			{
				resultSet = statement.executeQuery("SELECT amount FROM payment_table "
						+ "WHERE patient_name = '"+patient_name+"' AND "
						+ "payment_type = 'Invoice' AND date = '"+date+"'");
				resultSet.next();
				String amount = resultSet.getString(1);
				
				resultSet = null;
				resultSet = statement.executeQuery("SELECT email FROM patient_account_table "
						+ "WHERE patient_name = '"+patient_name+"'");
				resultSet.next();
				String email = resultSet.getString(1);
				sendReceipt(patient_name,date,"Invoice",card,payment_ref,amount,email);
			}
			
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
	
	public int generatePaymentRef()
	{
		int rand = (new Random()).nextInt(900000000) + 100000;
		return rand;
	}
	
	public void sendReceipt(String patient_name,String date,String payment_type,
			String card, int payment_ref, String amount, String email)
	{	
		//generating receipt
		String receipt = "PAYMENT RECEIPT\n"
						+ payment_type + " Payment\n"
						+ "Paid By: " + patient_name+"\n"
						+ "Date: " + date + "\n"
						+ "Amount: " + amount + "\n"
						+ "Card Number: " + card + "\n"
						+ "Payment Reference: " + payment_ref + "\n";
		
		//Sending email
		final String username = "ceohcs3365@gmail.com";
		final String password = "helloworld";
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });
		try
		{
			
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("ceo.hcs.cs3365@gmail.com"));
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(email));
			message.setSubject("HealthCareSystem RECEIPT");
			message.setText(receipt);
			Transport.send(message);
		}
		catch (MessagingException e)
		{
			throw new RuntimeException(e);
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