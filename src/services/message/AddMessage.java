package services.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;

public class AddMessage {
	public static JSONObject addMessage(String message) throws JSONException {
		JSONObject retour = new JSONObject();
		if(message == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		ErrorJSON.serviceAccepted();
		return retour;
	}
}
