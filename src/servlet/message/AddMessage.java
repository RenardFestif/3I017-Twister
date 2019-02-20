package servlet.message;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

public class AddMessage extends HttpServlet{
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {
		
		String message = req.getParameter("message");
		String user_key = req.getParameter("user_key");
		

		try {
			//appel service user_key
			JSONObject retour = services.Message.addMessage(message, user_key);
			
			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
