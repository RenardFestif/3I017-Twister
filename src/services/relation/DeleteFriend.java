package services.relation;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.BDTools;

public class DeleteFriend {
	
	public static JSONObject removeFriend(String login) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		if(login==null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			if(!BDTools.checkUserExist(login))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		
		return retour;
	}

}
