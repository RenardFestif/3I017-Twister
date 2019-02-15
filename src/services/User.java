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
		//Verif des parametres
		if(login==null || mdp==null || mail==null || prenom==null || nom == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			Connection conn = Database.getMySQLConnection();
			//Verif si l'utilisateur est deja dans la base
			if(UserBDTools.checkUserExist(login, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur "+login+" existe deja", 1000);
			}
				
			//Verif format mdp
			if(!UserTools.checkFormatMdp(mdp)) {
				conn.close();
				return ErrorJSON.serviceRefused("Mauvais format de mot de passe", -1);
			}
			//Verif format mail
			if(!UserTools.checkFormatMail(mail)) {
				conn.close();
				return ErrorJSON.serviceRefused("Mauvais format de mail", -1);
			}
			//Insertion dans la BD
			if(!UserBDTools.insertUser(login, mdp, mail, nom, prenom, conn	)) {
				conn.close();
				return ErrorJSON.serviceRefused("Impossible d'inserer dans la BD", 1000);
			}
			
			//Great Succes !
			retour = ErrorJSON.serviceAccepted();
			conn.close();
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
		
		//Verif des parametres
		if(key == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			Connection conn = Database.getMySQLConnection();
			//Verif de la cle
			if(!UserBDTools.checkConnexion(key, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur de deconnexion", 1000); 
			}
			//Great Succes !
			retour = ErrorJSON.serviceAccepted();
			conn.close();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme", 1000);
		}
		return retour;
	}
	
	
	
	public static JSONObject login(String login, String mdp) throws JSONException {
		JSONObject retour = new JSONObject();
		
		//Verif des parametres
		if(login==null || mdp==null ) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		try {
			Connection conn = Database.getMySQLConnection();
			//verif si l'utilisateur existe dans la BD
			if(!UserBDTools.checkUserExist(login, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			}
			//Concordance du couple mot de passe et login
			if(!UserBDTools.checkUserMdp(login,mdp, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Mot de passe oublie ?", 1000);
			}
			
			//retour de l'id
			int id_user = UserBDTools.getUserId(login, conn);
			//retour de la clef de connexion
			String key = UserBDTools.insertConnexion(id_user, false, conn);
			retour = ErrorJSON.serviceAccepted();
			retour.put("userID", id_user);
			retour.put("key", key);
			conn.close();
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
