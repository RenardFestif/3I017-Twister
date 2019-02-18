package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class LogUser extends HttpServlet {



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

		String login = req.getParameter("login");
		String mdp = req.getParameter("password");

		try {
			//appel service login
			JSONObject retour = services.User.login(login, mdp);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}


