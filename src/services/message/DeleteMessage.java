package services.message;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.Database;
import tools.message.MessageBDTools;
import tools.user.UserBDTools;
import tools.user.UserTools;

public class DeleteMessage {
	
	public static JSONObject removeMessage(int iDMessage, int userID) throws JSONException{
		JSONObject retour = new JSONObject();
		
		//Faut bien faire commencer l'entr�e id de la table message a 1 sous peine de generer des erreurs
		if(iDMessage == 0 || userID == 0) 
			return ErrorJSON.serviceRefused("Champs manquants", -1);
	
		try {
			
			Connection conn = Database.getMySQLConnection();
			
			if(!UserBDTools.checkConnexion(userID, conn))
				return ErrorJSON.serviceRefused("Utilisateur non connecte", 1000);
			
			if(!MessageBDTools.removeMessage(iDMessage))
				return ErrorJSON.serviceRefused("Insertion Impossible", 1000);
			
			retour = ErrorJSON.serviceAccepted();
			
		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}
		
		return retour;
	}
	
}
