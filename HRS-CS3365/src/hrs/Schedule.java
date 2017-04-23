public class Schedule
{
	//3D array [doctor][day][half-hour]
	private static String[][][] schedule = new String[5][30][16];
	
	//stores names of doctors as String
	private String[] doctors = new String[5];
	
	//stores halfHours as double
	private static double[] halfHours = {9,9.3,10,10.3,11,11.3,12,12.3,1,1.3,2,2.3,3,3.3,4,4.3};
	
	//constructor
	public Schedule()
	{
		//doctor names can change
		doctors[0] = "Smith";
		doctors[1] = "Cantu";
		doctors[2] = "Rogers";
		doctors[3] = "Johnson";
		doctors[4] = "Gelfond";
		
		//sample appointments
		schedule[0][0][0] = "Josh"; //doctor: Smith, day: 1, time: 9 am
		schedule[1][5][11] = "Juan"; //doctor: Cantu, day: 6, time: 2:30 pm
		schedule[4][29][15] = "Maria"; //doctor: Gelfond, day: 30, time: 4:30 pm
	}
	
	//schedule/edit appointment
	public void setAppointment(String doctor, int day, double halfHour, String patient)
	{
		schedule[doctorIndex(doctor)][day-1][halfHourIndex(halfHour)] = patient;
	}
	
	//cancel appointment
	public void cancelAppointment(String doctor, int day, double halfHour, String patient)
	{
		schedule[doctorIndex(doctor)][day-1][halfHourIndex(halfHour)] = null;
	}
	
	//gets patientName
	public String getPatient(String doctor, int day, double halfHour)
	{
		return schedule[doctorIndex(doctor)][day-1][halfHourIndex(halfHour)];
	}
	
	//checks if appointment already exists
	public boolean appointmentExists(String doctor, int day, double halfHour)
	{
		return schedule[doctorIndex(doctor)][day-1][halfHourIndex(halfHour)] == null;
	}
	
	//helper function: returns index of given doctor String
	private int doctorIndex(String doctor)
	{	
		int x = 0;
		while(!doctors[x].equals(doctor))
		{
			x++;
		}
		return x;
	}
	
	//helper function: returns index of given halfHour double
	private int halfHourIndex(double halfHour)
	{	
		int x = 0;
		while(halfHours[x]!=halfHour)
		{
			x++;
		}
		return x;
	}
	
	/* <--------------Same functions using raw int numbers------------------->
	public void setAppointment(int doctor, int day, int halfHour, String patient)
	{
		schedule[doctor][day][halfHour] = patient;
	}
		
	//cancel appointment
	public void cancelAppointment(int doctor, int day, int halfHour, String patient)
	{
		schedule[doctor][day][halfHour] = null;
	}
		
	//gets patientName
	public String getPatient(int doctor, int day, int halfHour)
	{
		return schedule[doctor][day][halfHour];
	}
		
	//checks if appointment already exists
	public boolean appointmentExists(int doctor, int day, int halfHour)
	{
		return schedule[doctor][day][halfHour] == null;
	}
	*/
}
