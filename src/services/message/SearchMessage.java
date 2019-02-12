package services.message;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.message.MessageBDTools;
import tools.user.UserBDTools;

public class SearchMessage {
	public static JSONObject searchMessage(int idMessage, int userKey) throws JSONException {
		JSONObject retour = new JSONObject();
		if(idMessage == 0|| userKey == 0 ) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(!UserBDTools.checkConnexion(userKey)) 
				return ErrorJSON.serviceRefused("Erreur de connexion", 1000);
			
			retour = MessageBDTools.getMessage(idMessage);
			if(retour == null) 
				return ErrorJSON.serviceRefused("Impossible de reccuperer le message", 1000);
			retour.put("status","OK");
			
		} catch (SQLException e) {
			
			ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		return retour;
	}
}
