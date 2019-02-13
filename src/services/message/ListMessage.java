package services.message;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.Database;
import tools.message.MessageBDTools;
import tools.user.UserBDTools;

public class ListMessage {
	
	public static JSONObject getMessages(int userID) throws JSONException{
		JSONObject retour = new JSONObject();
		
		//Faut bien faire commencer l'entr�e id de la table message a 1 sous peine de generer des erreurs
		if(userID == 0) 
			return ErrorJSON.serviceRefused("Champs manquants", -1);

		
		try {
			
			Connection conn = Database.getMySQLConnection();
			
			if(!UserBDTools.checkConnexion(userID, conn))
				return ErrorJSON.serviceRefused("Erreur correspondance cle utilisateur", 1000);
			//Tool
			retour = MessageBDTools.getMessages(userID, conn);
			if(retour == null)
				return ErrorJSON.serviceRefused("Reccuppertation Impossible", 1000);
			
			retour.put("status", "OK");
			
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		return retour;
	}

}
