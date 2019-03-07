package tools.relation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONException;
import org.json.JSONObject;

import tools.user.UserBDTools;

public class RelationBDTools {

	public static boolean insertFriend (int friendID, int userID, Connection conn)throws SQLException {
		//Insertion de la relation dans la BD
		//notifiy(friendID, userID);
		
		String query = "INSERT INTO follow VALUES("+userID+","+friendID+",NOW())";
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
		
		String query = "DELETE FROM follow WHERE user_id2='"+relationID+"' AND user_id1='"+loginID+"'";
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
		
		String query = "SELECT user_id2 FROM follow WHERE user_id1='"+userID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) { 
			String name =  UserBDTools.getLogin(rs.getInt("user_id2"), conn);
			retour.put(rs.getString("user_id2"), name);
		}
		rs.close();
		st.close();
		return retour;
	}
	
public static JSONObject getAbonnes(int userID, Connection conn)throws SQLException, JSONException {
		
		JSONObject retour = new JSONObject();
		
		String query = "SELECT user_id1 FROM follow WHERE user_id2='"+userID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		while(rs.next()) { 
			String name =  UserBDTools.getLogin(rs.getInt("user_id1"), conn);
			retour.put(rs.getString("user_id1"), name);
		}
		rs.close();
		st.close();
		return retour;
	}


	public static boolean checkFriend(int loginID, int relationID, Connection conn) throws SQLException{
		
		
		String query = "SELECT * FROM follow WHERE user_id1='"+loginID+"' AND user_id2='"+relationID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean friended = false;
		while(rs.next()) {
			friended = true;
		}
		rs.close();
		st.close();
		return friended;
		
	}

	public static boolean keyPseudoEquals(String userKey, String pseudo, Connection conn) throws SQLException {
		
		if(UserBDTools.getUserId(pseudo, conn) == UserBDTools.getUserIdfromKey(userKey, conn))
				return true;
		return false;
	}

	
	
	
}
