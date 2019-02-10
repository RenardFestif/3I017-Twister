package services.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.BDTools;

public class ListMessage {
	
	public static JSONObject searchMessage(String message) throws JSONException {
		JSONObject retour = new JSONObject();
		if(message == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		if(!BDTools.checkConnexion()) {
			return ErrorJSON.serviceRefused("Erreur de connexion", 100);
		}
		ErrorJSON.serviceAccepted();
		return retour;
	}

}
