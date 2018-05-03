package survlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LogoutSurvlet")
public class LogoutSurvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.setContentType("text/html");

		ServletOutputStream out = response.getOutputStream();
		out.println("<html><head>");
		out.println("<title>Logout</title></head>");
		out.println("<body>");
		out.println("<h1>Logout successfull.</h1>");
		out.println("<h1><a href=\"http://localhost:8080/kinobotevgrad.bg/\">Return</a></h1>");
		out.println("</body></html>");

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
