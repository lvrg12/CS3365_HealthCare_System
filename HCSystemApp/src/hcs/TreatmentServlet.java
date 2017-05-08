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
		String patient_name, date, weight, height, blood_pressure, reason_for_visit, treatment, prescription;
		patient_name = request.getParameter("patient_name_treatRecInput");
		date = request.getParameter("date_treatRecInput");
		weight = request.getParameter("weight");
		height = request.getParameter("height");
		blood_pressure = request.getParameter("blood_pressure");
		reason_for_visit = request.getParameter("reason_for_visit");
		treatment = request.getParameter("treatment");
		prescription = request.getParameter("prescription");
		
		String permission = treatmentTable.getPermission();
		
		if(patient_name!=null)
		{
			if(permission.equals("staff"))//permissions for staff
			{
				treatmentTable.addTreatmentRecord(patient_name, date);
			}
			else if(permission.equals("nurse"))//permissions for nurse
			{
				treatmentTable.updateTreatmentRecord(patient_name, date, weight, height, blood_pressure, reason_for_visit);
			}
			else if(permission.equals("dr"))//permissions for doctor
			{
				treatmentTable.updateTreatmentRecord(patient_name, date, treatment, prescription);
			}
		}
		
		treatmentTable.close();
		response.sendRedirect("web/HCS.jsp"); // redirecting to client file
	}

}
