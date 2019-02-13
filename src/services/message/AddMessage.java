package services.message;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import services.ErrorJSON;
import tools.bd.Database;
import tools.message.MessageBDTools;
import tools.message.MessageTools;
import tools.user.UserBDTools;

public class AddMessage {
	
	public static JSONObject addMessage(String message, String login) throws JSONException {
		JSONObject retour = new JSONObject();
		if(message == null || login == null) 
			return ErrorJSON.serviceRefused("Champs manquants", -1);

		if(!MessageTools.checkMesslength(message))
			return ErrorJSON.serviceRefused("Message trop long (<140 caracteres)", -1);
		
		try {
			
			Connection conn = Database.getMySQLConnection();
			
			if(!UserBDTools.checkConnexion(UserBDTools.getUserId(login, conn), conn))
				return ErrorJSON.serviceRefused("Erreur correspondance cle utilisateur", 100);
			//Insertion
			if(!MessageBDTools.insertMessage(message, login, conn))
				return ErrorJSON.serviceRefused("Insertion Impossible", 1000);
			
			retour = ErrorJSON.serviceAccepted();
			
		} catch (SQLException e) {
			
			return ErrorJSON.serviceRefused("Erreur SQL", 1000);
		}
		
		return retour;
	}
}
