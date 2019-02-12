package services.relation;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.relation.RelationBDTools;
import tools.user.UserBDTools;

public class DeleteFriend {
	
	public static JSONObject removeFriend(String pseudo, String login) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		if(pseudo==null || login == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			if(!UserBDTools.checkUserExist(pseudo))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			if(!UserBDTools.checkConnexion(UserBDTools.getUserId(login)))
				return ErrorJSON.serviceRefused("Utilisateur non-connecté", 1000);
			
			//Manque BD
			int relationID = UserBDTools.getUserId(pseudo);
			if(!RelationBDTools.remooveRelation(relationID))
				return ErrorJSON.serviceRefused("Suppression de la relation impossible", 1000);
			
			retour = ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		
		return retour;
	}

}
