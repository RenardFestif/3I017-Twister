package services;

import org.json.JSONException;
import org.json.JSONObject;

public class ErrorJSON {

	public static JSONObject serviceRefused(String message, int id) throws JSONException {

		JSONObject json = new JSONObject();
		json.put("id erreur", id);
		json.put("message", message);
		json.put("status", "KO");
		return json;
	}
	public static JSONObject serviceAccepted() throws JSONException {

		JSONObject json = new JSONObject();
		json.put("status", "OK");
		return json;
	}
	

}
