package servlet.relation;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class AddFriend extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

		String pseudo = req.getParameter("pseudo");
		String user_key = req.getParameter("user_key");


		try {
			//appel service login
			JSONObject retour = services.Friend.addFriend(pseudo, user_key);

			rep.setContentType("text/json");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
			
			
		} catch (JSONException e) {
			
			/*Service refused ?*/
			
			e.printStackTrace();
		}


	}

}
