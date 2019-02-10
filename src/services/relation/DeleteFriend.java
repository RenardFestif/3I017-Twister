package services.relation;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.user.UserBDTools;

public class DeleteFriend {
	
	public static JSONObject removeFriend(String pseudo, String userKey) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		if(pseudo==null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			if(!UserBDTools.checkKey(userKey))
				return ErrorJSON.serviceRefused("Echec authentification clé utilisateur", 1000);
			if(!UserBDTools.checkUserExist(pseudo))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			
			//Manque BD
			
			ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		
		return retour;
	}

}
