package ro.digix.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;

import ro.digix.services.UserService;

@WebServlet("/LoginServlet")
@Service
public class LoginServlet extends HttpServlet {

	@Autowired
	private UserService userService;

	private static final long serialVersionUID = -811311597008235764L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String email = req.getParameter("email");
		String password = req.getParameter("password");
		PrintWriter out = resp.getWriter();
		HttpSession session = req.getSession();
		System.out.println("email" + email + password);
		if (userService.authenticate(email, password)) {
			session.setAttribute("EMAIL", email);
			resp.sendRedirect("afterLogIn.html");
		} else {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Username or password is incorrect');");
			out.println("location='log-in.html';");
			out.println("</script>");
		}
	}

}
