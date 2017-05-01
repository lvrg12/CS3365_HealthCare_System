package hcs;
public class HCSDemo
{
	public static void main(String[] args)
	{	
		//ScheduleHandler test
		ScheduleHandler schedule = new ScheduleHandler();
		System.out.println(schedule.appointmentExists("Gelfond", "12/23/2017", "4:30"));
		//schedule.createAppointment("Gelfond", "12/23/2017", "4:30","Juan");
		//schedule.changeAppointment("Gelfond", "12/23/2017", "4:30","Juan","Watson","11/06/2017","2:30");
		System.out.println(schedule.appointmentExists("Gelfond", "12/23/2017", "4:30"));
		schedule.printAll();
		
		//PatientAccountHandler test
		PatientAccountHandler accounts = new PatientAccountHandler();
		accounts.printAll();
		System.out.println(accounts.accountExists(123456789));
		//accounts.addAccount("Eva Williams", "151 University Amarillo, TX", "999-444-7777", "hereiseva@ttu.edu", 147258369);
		accounts.setName("Pedro Infante", 123456789);
		accounts.printAll();
		accounts.close();
		
		//Comment made from Eclipse project on master branch
	}
}
