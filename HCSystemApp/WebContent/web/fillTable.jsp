<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%
	String DATABASE_URL = "jdbc:mysql://localhost/hcs_schema?useSSL=true";
	String JDBC_DRIVER= "com.mysql.jdbc.Driver";
	String username = "root";
	String password = "Abiel.242.";

	try
	{
		Class.forName(JDBC_DRIVER);
	}
	catch (ClassNotFoundException e)
	{
		e.printStackTrace();
	}

	Connection connection = null;
	Statement statement = null;
	ResultSet resultSet = null;
%>

<script>

function fillScheduleTable(headName, tableName)
{
	var table = document.getElementById(tableName);
    var i=0, row, cell;
    
	<%
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM schedule_table";
			resultSet = statement.executeQuery(sql);
			String doctor, date, time, patient_name;
			
			while(resultSet.next())
			{
				doctor = resultSet.getString(1);
				date = resultSet.getString(2);
				time = resultSet.getString(3);
				patient_name = resultSet.getString(4);
	%>
				row = addAfter(headName);
				cell = row.insertCell(i++);
    			cell.innerHTML = "<%=date%>";
    			
    			cell = row.insertCell(i++);
    			cell.innerHTML = "<%=time%>";
    			
    			cell = row.insertCell(i++);
    			cell.innerHTML = "<%=doctor%>";
    			
    			cell = row.insertCell(i++);
    			cell.innerHTML = "<%=patient_name%>";
    			i=0;
	<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

function fillPatientAccountTable(headName, tableName)
{
	var table = document.getElementById(tableName);
    var i=0, row, cell;
    
	<%
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM patient_account_table";
			resultSet = statement.executeQuery(sql);
			String patient_name, address, phone_number, email, SSN, insurance;
			
			while(resultSet.next())
			{
				patient_name = resultSet.getString(1);
				address = resultSet.getString(2);
				phone_number = resultSet.getString(3);
				email = resultSet.getString(4);
				SSN = resultSet.getString(5);
				insurance = resultSet.getString(6);
	%>
				row = addAfter(headName);
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=SSN%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=patient_name%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=address%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=phone_number%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=email%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=insurance%>";
				i=0;
	<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

function fillTreatmentTable(headName, tableName)
{
	var table = document.getElementById(tableName);
    var i=0, row, cell;
    
	<%
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM treatment_table";
			resultSet = statement.executeQuery(sql);
			String patient_name,date,weight,height,blood_pressure;
			String reason_for_visit,treatment,prescription;
			
			while(resultSet.next())
			{
				patient_name = resultSet.getString(1);
				date = resultSet.getString(2);
				weight = resultSet.getString(3);
				height = resultSet.getString(4);
				blood_pressure = resultSet.getString(5);
				reason_for_visit = resultSet.getString(6);
				treatment = resultSet.getString(7);
				prescription = resultSet.getString(8);
	%>
				row = addAfter(headName);
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=patient_name%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=date%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=weight%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=height%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=blood_pressure%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=reason_for_visit%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=treatment%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=prescription%>";
				i=0;
	<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

function fillPaymentTable(headName, tableName)
{
	var table = document.getElementById(tableName);
    var i=0, row, cell;
    
	<%
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM payment_table";
			resultSet = statement.executeQuery(sql);
			String SSN,date,payment_type,is_paid;
			String amount, payment_ref, card;
			
			while(resultSet.next())
			{
				SSN = resultSet.getString(1);
				date = resultSet.getString(2);
				payment_type = resultSet.getString(3);
				is_paid = resultSet.getString(4);
				amount = resultSet.getString(5);
				payment_ref = resultSet.getString(6);
				card = resultSet.getString(7);
	%>
				row = addAfter(headName);
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=SSN%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=date%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=payment_type%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=is_paid%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=amount%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=payment_ref%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=card%>";
				i=0;
	<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

function fillPatientPaymentTable(headName, tableName)
{
	var table = document.getElementById(tableName);
    var i=0, row, cell;
    
	<%
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM payment_table WHERE payment_type = 'Invoice' AND patient_name = (SELECT name FROM permission_table)";
			resultSet = statement.executeQuery(sql);
			String patient_name,date,payment_type,is_paid;
			String amount, payment_ref, card, receipt_given;
			
			while(resultSet.next())
			{
				patient_name = resultSet.getString(1);
				date = resultSet.getString(2);
				is_paid = resultSet.getString(4);
				amount = resultSet.getString(5);
				payment_ref = resultSet.getString(6);
				card = resultSet.getString(7);
				receipt_given = resultSet.getString(8);
	%>
				row = addAfter(headName);
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=patient_name%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=date%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=is_paid%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=amount%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=payment_ref%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=card%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=receipt_given%>";
				i=0;
	<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

function fillReportsTable(headName, tableName)
{
	var table = document.getElementById(tableName);
    var i=0, row, cell;
    
	<%
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql;
			String month = request.getParameter("month");
			String date = request.getParameter("date");
			if(date!=null)
			{
				sql = "SELECT * FROM reports_table WHERE date = '"+date+"'";
			}
			else if(month!=null)
			{
				sql = "SELECT * FROM reports_table WHERE SUBSTRING(date,1,7) = '"+month+"'";
			}
			else
			{
				sql = "SELECT * FROM reports_table";
			}			
			resultSet = statement.executeQuery(sql);
			String doctor,num_patients,income;

			while(resultSet.next())
			{
				date = resultSet.getString(1);
				doctor = resultSet.getString(2);
				num_patients = resultSet.getString(3);
				income = resultSet.getString(4);

	%>
				row = addAfter(headName);
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=date%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=doctor%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=num_patients%>";
				
				cell = row.insertCell(i++);
				cell.innerHTML = "<%=income%>";
				i=0;
	<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

</script>