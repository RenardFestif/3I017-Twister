package services.users;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.user.UserBDTools;

public class LogOutUser {

	
	public static JSONObject logout(String key) throws JSONException, SQLException{
		JSONObject retour = new JSONObject();
		
		if(key == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(!UserBDTools.checkKey(key)) {
				return ErrorJSON.serviceRefused("Erreur de deconnexion", 1000); 
			}
			ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		return retour;
	}
}
