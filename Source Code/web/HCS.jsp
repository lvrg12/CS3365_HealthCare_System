<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="HCSstyle.css" />
</head>

<body onload="permissions()">

<header>
<form action="HCSLogIn.jsp" method="get">
	<input type="submit" style="float: right; width: 20%" value="Log Out">
</form>
	<h1>HealthCare Reservation System</h1>
</header>

<!-- ***Tabs*** -->

<div class="tab" id="tabs">
  <button class="tablinks" id="patAccButton" onclick="openTab(event, 'PatientAcc')">Patient Accounts</button>
  <button class="tablinks" id="schedButton" onclick="openTab(event, 'Sched')">Schedule</button>
  <button class="tablinks" id="treatRecButton" onclick="openTab(event, 'TreatRec')">Treatment Records</button>
  <button class="tablinks" id="payRecButton" onclick="openTab(event, 'PayRec')">Payment Records</button>
  <button class="tablinks" id="repButton" onclick="openTab(event, 'Reports')">Reports</button>
  <button class="tablinks" id="patPayButton" onclick="openTab(event, 'PatientPay')">Patient Payments</button>
</div>


<!-- ***Patient Account Tab*** -->

<div id="PatientAcc" class="tabcontent">
<center>
  <table id="PatientAccTable">
    <tr id="PatientAccHead">
      <th style="width: 10%">SSN</th>
      <th style="width: 20%">Patient Name</th>
      <th style="width: 20%">Address</th>
      <th style="width: 10%">Phone Number</th>
      <th style="width: 20%">Email</th>
      <th style="width: 10%">Insurance Name</th>
    </tr>
  </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'CreatePatientAcc')">Create New Patient Account</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'CreatePatientAcc')">Update Patient Account</button>
  </div>
  
  <div id="CreatePatientAcc" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/PatientAccountServlet">
      SSN:
      <input type="text" name="SSN" placeholder="### - ## - ####" required>
      Patient Name:
      <input type="text" name="patient_name" placeholder="John Doe" required>
      Address:
      <input type="text" name="address" placeholder="123 Happy Ln" required>
      <br>
      Phone Number:
      <input type="tel" name="phone_number" placeholder="(###)  ### - ####" required>
      Email:
      <input type="email" name="email" placeholder="john.doe@hrsmail.com" required>
      Insurance Name:
      <input type="insurance" name="insurance" placeholder="HCS HealthCare" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>


<!-- ***Schedule Tab*** -->

