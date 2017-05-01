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
		//this servlet still needs to respond to change and delete appointment
		
		scheduleTable = new ScheduleHandler();
		
		//retriving data from jsp file
		String date = request.getParameter("date");
		String time = request.getParameter("time");
		String doctor = request.getParameter("doctor");
		String patient_name = request.getParameter("patient_name");
		
		if(!scheduleTable.appointmentExists(doctor, date, time))
		{
			scheduleTable.createAppointment(doctor, date, time, patient_name);
		}
		
		scheduleTable.close();
		response.sendRedirect("web/HCS.jsp"); // redirecting to client file
	}
}