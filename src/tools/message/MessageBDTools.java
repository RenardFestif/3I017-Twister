package tools.message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import tools.user.UserBDTools;

public class MessageBDTools {

	public static boolean insertMessage(String message, String userKey, Connection conn) throws SQLException{

		int id = UserBDTools.getUserId(userKey, conn);

		String query = "INSERT INTO USERS VALUES(null, "+id+", NOW(),"+message+")";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}

		return false;
	}

	public int getIDMessage() throws SQLException {
		return 0;
	}
	public static boolean removeMessage(int idMessage, Connection conn) throws SQLException {

		String query = "DELETE FROM messages WHERE idmessage="+idMessage+"";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}

		return false;
	}

	public static JSONObject getMessages(int userID,Connection conn) throws SQLException, JSONException {
		JSONObject retour = new JSONObject();

		String query = "SELECT * FROM messages WHERE user_id="+userID+"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			// Pas du tout sure de celle la, j'ai mis un peu au pif
			retour.put("Message", rs.getString(query));
		}
		rs.close();
		st.close();
		return retour;
	}

	public static JSONObject getMessage(int idMessage, Connection conn) throws SQLException, JSONException{
		JSONObject retour = new JSONObject();

		String query = "SELECT * FROM messages WHERE id_message="+idMessage+"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			// Pas du tout sure de celle la, j'ai mis un peu au pif
			retour.put("Message", rs.getString("message"));
		}
		rs.close();
		st.close();
		return retour;
	}

}
