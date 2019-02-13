package tools.message;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONObject;

import tools.user.UserBDTools;

public class MessageBDTools {
	
	public static boolean insertMessage(String message, String login, Connection conn) throws SQLException{
		
		int id = UserBDTools.getUserId(login);
		
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
		
		String query = "INSERT INTO USERS VALUES(null, "+id+", NOW(),"+message+")";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}
			
		return false;
	}
	
	public static JSONObject getMessages(int userID) throws SQLException {
		return new JSONObject();
	}
	
	public static JSONObject getMessage(int idMessage) throws SQLException{
		return new JSONObject();
	}

}
