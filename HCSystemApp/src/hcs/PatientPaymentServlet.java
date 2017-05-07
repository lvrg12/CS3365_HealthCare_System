package hcs;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PatientPaymentServlet")
public class PatientPaymentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private PaymentHandler paymentTable;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//setting schedule table handler
		paymentTable = new PaymentHandler();
		
		//retriving data from jsp file
		String patient_name, date, card, receipt_given;
		patient_name = request.getParameter("patient_name");
		date = request.getParameter("date");
		card = request.getParameter("card");
		receipt_given = request.getParameter("receipt_given");
		
		//making payment
		if(patient_name!=null)
			paymentTable.makeInvoicePayment(patient_name, date, card, receipt_given);

		paymentTable.close();
		response.sendRedirect("web/HCS.jsp"); // redirecting to client file
	}

}
