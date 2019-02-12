package tools.message;

import java.sql.SQLException;

import org.json.JSONObject;

public class MessageBDTools {
	
	public static boolean insertMessage(String message) throws SQLException{
		return true;
	}
	
	public int getIDMessage() throws SQLException {
		return 0;
	}
	public static boolean removeMessage(int idMessage) throws SQLException {
		return true;
	}
	
	public static JSONObject getMessages(String userKey) throws SQLException {
		return new JSONObject();
	}
	
	public static JSONObject getMessage(String idMessage) throws SQLException{
		return new JSONObject();
	}

}
