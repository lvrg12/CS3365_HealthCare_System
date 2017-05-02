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
function fillTable(headName, tableName)
{
	if(tableName == "SchedTable")
	{
		fillScheduleTable(headName, tableName);
	}
	else if(tableName == "PatientAccTable")
	{
		fillPatientAccountTable(headName, tableName);
	}
	else if(tableName == "TreatRecTable")
	{
		fillTreatmentTable(headName, tableName);
	}
	else if(tableName == "PayRecTable")
	{
		fillPaymentTable(headName, tableName);
	}
	/*
	else if(tableName == "ReportsTable")
	{
		fillTreatmentTable(headName, tableName);
	}
	else
	{
		fillTreatmentTable(headName, tableName);
	}
	*/
	else{}
}

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
			String patient_name, address, phone_number, email, SSN;
			
			while(resultSet.next())
			{
				patient_name = resultSet.getString(1);
				address = resultSet.getString(2);
				phone_number = resultSet.getString(3);
				email = resultSet.getString(4);
				SSN = resultSet.getString(5);
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
			String SSN,date,weight,height,blood_pressure;
			String reason_for_visit,treatment,prescription;
			
			while(resultSet.next())
			{
				SSN = resultSet.getString(1);
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
				cell.innerHTML = "<%=SSN%>";
				
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

</script>