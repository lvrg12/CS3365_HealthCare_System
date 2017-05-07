package hcs;
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private PaymentHandler paymentTable;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//setting schedule table handler
		paymentTable = new PaymentHandler();
		
		//retriving data from jsp file
		String patient_name, date, payment_type, payment_method, card, receipt_given;
		double amount;
		
		//creating payment record
		if(request.getParameter("create_patient_name")!=null)
		{
			patient_name = request.getParameter("create_patient_name");
			date = request.getParameter("create_date");
			payment_type = request.getParameter("create_payment_type");
			amount = Double.parseDouble(request.getParameter("create_amount"));
			
			paymentTable.createPaymentRecord(patient_name,date,payment_type,amount);
		}
		//making copay payment
		else if (request.getParameter("update_patient_name")!=null)
		{
			patient_name = request.getParameter("update_patient_name");
			date = request.getParameter("update_date");
			payment_type = request.getParameter("update_payment_type");
			payment_method = request.getParameter("update_payment_method");
			card = request.getParameter("update_card");
			receipt_given = request.getParameter("update_receipt_given");
			
			paymentTable.makeCopayPayment(patient_name, date, card, receipt_given);
		}
		else
		{
			//Nothing done
		}

		paymentTable.close();
		response.sendRedirect("web/HCS.jsp"); // redirecting to client file
	}
}