<div id="Sched" class="tabcontent">
<center>
   <table id="SchedTable">
    <tr id="SchedHead">
      <th style="width: 5%">Date</th>
      <th style="width: 5%">Time</th>
      <th style="width: 20%">Doctor</th>
      <th style="width: 20%">Patient Name</th>
    </tr>
   </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'SchedCreate')">Create Appointment</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'SchedChange')">Change Appointment</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'SchedCancel')">Cancel Appointment</button>
  </div>
  
  <div id="SchedCreate" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/ScheduleServlet">
      Date:
      <input type="date" name="create_date" placeholder="mm/dd/yyyy" required>
      Time:
      <select name="create_time">
		  <option value="09:00">09:00</option>
		  <option value="09:30">09:30</option>
		  <option value="10:00">10:00</option>
		  <option value="10:30">10:30</option>
		  <option value="11:00">11:00</option>
		  <option value="11:30">11:30</option>
		  <option value="12:00">12:00</option>
		  <option value="12:30">12:30</option>
		  <option value="13:00">13:00</option>
		  <option value="13:30">13:30</option>
		  <option value="14:00">14:00</option>
		  <option value="14:30">14:30</option>
		  <option value="15:00">15:00</option>
		  <option value="15:30">15:30</option>
		  <option value="16:00">16:00</option>
		  <option value="16:30">16:30</option>
	  </select>
      Doctor:
      <select name="create_doctor">
		  <option value="Alice Allen">Alice Allen</option>
		  <option value="Bob Brown">Bob Brown</option>
		  <option value="Chris Christie">Chris Christie</option>
		  <option value="Julie Jones">Julie Jones</option>
		  <option value="Tracy Thomas">Tracy Thomas</option>
	  </select>
      Patient Name:
      <input type="text" placeholder="Jane Doe" name="patient_name" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="SchedChange" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/ScheduleServlet">
      Old Date:
      <input type="date" name="old_date" placeholder="mm/dd/yyyy" required>
      New Date:
      <input type="date" name="new_date" placeholder="mm/dd/yyyy" required>
      <br>
      Old Time:
      <select name="old_time">
		  <option value="09:00">09:00</option>
		  <option value="09:30">09:30</option>
		  <option value="10:00">10:00</option>
		  <option value="10:30">10:30</option>
		  <option value="11:00">11:00</option>
		  <option value="11:30">11:30</option>
		  <option value="12:00">12:00</option>
		  <option value="12:30">12:30</option>
		  <option value="13:00">13:00</option>
		  <option value="13:30">13:30</option>
		  <option value="14:00">14:00</option>
		  <option value="14:30">14:30</option>
		  <option value="15:00">15:00</option>
		  <option value="15:30">15:30</option>
		  <option value="16:00">16:00</option>
		  <option value="16:30">16:30</option>
	  </select>
      New Time:
      <select name="new_time">
		  <option value="09:00">09:00</option>
		  <option value="09:30">09:30</option>
		  <option value="10:00">10:00</option>
		  <option value="10:30">10:30</option>
		  <option value="11:00">11:00</option>
		  <option value="11:30">11:30</option>
		  <option value="12:00">12:00</option>
		  <option value="12:30">12:30</option>
		  <option value="13:00">13:00</option>
		  <option value="13:30">13:30</option>
		  <option value="14:00">14:00</option>
		  <option value="14:30">14:30</option>
		  <option value="15:00">15:00</option>
		  <option value="15:30">15:30</option>
		  <option value="16:00">16:00</option>
		  <option value="16:30">16:30</option>
	  </select>
      <br>
      Old Doctor:
      <select name="old_doctor">
		  <option value="Alice Allen">Alice Allen</option>
		  <option value="Bob Brown">Bob Brown</option>
		  <option value="Chris Christie">Chris Christie</option>
		  <option value="Julie Jones">Julie Jones</option>
		  <option value="Tracy Thomas">Tracy Thomas</option>
	  </select>
      New Doctor:
      <select name="new_doctor">
		  <option value="Alice Allen">Alice Allen</option>
		  <option value="Bob Brown">Bob Brown</option>
		  <option value="Chris Christie">Chris Christie</option>
		  <option value="Julie Jones">Julie Jones</option>
		  <option value="Tracy Thomas">Tracy Thomas</option>
	  </select>
      <br>
      Patient Name:
      <input type="text" name="patient_name" placeholder="Jane Doe" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="SchedCancel" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/ScheduleServlet">
      Date:
      <input type="date" name="cancel_date" placeholder="mm/dd/yyyy" required>
      Time:
      <select name="cancel_time">
		  <option value="09:00">09:00</option>
		  <option value="09:30">09:30</option>
		  <option value="10:00">10:00</option>
		  <option value="10:30">10:30</option>
		  <option value="11:00">11:00</option>
		  <option value="11:30">11:30</option>
		  <option value="12:00">12:00</option>
		  <option value="12:30">12:30</option>
		  <option value="13:00">13:00</option>
		  <option value="13:30">13:30</option>
		  <option value="14:00">14:00</option>
		  <option value="14:30">14:30</option>
		  <option value="15:00">15:00</option>
		  <option value="15:30">15:30</option>
		  <option value="16:00">16:00</option>
		  <option value="16:30">16:30</option>
	  </select>
      Doctor:
      <select name="cancel_doctor">
		  <option value="Alice Allen">Alice Allen</option>
		  <option value="Bob Brown">Bob Brown</option>
		  <option value="Chris Christie">Chris Christie</option>
		  <option value="Julie Jones">Julie Jones</option>
		  <option value="Tracy Thomas">Tracy Thomas</option>
	  </select>
      Patient Name:
      <input type="text" name="patient_name" placeholder="Jane Doe" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>


<!-- ***Treatment Records Tab*** -->

<div id="TreatRec" class="tabcontent">
<center>
  <table id="TreatRecTable">
    <tr id="TreatRecHead">
      <th style="width: 10%">Patient Name</th>
      <th style="width: 5%">Date</th>
      <th style="width: 5%">Weight</th>
      <th style="width: 5%">Height</th>
      <th style="width: 5%">Blood Pressure</th>
      <th style="width: 20%">Reason for Visit</th>
      <th style="width: 10%">Treatment</th>
      <th style="width: 10%">Prescription</th>
    </tr>
  </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" id="createTreatRecTab" style="width:30%" onclick="openInputTab(event, 'TreatRecInput')">Create Treatment Record</button>
    <button class="inputlinks" id="updateTreatRecTab" style="width: 30%" onclick="openInputTab(event, 'TreatRecInput')">Update Treatment Record</button>
  </div>
  
  <div id="TreatRecInput" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/TreatmentServlet">
      <label class="labels" id="patNameTreatRec">Patient Name:</label>
      	<input class="inputs" type="text" id="patient_name_treatRecInput" name="patient_name_treatRecInput" placeholder="Jane Doe" required>
      <label class="labels" id="dateTreatRec">Date:</label>
      	<input class="inputs" type="date" id="date_treatRecInput" name="date_treatRecInput" placeholder="mm/dd/yyyy" required>
      <label class="labels" id="weightTreatRec">Weight:</label>
      	<input class="inputs" type="text" id="weight" name="weight" placeholder="125 lbs">
      <label class="labels" id="heightTreatRec">Height:</label>
      	<input class="inputs" type="text" id="height" name="height" placeholder="#'##''">
      <label class="labels" id="BPTreatRec">Blood Pressure:</label>
      	<input class="inputs" type="text" id="blood_pressure" name="blood_pressure" placeholder="###/##">
      <br>
      <label class="labels" id="reasonTreatRec">Reason for Visit:</label>
      	<input class="inputs" type="text" id="reason_for_visit" name="reason_for_visit" placeholder="Ear infection">
      <label class="labels" id="treatmentTreatRec">Treatment:</label>
        <input class="inputs" type="text" id="treatment" name="treatment" placeholder="None">
      <label class="labels" id="prescriptionTreatRec">Prescription:</label>
      	<input class="inputs" type="text" id="prescription" name="prescription" placeholder="Amoxicillin">
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>


