package hcs;
import java.io.IOException;
import java.time.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EndOfDayServlet")
public class EndOfDayServlet extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	private ScheduleHandler scheduleTable;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException
	{	
		//setting schedule table handler
		scheduleTable = new ScheduleHandler();
		
		LocalDateTime currentTime = LocalDateTime.now();
		LocalDate end_day = currentTime.toLocalDate(); 
		
		scheduleTable.chargeNoShow(end_day.toString());

		scheduleTable.close();
		response.sendRedirect("web/HCS.jsp");
	}
}
