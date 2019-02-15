package services;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.bd.Database;
import tools.user.UserBDTools;
import tools.user.UserTools;

public class User {

	
	public static JSONObject createUser(String login, String mdp, String mail, String nom, String prenom) throws JSONException {
		JSONObject retour = new JSONObject();
		
		if(login==null || mdp==null || mail==null || prenom==null || nom == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			
			Connection conn = Database.getMySQLConnection();
			
			if(UserBDTools.checkUserExist(login, conn))
				return ErrorJSON.serviceRefused("Utilisateur "+login+" existe deja", 1000);
			
			if(!UserTools.checkFormatMdp(mdp))
				return ErrorJSON.serviceRefused("Mauvais format de mot de passe", -1);
			
			if(!UserTools.checkFormatMail(mail))
				return ErrorJSON.serviceRefused("Mauvais format de mail", -1);
			
			if(!UserBDTools.insertUser(login, mdp, mail, nom, prenom, conn	))
				return ErrorJSON.serviceRefused("Impossible d'inserer dans la BD", 1000);
			
			retour = ErrorJSON.serviceAccepted();
		}
		catch (JSONException e){
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme", 1000);
		}
		
		return retour;
	}
	
	
	public static JSONObject logout(String key) throws JSONException, SQLException{
		JSONObject retour = new JSONObject();
		
		if(key == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			Connection conn = Database.getMySQLConnection();
			
			if(!UserBDTools.checkKey(key, conn)) {
				return ErrorJSON.serviceRefused("Erreur de deconnexion", 1000); 
			}
			ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		return retour;
	}
	
	
	public static JSONObject login(String login, String mdp) throws JSONException {
		JSONObject retour = new JSONObject();
		
		if(login==null || mdp==null ) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			
			Connection conn = Database.getMySQLConnection();
			
			if(!UserBDTools.checkUserExist(login, conn))
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			
			if(!UserBDTools.checkUserMdp(login,mdp, conn))
				
				return ErrorJSON.serviceRefused("Mot de passe oublie ?", 1000);
			
			int id_user = UserBDTools.getUserId(login, conn);
			String key = UserBDTools.insertConnexion(id_user, false, conn);
			retour = ErrorJSON.serviceAccepted();
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
