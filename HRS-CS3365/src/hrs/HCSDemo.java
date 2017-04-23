package hrs;
import java.util.Random;
//Sees this
//Edit #2
public class HCSDemo
{
	public static void main(String[] args)
	{	
		//ScheduleRecord test
		ScheduleRecord scheduleRecord = new ScheduleRecord();
		//System.out.println(scheduleRecord.appointmentExists("Gelfond", "12/23/2017", "4:30"));
		scheduleRecord.createAppointment("Gelfond", "12/23/2017", "4:30","Juan");
		//scheduleRecord.changeAppointment("Gelfond", "12/23/2017", "4:30","Juan","Watson","11/06/2017","2:30");
		//System.out.println(scheduleRecord.appointmentExists("Gelfond", "12/23/2017", "4:30"));
		scheduleRecord.printAll();
		
		//PatientAccount test
		PatientAccount accounts = new PatientAccount();
		accounts.printAll();
		System.out.println(accounts.accountExists(123456789));
		//accounts.addAccount("Eva Williams", "151 University Amarillo, TX", "999-444-7777", "hereiseva@ttu.edu", 147258369);
		accounts.setName("Pedro Infante", 123456789);
		accounts.printAll();
		accounts.treatmentRecord.printAll();
		accounts.treatmentRecord.setWeight(70.0, 123456789, "04/16/2017");
		accounts.treatmentRecord.printAll();
		accounts.paymentRecord.printAll();
		accounts.close();
		
		System.out.print(generateReferenceNum());
		
	}
	
	public static int generateReferenceNum(){
		int rand = (new Random()).nextInt(90000000) + 10000000;
		return rand;
		
	
	}

}