<!-- ***Payment Records Tab*** -->

<div id="PayRec" class="tabcontent">
<center>
   <table id="PayRecTable">
    <tr id="PayRecHead">
      <th style="width: 10%">Patient Name</th>
      <th style="width: 5%">Date</th>
      <th style="width: 10%">Payment Type</th>
      <th style="width: 10%">Payment Status</th>
      <th style="width: 10%">Payment Amount</th>
      <th style="width: 5%">Reference #</th>
      <th style="width: 10%">Card/Check #</th>
    </tr>
   </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'PayRecCreate')">Create New Patient Payment Record</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'PayRecUpdate')">Make Copay Payment</button>
  </div>
  
  <div id="PayRecCreate" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/PaymentServlet">
      Patient Name:
      <input name="create_patient_name" type="text" placeholder="Jane Doe" required>
      Date:
      <input name="create_date" type="date" placeholder="mm/dd/yyyy" required>
      Payment Type:
      <select name="create_payment_type">
		  <option value="Invoice">Invoice</option>
		  <option value="Copay">Copay</option>
	  </select>
      Payment Amount:
      <input name="create_amount" type="number" placeholder="$100.00" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="PayRecUpdate" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/PaymentServlet">
      Patient Name:
      <input name="update_patient_name" type="text" placeholder="Jane Doe" required>
      Date:
      <input name="update_date" type="date" placeholder="mm/dd/yyyy" required>
      <br>
      Payment Method:
      <select name="update_method">
		  <option value="Debit">Debit</option>
		  <option value="Credit">Credit</option>
		  <option value="Cash">Cash</option>
		  <option value="Check">Check</option>
	  </select>
      Card/Check Number (if needed):
      <input name="update_card" type="text" placeholder="#### - #### - ####">
      Receipt Desired:
      <input name="update_receipt_given" type="checkbox">
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>


<!-- ***Reports Tab*** -->

<div id="Reports" class="tabcontent">
<center>
   <table id="ReportsTable">
    <tr id="ReportsHead">
      <th style="width: 5%">Date</th>
      <th style="width: 10%">Doctor</th>
      <th style="width: 20%">Number of Patients Served</th>
      <th style="width: 10%">HRS Income</th>
    </tr>
   </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'ReportsDaily')">Daily Reports</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'ReportsMonthly')">Monthly Reports</button>
  </div>
  
  <div id="ReportsDaily" class="inputcontent">
  	<form>
      Date to be Viewed:
      <input name="date" type="date" placeholder="mm/dd/yyyy" required>
      <br><br>
       <input type="submit" value="Submit">
  	</form>
  </div>
  
  <div id="ReportsMonthly" class="inputcontent">
    <form>
      Month to be Viewed:
      <input name="month" type="month" placeholder="month yyyy" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>


<!-- ***Patient Payments Tab*** -->

<div id="PatientPay" class="tabcontent">
<center>
   <table id="PatientPayTable">
    <tr id="PatientPayHead">
      <th style="width: 10%">Patient Name</th>
      <th style="width: 5%">Date</th>
      <th style="width: 10%">Payment Status</th>
      <th style="width: 10%">Payment Amount</th>
      <th style="width: 10%">Reference #</th>
      <th style="width: 20%">Card #</th>
      <th style="width: 5%">Receipt Given</th>
    </tr>
   </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'PatientPayMakePay')">Make Invoice Payment</button>
  </div>
  
  <div id="PatientPayMakePay" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/PatientPaymentServlet">
      Patient Name:
      <input name="patient_name" type="text" placeholder="Jane Doe" required>
      Date:
      <input name="date" type="date" placeholder="mm/dd/yyyy" required>
      Card Number:
      <input name="card" type="text" placeholder="#### - #### - #### - ####" required>
      Receipt Desired:
      <input name="receipt_given" type="checkbox">
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>

<footer>
	<form action="http://localhost:8080/HCSystemApp/EndOfDayServlet">
		<input value="End of Day" type="submit" id="end_day" name="end_day" style="float: right">
	</form>
</footer>

<jsp:include page="HCSscript.jsp" />
     
</body>
</html>