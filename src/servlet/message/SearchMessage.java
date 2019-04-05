package servlet.message;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class SearchMessage extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

		String query = req.getParameter("query");
		String userKey = req.getParameter("userKey");
		String userId = req.getParameter("userId");
		
		int id;
		if (userId == null)
				id = 0;
		else 
			id = Integer.parseInt(userId);
		
		if (query == "undefined")
			query = null;
		if (userKey == "undefined")
			userKey = null;
	

		try {
			//appel service login
			
			JSONObject retour = services.Message.searchMessage(query, userKey, id); 

			rep.setContentType("text/json");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}


