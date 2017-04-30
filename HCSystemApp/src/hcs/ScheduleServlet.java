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
		String message = "Hello World";
        request.setAttribute("message", message); // This will be available as ${message}
        request.getRequestDispatcher("html/inputFile.html").forward(request, response);
        
		//response.sendRedirect("html/inputFile.html"); // use to redirect to another file
		//request.getParameter("cell");
		//request.setAttribute("value","new sentence");
		//request.setAttribute("innerHTML", "new sentence");
		//request.getRequestDispatcher("html/inputFile.html").forward(request, response);
		
		/*
		scheduleTable = new ScheduleHandler();
		String selection = request.getParameter("action");
		
		//response
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Schedule Responde";
		String docType = "<!DOCTYPE html>\n";
		out.println(docType +
					"<html>\n" +
					"<head><title>" + title + "</title></head>\n" +
					"<body>\n" +
					"<h1>Schedule</h1>\n");
		
		String do0,da0,ti0,na0;
		String do1,da1,ti1,do1a,da1a,ti1a,na1;
		String do2,da2,ti2,na2;
		
		if(selection.equals("create"))
		{
			do0 = request.getParameter("do0");
			da0 = request.getParameter("da0");
			ti0 = request.getParameter("ti0");
			na0 = request.getParameter("na0");
			
			if(!scheduleTable.appointmentExists(do0, da0, ti0))
			{
				scheduleTable.createAppointment(do0, da0, ti0, na0);
				out.println("You created the following appointment:<br>");
				out.println("Doctor: "+ do0 + "<br>");
				out.println("Date: "+ da0 + "<br>");
				out.println("Time: "+ ti0 + "<br>");
				out.println("Patient Name: "+ na0 + "<br><br>");
			}
			else
			{
				out.println("An appointment already exists!<br><br>");
			}
		}
		else if(selection.equals("change"))
		{
			do1 = request.getParameter("do1");
			da1 = request.getParameter("da1");
			ti1 = request.getParameter("ti1");
			do1a = request.getParameter("do1a");
			da1a = request.getParameter("da1a");
			ti1a = request.getParameter("ti1a");
			na1 = request.getParameter("na1");
			
			if(!scheduleTable.appointmentExists(do1, da1, ti1))
			{
				if(scheduleTable.appointmentExists(do1a, da1a, ti1a))
				{
					scheduleTable.changeAppointment(do1a, da1a, ti1a, na1, do1, da1, ti1);
					out.println("You edited the following appointment:<br>");
					out.println("<i>Old Appointment</i><br>");
					out.println("old Doctor: "+ do1a + "<br>");
					out.println("old Date: "+ da1a + "<br>");
					out.println("old Time: "+ ti1a + "<br>");
					out.println("Patient Name: "+ na1 + "<br><br>");
					out.println("<i>New Appointment</i><br>");
					out.println("new Doctor: "+ do1 + "<br>");
					out.println("new Date: "+ da1 + "<br>");
					out.println("new Time: "+ ti1 + "<br>");
					out.println("Patient Name: "+ na1 + "<br><br>");
				}
				else
				{
					out.println("Old appointment does not exist!<br><br>");
				}
			}
			else
			{
				out.println("An appointment already exists!<br><br>");
			}
		}
		else
		{
			do2 = request.getParameter("do2");
			da2 = request.getParameter("da2");
			ti2 = request.getParameter("ti2");
			na2 = request.getParameter("na2");
			
			if(scheduleTable.appointmentExists(do2, da2, ti2))
			{
				scheduleTable.cancelAppointment(do2, da2, ti2);
				out.println("You cancel the following appointment:<br>");
				out.println("Doctor: "+ do2 + "<br>");
				out.println("Date: "+ da2 + "<br>");
				out.println("Time: "+ ti2 + "<br>");
				out.println("Patient Name: "+ na2 + "<br><br>");
			}
			else
			{
				out.println("Appointment does not exist!<br><br>");
			}
		}
		
		out.println("<button onclick=\"goBack()\">Go Back</button>");
		out.println("<script>");
		out.println("function goBack(){");
		out.println("window.history.back();}</script>");
		
		out.println("</body></html>");
		out.close();
		scheduleTable.close();
		*/
	}
}