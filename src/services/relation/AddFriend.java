package services.relation;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.relation.RelationBDTools;
import tools.user.UserBDTools;

public class AddFriend {

	public static JSONObject addFriend(String pseudo, String login) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		if(pseudo==null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			if(!UserBDTools.checkUserExist(pseudo))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			//Check Connection ?
			
			//Manque BD
			int friendID = UserBDTools.getUserId(pseudo);
			int iD = UserBDTools.getUserId(login);
			 
			if(!RelationBDTools.insertFriend(friendID, iD))
				return ErrorJSON.serviceRefused("Impsossible d'ajouter la relation", 1000);
			
			retour = ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		
		return retour;
	}
}
