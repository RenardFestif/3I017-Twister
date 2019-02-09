package services;

import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;
import tools.bd.*;
import tools.user.UserTools;

public class User {

	public static JSONObject createUser(String login, String mdp, String mail, String nom, String prenom) throws JSONException {
		JSONObject retour = new JSONObject();
		
		if(login==null || mdp==null || mail==null || prenom==null || nom == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(BDTools.checkUserExist(login))
				return ErrorJSON.serviceRefused("Utilisateur "+login+" existe deja", 1000);
			
			if(!UserTools.checkFormatMdp(mdp))
				return ErrorJSON.serviceRefused("Mauvais format de mot de passe", -1);
			
			if(!UserTools.checkFormatMail(mail))
				return ErrorJSON.serviceRefused("Mauvais format de mail", -1);
			
			if(!BDTools.insertUser(login, mdp, mail, nom, prenom))
				return ErrorJSON.serviceRefused("Impossible d'inserer dans la BD", 1000);
			
			ErrorJSON.serviceAccepted();
		}
		catch (JSONException e){
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme", 1000);
		}
		
		return retour;
	}
	
	
	
	
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
	
	public static JSONObject logout(String key) throws JSONException{
		JSONObject retour = new JSONObject();
		
		if(key == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			if(!BDTools.checkKey(key)) {
				return ErrorJSON.serviceRefused("Erreur de déconnexion", 1000); 
			}
			ErrorJSON.serviceAccepted();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		return retour;
	}
	
}
