package servlet.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;


public class CreateUser extends HttpServlet {
	
	protected void doGet(HttpServletRequest req, HttpServletResponse rep) throws IOException, ServletException {
		
		String login = req.getParameter("login");
		String mdp = req.getParameter("password");
		String mail = req.getParameter("mail");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		
		try {
			JSONObject retour = services.User.createUser(login, mdp, mail, nom, prenom);
			
			rep.setContentType("text/json");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
