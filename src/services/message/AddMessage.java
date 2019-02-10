package services.message;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.message.MessageBDTools;
import tools.message.MessageTools;
import tools.user.UserBDTools;

public class AddMessage {
	
	public static JSONObject addMessage(String message, String userKey) throws JSONException {
		JSONObject retour = new JSONObject();
		if(message == null || userKey == null) 
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		if(!UserBDTools.checkKey(userKey))
			return ErrorJSON.serviceRefused("Erreur correspondance cle utilisateur", 100);
		if(!MessageTools.checkMesslength(message))
			return ErrorJSON.serviceRefused("Message trop long (<140 caracteres)", -1);
		
		try {
			//Insertion
			if(!MessageBDTools.insertMessage(message))
				return ErrorJSON.serviceRefused("Insertion Impossible", 1000);
			
			retour = ErrorJSON.serviceAccepted();
			
		} catch (SQLException e) {
			
			return ErrorJSON.serviceRefused("Erreur SQL", 1000);
		}
		
		return retour;
	}
}
