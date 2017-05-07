package hcs;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/TreatmentServlet")
public class TreatmentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private TreatmentHandler treatmentTable;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//setting schedule table handler
		treatmentTable = new TreatmentHandler();
		
		//retriving data from jsp file
		String SSN, date, weight, height, blood_pressure, reason_for_visit, treatment, prescription;
		SSN = request.getParameter("SSN");
		date = request.getParameter("date");
		weight = request.getParameter("weight");
		height = request.getParameter("height");
		blood_pressure = request.getParameter("blood_pressure");
		reason_for_visit = request.getParameter("reason_for_visit");
		treatment = request.getParameter("treatment");
		prescription = request.getParameter("prescription");
		
		treatmentTable.addTreatmentRecord(SSN, date, weight, height, blood_pressure, reason_for_visit, treatment, prescription);
		
		/*
		if(SSN=="thing")//permissions for staff
		{
			treatmentTable.addTreatmentRecord(SSN, date);
		}
		else if(true)//permissions for nurse
		{
			treatmentTable.updateTreatmentRecord(SSN, date, weight, height, blood_pressure, reason_for_visit);
		}
		else if(false)//permissions for doctor
		{
			treatmentTable.updateTreatmentRecord(SSN, date, treatment, prescription);
		}
		*/
		
		treatmentTable.close();
		response.sendRedirect("web/HCS.jsp"); // redirecting to client file
	}

}
