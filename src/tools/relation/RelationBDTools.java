package tools.relation;

import java.sql.SQLException;

import org.json.JSONObject;

public class RelationBDTools {

	public static boolean insertFriend (int friendID, int userID)throws SQLException {
		//Insertion de la relation dans la BD
		//notifiy(friendID, userID);
		return true;
	}
	
	/*public static void notifiy(int userID, int relationID) throws SQLException {
		//Notifie un utilisateur qu'il a ete ajouté en relation
	}*/
	
	public static boolean remooveRelation (int relationID) throws SQLException {
		return true;
	}
	
	public static JSONObject getFriends (int userID)throws SQLException {
		return new JSONObject();
	}
	
	
}
