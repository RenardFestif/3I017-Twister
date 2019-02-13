package services.relation;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.Database;
import tools.relation.RelationBDTools;
import tools.user.UserBDTools;

public class ListFriend {
	
	public static JSONObject getListFriends(String login) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		if(login == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			Connection conn = Database.getMySQLConnection();
			
			if (!UserBDTools.checkConnexion(UserBDTools.getUserId(login,conn),conn))
				return ErrorJSON.serviceRefused("Utilisateur non-connecte", 1000);
			
			int userID = UserBDTools.getUserId(login,conn);
			retour = RelationBDTools.getFriends(userID,conn);
			
			if(retour == null)
				return ErrorJSON.serviceRefused("Echec de creation de la liste d'ami", 1000);
			
			retour.put("status", "OK");
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		
		return retour;
	}

}
