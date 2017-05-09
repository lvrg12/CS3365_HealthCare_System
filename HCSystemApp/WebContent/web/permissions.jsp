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

function permissions()
{
	<%
		int i;
		String userType = "";
	
		try
		{
			connection = DriverManager.getConnection(DATABASE_URL, username, password);
			statement = connection.createStatement();
			String sql = "SELECT * FROM permission_table";
			resultSet = statement.executeQuery(sql);
			resultSet.next();
			userType = resultSet.getString(1);
			
			
			if(userType.equals("staff"))
			{
				%>
				staffPermissions();
				<%
			}
			else if(userType.equals("nurse"))
			{
				%>
				nursePermissions();
				<%
			}
			else if(userType.equals("dr"))
			{
				%>
				doctorPermissions();
				<%
			}
			else if(userType.equals("CEO"))
			{
				%>
				CEOPermissions();
				<%
			}
			else
			{
				%>
				patientPermissions();
				<%
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	%>
}

function CEOPermissions() {
	var i, tablinks, labels, inputs, content, inputlink;
	
	// Display all tabs
	tablinks = document.getElementsByClassName("tablinks");
	
	tab = document.getElementById("end_day");
	tab.style.display = "inline";
	
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].style.display = "block";
	}
	
	// Display input content
	labels = document.getElementsByClassName("labels");
	inputs = document.getElementsByClassName("inputs");
	inputlinks = document.getElementsByClassName("inputlinks");
	
	for(i = 0; i < labels.length; i++) {
		labels[i].style.display = "inline";
	}
	
	for(i = 0; i < inputs.length; i++) {
		inputs[i].style.display = "inline";
	}
	
	for(i = 0; i < inputlinks.length; i++) {
		inputlinks[i].style.display = "inline";
	}
	
	// Hide tabs from CEO
	tab = document.getElementById("patAccButton");
	tab.style.display = "none";
	tab = document.getElementById("schedButton");
	tab.style.display = "none";
	tab = document.getElementById("treatRecButton");
	tab.style.display = "none";
	tab = document.getElementById("payRecButton");
	tab.style.display = "none";
	tab = document.getElementById("patPayButton");
	tab.style.display = "none";
}

function patientPermissions() {
	var i, tablinks, labels, inputs, content, inputlink;
	
	// Display all tabs
	tablinks = document.getElementsByClassName("tablinks");
	
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].style.display = "block";
	}
	
	// Display input content
	labels = document.getElementsByClassName("labels");
	inputs = document.getElementsByClassName("inputs");
	inputlinks = document.getElementsByClassName("inputlinks");
	
	for(i = 0; i < labels.length; i++) {
		labels[i].style.display = "inline";
	}
	
	for(i = 0; i < inputs.length; i++) {
		inputs[i].style.display = "inline";
	}
	
	for(i = 0; i < inputlinks.length; i++) {
		inputlinks[i].style.display = "inline";
	}
	
	// Hide tabs from patient
	tab = document.getElementById("patAccButton");
	tab.style.display = "none";
	tab = document.getElementById("schedButton");
	tab.style.display = "none";
	tab = document.getElementById("treatRecButton");
	tab.style.display = "none";
	tab = document.getElementById("payRecButton");
	tab.style.display = "none";
	tab = document.getElementById("repButton");
	tab.style.display = "none";
	
	//Hide end-of-day button
	tab = document.getElementById("end_day");
	tab.style.display = "none";
}

function doctorPermissions() {
	var i, tablinks, labels, inputs, content, inputlink;
	
	// Display all tabs
	tablinks = document.getElementsByClassName("tablinks");
	
	tab = document.getElementById("end_day");
	tab.style.display = "inline";
	
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].style.display = "block";
	}
	
	// Display input content
	labels = document.getElementsByClassName("labels");
	inputs = document.getElementsByClassName("inputs");
	inputlinks = document.getElementsByClassName("inputlinks");
	
	for(i = 0; i < labels.length; i++) {
		labels[i].style.display = "inline";
	}
	
	for(i = 0; i < inputs.length; i++) {
		inputs[i].style.display = "inline";
	}
	
	for(i = 0; i < inputlinks.length; i++) {
		inputlinks[i].style.display = "inline";
	}
	
	// Hide tabs from doctor
	tab = document.getElementById("patAccButton");
	tab.style.display = "none";
	tab = document.getElementById("schedButton");
	tab.style.display = "none";
	tab = document.getElementById("patPayButton");
	tab.style.display = "none";
	tab = document.getElementById("payRecButton");
	tab.style.display = "none";
	tab = document.getElementById("repButton");
	tab.style.display = "none";
	
	// Hide input content from doctor
	tab = document.getElementById("weightTreatRec");
	tab.style.display = "none";
	tab = document.getElementById("weight");
	tab.style.display = "none";
	tab = document.getElementById("heightTreatRec");
	tab.style.display = "none";
	tab = document.getElementById("height");
	tab.style.display = "none";
	tab = document.getElementById("BPTreatRec");
	tab.style.display = "none";
	tab = document.getElementById("blood_pressure");
	tab.style.display = "none";
	tab = document.getElementById("reasonTreatRec");
	tab.style.display = "none";
	tab = document.getElementById("reason_for_visit");
	tab.style.display = "none";
	
	// Hide create input tab
	inputlink = document.getElementById("createTreatRecTab");
	inputlink.style.display = "none";
}

