package hcs;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PatientAccountServlet")
public class PatientAccountServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private PatientAccountHandler patientAccountTable;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//setting schedule table handler
		patientAccountTable = new PatientAccountHandler();
		
		//retriving data from jsp file
		String SSN, patient_name, address, phone_number, email;
		SSN = request.getParameter("SSN");
		patient_name = request.getParameter("patient_name");
		address = request.getParameter("address");
		phone_number = request.getParameter("phone_number");
		email = request.getParameter("email");
		
		if(!patientAccountTable.accountExists(SSN))
		{
			patientAccountTable.addAccount(patient_name, address, phone_number, email, SSN);
		}
		else
		{
			patientAccountTable.updateAccount(patient_name, address, phone_number, email, SSN);
		}

		
		patientAccountTable.close();
		response.sendRedirect("web/HCS.jsp"); // redirecting to client file
	}

}
