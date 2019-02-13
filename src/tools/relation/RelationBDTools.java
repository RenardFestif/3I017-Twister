package tools.relation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

public class RelationBDTools {

	public static boolean insertFriend (int friendID, int userID, Connection conn)throws SQLException {
		//Insertion de la relation dans la BD
		//notifiy(friendID, userID);
		
		String query = "INSERT INTO FOLLOW VALUES("+userID+","+friendID+",NOW())";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}
			
		return false;
		
	}
	
	/*public static void notifiy(int userID, int relationID) throws SQLException {
		//Notifie un utilisateur qu'il a ete ajout� en relation
	}*/
	
	public static boolean remooveRelation (int loginID, int relationID, Connection conn) throws SQLException {
		
		String query = "DELETE FROM follow WHERE user_id2="+relationID+" AND user_id1="+loginID+"";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}
			
		return false;
	}
	
	public static JSONObject getFriends (int userID, Connection conn)throws SQLException, JSONException {
		
		JSONObject retour = new JSONObject();
		
		String query = "SELECT user_id2 FROM messages WHERE user_id="+userID+"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) {
			// Pas du tout sure de celle la, j'ai mis un peu au pif
			retour.put("Amis", rs.getString(query));
		}
		rs.close();
		st.close();
		return retour;
	}
	
	
}
