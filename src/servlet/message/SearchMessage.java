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

		String pattern = req.getParameter("pattern");
		String userKey = req.getParameter("userKey");
	

		try {
			//appel service login
			JSONObject retour = services.Message.searchMessage(pattern, userKey); 

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}