function nursePermissions() {
	var i, tablinks, labels, inputs, content, inputlink;
	
	// Display all tabs
	tablinks = document.getElementsByClassName("tablinks");
	
	tab = document.getElementById("end_day");
	tab.style.display = "inline";
	
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].style.display = "block";
	}
	
	// Display input content
	labels = document.getElementsByClassName("labels");
	inputs = document.getElementsByClassName("inputs");
	inputlinks = document.getElementsByClassName("inputlinks");
	
	for(i = 0; i < labels.length; i++) {
		labels[i].style.display = "inline";
	}
	
	for(i = 0; i < inputs.length; i++) {
		inputs[i].style.display = "inline";
	}
	
	for(i = 0; i < inputlinks.length; i++) {
		inputlinks[i].style.display = "inline";
	}
	
	// Hide tabs from nurse
	tab = document.getElementById("patAccButton");
	tab.style.display = "none";
	tab = document.getElementById("schedButton");
	tab.style.display = "none";
	tab = document.getElementById("patPayButton");
	tab.style.display = "none";
	tab = document.getElementById("payRecButton");
	tab.style.display = "none";
	tab = document.getElementById("repButton");
	tab.style.display = "none";
	
	// Hide input content from nurse
	content = document.getElementById("treatmentTreatRec");
	content.style.display = "none";
	content = document.getElementById("treatment");
	content.style.display = "none";
	content = document.getElementById("prescriptionTreatRec");
	content.style.display = "none";
	content = document.getElementById("prescription");
	content.style.display = "none";
	
	// Hide create input tab
	inputlink = document.getElementById("createTreatRecTab");
	inputlink.style.display = "none";
}

function staffPermissions() {
	var i, tablinks, labels, inputs, content, inputlink;
	
	// Display all tabs
	tablinks = document.getElementsByClassName("tablinks");
	
	tab = document.getElementById("end_day");
	tab.style.display = "inline";
	
	for(i = 0; i < tablinks.length; i++) {
		tablinks[i].style.display = "block";
	}
	
	// Display input content
	labels = document.getElementsByClassName("labels");
	inputs = document.getElementsByClassName("inputs");
	inputlinks = document.getElementsByClassName("inputlinks");
	
	for(i = 0; i < labels.length; i++) {
		labels[i].style.display = "inline";
	}
	
	for(i = 0; i < inputs.length; i++) {
		inputs[i].style.display = "inline";
	}
	
	for(i = 0; i < inputlinks.length; i++) {
		inputlinks[i].style.display = "inline";
	}
	
	// Hide tabs from staff
	tab = document.getElementById("patPayButton");
	tab.style.display = "none";
	tab = document.getElementById("repButton");
	tab.style.display = "none";
	
	// Hide input content from staff
	content = document.getElementById("weightTreatRec");
	content.style.display = "none";
	content = document.getElementById("weight");
	content.style.display = "none";
	content = document.getElementById("heightTreatRec");
	content.style.display = "none";
	content = document.getElementById("height");
	content.style.display = "none";
	content = document.getElementById("BPTreatRec");
	content.style.display = "none";
	content = document.getElementById("blood_pressure");
	content.style.display = "none";
	content = document.getElementById("reasonTreatRec");
	content.style.display = "none";
	content = document.getElementById("reason_for_visit");
	content.style.display = "none";
	content = document.getElementById("treatmentTreatRec");
	content.style.display = "none";
	content = document.getElementById("treatment");
	content.style.display = "none";
	content = document.getElementById("prescriptionTreatRec");
	content.style.display = "none";
	content = document.getElementById("prescription");
	content.style.display = "none";
	
	// Hide update input tab
	inputlink = document.getElementById("updateTreatRecTab");
	inputlink.style.display = "none";
}

</script>