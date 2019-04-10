package services;

import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.bd.Database;
import tools.relation.RelationBDTools;
import tools.user.UserBDTools;

public class Friend{
	
	
	

	public static JSONObject addFriend(String pseudo, String userKey) throws JSONException {
		JSONObject retour = new JSONObject();
		
		//Test des champs
		if(pseudo==null || userKey == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			Connection conn = Database.getMySQLConnection();
			//Verif existance du futur ami
			if(!UserBDTools.checkUserExist(pseudo, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur "+pseudo+" inconnu", 1000);
			}
			//Verif de la cl�e
			String key = UserBDTools.checkKeyUpdate(userKey, conn);
			if (key == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur cl� correspondance ou timestamp depasse", 1000);
			}
			//Verif que l'ami n'est pas le demandeur
			if (RelationBDTools.keyPseudoEquals(userKey, pseudo, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("On ne peut pas etre son propre ami ;) ", 1000);
			}
			//Deja ami ?
			if (RelationBDTools.checkFriend(UserBDTools.getUserIdfromKey(key, conn), UserBDTools.getUserId(pseudo, conn), conn)) {
				conn.close();
				return ErrorJSON.serviceRefused(pseudo+" deja suivis", 1000);
			}
			
			//Recup id de l'ami et Recup id du demandeur
			int friendID = UserBDTools.getUserId(pseudo, conn);
			int iD = UserBDTools.getUserIdfromKey(key, conn);
			
			//Insetion dans la BD de la relation
			if(!RelationBDTools.insertFriend(friendID, iD,conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Impsossible d'ajouter la relation", 1000);
			}
			
			//Great Succes !
			retour = ErrorJSON.serviceAccepted();
			retour.put("new_key", key);
			conn.close();
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme // "+e.getMessage(), 1000);
		}
		
		return retour;
	}
	
	
	
	public static JSONObject removeFriend(String pseudo, String userKey) throws JSONException {
		JSONObject retour = new JSONObject();

		//Test des champs
		if(pseudo==null || userKey == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}

		try {
			//Ouverture connexion
			Connection conn = Database.getMySQLConnection();
			
			//Verif de l'ami et de userKey
			if(!UserBDTools.checkUserExist(pseudo,conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			}
			
			String key = UserBDTools.checkKeyUpdate(userKey, conn);
			if (key == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur cl� correspondance ou timestamp depasse", 1000);
			}

			//Recup id de l'ami et de l'utilisateur
			int relationID = UserBDTools.getUserId(pseudo, conn);
			int loginID = UserBDTools.getUserIdfromKey(key, conn);
			
			//Test si ami
			if(!RelationBDTools.checkFriend(loginID, relationID, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Vous ne suivez deja pas "+pseudo, 1000);
			}
			
			//Suppression de la relation
			if(!RelationBDTools.remooveRelation(loginID, relationID, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Suppression de la relation impossible", 1000);
			}
				
			//Great Succes
			retour = ErrorJSON.serviceAccepted();
			retour.put("new_key", key);
			conn.close();
		}

		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme // "+e.getMessage(), 1000);
		}

		return retour;
	}
	
	
	public static JSONObject getListFriends(String userKey) throws JSONException {
		JSONObject retour = new JSONObject();
		
		//Verif des parametres
		if(userKey == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			Connection conn = Database.getMySQLConnection();
			//Verif de la key
			String key = UserBDTools.checkKeyUpdate(userKey, conn);
			if (key == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur clé correspondance ou timestamp depasse", 1000);
			}
			
			//Recup de l'id 
			int userID = UserBDTools.getUserIdfromKey(key, conn);
			
			//retour <- liste des relations
			retour = RelationBDTools.getFriends(userID,conn);
			//test si liste null
			if(retour == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Echec de creation de la liste d'ami", 1000);
			}
			if (retour.length() == 0 )
				retour.put("Resultat", "Vous n'avez pas d'amis pour l'instant");
				
			//Great Succes
			retour.put("new_key", key);
			retour.put("status", "OK");
			conn.close();
		}

		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme // "+e.getMessage(), 1000);
		}

		
		return retour;
	}
	
	public static JSONObject getListAbonnes(String userKey) throws JSONException {
		JSONObject retour = new JSONObject();
		
		//Verif des parametres
		if(userKey == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			Connection conn = Database.getMySQLConnection();
			//Verif de la key
			String key = UserBDTools.checkKeyUpdate(userKey, conn);
			if (key == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur cle correspondance ou timestamp depasse", 1000);
			}
			
			//Recup de l'id 
			int userID = UserBDTools.getUserIdfromKey(key, conn);
			
			//retour <- liste des relations
			retour = RelationBDTools.getAbonnes(userID,conn);
			//test si liste null
			if(retour == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Echec de creation de la liste d'abonnés", 1000);
			}
			if (retour.length() == 0 )
				retour.put("Resultat", "Vous n'avez pas d'abonnes pour l'instant");
				
			//Great Succes
			retour.put("new_key", key);
			retour.put("status", "OK");
			conn.close();
		}

		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme // "+e.getMessage(), 1000);
		}

		
		return retour;
	}

}
