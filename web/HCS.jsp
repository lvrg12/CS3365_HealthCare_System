<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="HCSstyle.css" />
</head>

<body>

<header>
	<h1>HealthCare Reservation System</h1>
</header>


<!-- ***Tabs*** -->

<div class="tab">
  <button class="tablinks" onclick="openTab(event, 'PatientAcc')">Patient Accounts</button>
  <button class="tablinks" onclick="openTab(event, 'Sched')">Schedule</button>
  <button class="tablinks" onclick="openTab(event, 'TreatRec')">Treatment Records</button>
  <button class="tablinks" onclick="openTab(event, 'PayRec')">Payment Records</button>
  <button class="tablinks" onclick="openTab(event, 'Reports')">Reports</button>
  <button class="tablinks" onclick="openTab(event, 'PatientPay')">Patient Payments</button>
</div>


<!-- ***Patient Account Tab*** -->

<div id="PatientAcc" class="tabcontent">
<center>
  <table id="PatientAccTable">
    <tr id="PatientAccHead">
      <th style="width: 5%">SSN</th>
      <th style="width: 20%">Patient Name</th>
      <th style="width: 20%">Address</th>
      <th style="width: 5%">Phone Number</th>
      <th style="width: 20%">Email</th>
    </tr>
  </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'PatientAccInput')">Create New Patient Account</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'PatientAccInput')">Update Patient Account</button>
  </div>
  
  <div id="PatientAccInput" class="inputcontent">
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
      <input type="date" name="create_date" required>
      Time:
      <input type="time" name="create_time" required>
      Doctor:
      <input type="text" placeholder="John Doe" name="create_doctor" required>
      Patient Name:
      <input type="text" placeholder="Jane Doe" name="patient_name" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="SchedChange" class="inputcontent">
    <form action="http://localhost:8080/HCSystemApp/ScheduleServlet">
      Old Date:
      <input type="date" name="old_date" required>
      New Date:
      <input type="date" name="new_date"required>
      <br>
      Old Time:
      <input type="time" name="old_time" required>
      New Time:
      <input type="time" name="new_time" required>
      <br>
      Old Doctor:
      <input type="text" name="old_doctor" placeholder="John Doe" required>
      New Doctor:
      <input type="text" name="new_doctor" placeholder="John Doe" required>
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
      <input type="date" name="cancel_date" required>
      Time:
      <input type="time" name="cancel_time" required>
      Doctor:
      <input type="text" name="cancel_doctor" placeholder="John Doe" required>
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
      <th style="width: 5%">SSN</th>
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
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'TreatRecInput')">Create Treatment Record</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'TreatRecInput')">Update Treatment Record</button>
  </div>
  
  <div id="TreatRecInput" class="inputcontent">
    <form>
      SSN:
      <input type="text" placeholder="### - ## - ####" required>
      Date:
      <input type="date" required>
      Weight:
      <input type="text" placeholder="125 lbs" required>
      Height:
      <input type="text" placeholder="#'##''" required>
      Blood Pressure:
      <input type="text" placeholder="###/##" required>
      <br>
      Reason for Visit:
      <input type="text" placeholder="Ear infection" required>
      Treatment:
      <input type="text" placeholder="None" required>
      Prescription:
      <input type="text" placeholder="Amoxicillin" required>
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
      <th style="width: 5%">SSN</th>
      <th style="width: 5%">Date</th>
      <th style="width: 10%">Payment Type</th>
      <th style="width: 10%">Payment Status</th>
      <th style="width: 10%">Payment Amount</th>
      <th style="width: 5%">Reference #</th>
      <th style="width: 10%">Card #</th>
    </tr>
   </table>
</center>

<center>
  <div style="margin:20px">
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'PayRecCreate')">Create New Patient Payment Record</button>
    <button class="inputlinks" style="width: 30%" onclick="openInputTab(event, 'PayRecUpdate')">Update Patient Payment Record</button>
  </div>
  
  <div id="PayRecCreate" class="inputcontent">
    <form>
      SSN:
      <input type="text" placeholder="### - ## - ####" required>
      Date:
      <input type="date" required>
      Payment Type:
      <input type="text" placeholder="Invoice" required>
      Payment Amount:
      <input type="number" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="PayRecUpdate" class="inputcontent">
    <form>
      SSN:
      <input type="text" placeholder="### - ## - ####" required>
      Date:
      <input type="date" required>
      <br>
      Payment Method:
      <input type="text" placeholder="Debit" required>
      Card/Check Number (if needed):
      <input type="text" placeholder="#### - #### - ####">
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
      <input type="date" required>
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
  
  <div id="ReportsMonthly" class="inputcontent">
    <form>
      Month to be Viewed:
      <input type="month" required>
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
      <th style="width: 5%">SSN</th>
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
  	<button class="inputlinks" style="width:30%" onclick="openInputTab(event, 'PatientPayMakePay')">Make Payment</button>
  </div>
  
  <div id="PatientPayMakePay" class="inputcontent">
    <form>
      SSN:
      <input type="text" placeholder="### - ## - ####" required>
      Date:
      <input type="date" required>
      Card Number:
      <input type="text" placeholder="### - #### - ####" required>
      Receipt Desired:
      <input type="checkbox">
      <br><br>
      <input type="submit" value="Submit">
    </form>
  </div>
</center>
</div>

<jsp:include page="HCSscript.jsp" />
     
</body>
</html>