package survlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controller.UserManager;

@WebServlet("/LoginSurvlet")
public class LoginSurvlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("uname");
		String password = req.getParameter("pass");
		
		if (UserManager.getInstance().signIn(username, password)) {
			HttpSession session = req.getSession();
			session.setAttribute("USER", username);

			req.getRequestDispatcher("Logedin.jsp").forward(req, resp);
		} else {
			resp.sendRedirect("InvalidLogin.html");
		}

	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		req.getRequestDispatcher("Logedin.jsp").forward(req, resp);
	}

}
