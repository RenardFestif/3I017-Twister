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


public class DeleteMessage extends HttpServlet {


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse rep) throws ServletException, IOException {

		String iDMessage = req.getParameter("idMessage");
		String user_key = req.getParameter("userKey");
		int iDMess = Integer.parseInt(iDMessage);

		try {
			//appel service login
			JSONObject retour = services.Message.removeMessage(iDMess, user_key);

			rep.setContentType("text/plain");
			PrintWriter out = rep.getWriter();
			out.println(retour.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}

