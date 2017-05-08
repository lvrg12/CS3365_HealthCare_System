package hcs;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ScheduleServlet")
public class ScheduleServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private ScheduleHandler scheduleTable;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//setting schedule table handler
		scheduleTable = new ScheduleHandler();
		
		//retriving data from jsp file
		String new_date, new_time, new_doctor, old_date, old_time, old_doctor, patient_name;
		String create_date, create_time, create_doctor, cancel_date, cancel_doctor, cancel_time;
		patient_name = request.getParameter("patient_name");
		
		//creating appointment
		if(request.getParameter("create_date")!=null)
		{
			create_date = request.getParameter("create_date");
			create_time = request.getParameter("create_time");
			create_doctor = request.getParameter("create_doctor");
			
			if(!scheduleTable.appointmentExists(create_doctor, create_date, create_time))
			{
				scheduleTable.createAppointment(create_doctor, create_date, create_time, patient_name);
			}
		}
		//changing appointment
		else if(request.getParameter("new_date")!=null)
		{
			new_date = request.getParameter("new_date");
			new_time = request.getParameter("new_time");
			new_doctor = request.getParameter("new_doctor");
			old_date = request.getParameter("old_date");
			old_time = request.getParameter("old_time");
			old_doctor = request.getParameter("old_doctor");
			
			if(scheduleTable.appointmentExists(old_doctor, old_date, old_time))
			{
				scheduleTable.changeAppointment(old_doctor, old_date, old_time, patient_name, new_doctor, new_date, new_time);
			}
		}
		//canceling appointment
		else if(request.getParameter("cancel_date")!=null)
		{
			cancel_date = request.getParameter("cancel_date");
			cancel_time = request.getParameter("cancel_time");
			cancel_doctor = request.getParameter("cancel_doctor");
			
			if(scheduleTable.appointmentExists(cancel_doctor, cancel_date, cancel_time))
			{
				scheduleTable.cancelAppointment(cancel_doctor, cancel_date, cancel_time, patient_name);
			}
		}
		else
		{
			//Nothing done
		}

		scheduleTable.close();
		response.sendRedirect("web/HCS.jsp");
	}
}