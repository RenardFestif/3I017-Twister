package services.users;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.BDTools;

public class LogUser {

	public static JSONObject login(String login, String mdp) throws JSONException {
		JSONObject retour = new JSONObject();
		
		if(login==null || mdp==null ) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(!BDTools.checkUserExist(login))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			if(!BDTools.checkUserMdp(login,mdp))
				return ErrorJSON.serviceRefused("Mot de passe oublié ?", 1000);
			int id_user = BDTools.getUserId(login);
			String key = BDTools.insertConnexion(id_user, false);
			ErrorJSON.serviceAccepted();
			retour.put("key", key);
		}
		catch (JSONException e){
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme", 1000);
		}
		
		return retour;
	}
	
}
