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

public class ListFriend extends HttpServlet {
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {


		String login = req.getParameter("login");


		try {
			//appel service login
			JSONObject retour = services.Friend.getListFriends(login);

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
			
			
		} catch (JSONException e) {
			
			/*Service refused ?*/
			
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


	}

}
