package services;

import java.io.Closeable;
import java.sql.Connection;
import java.sql.SQLException;

import org.json.JSONException;
import org.json.JSONObject;

import tools.bd.Database;
import tools.relation.RelationBDTools;
import tools.user.UserBDTools;

public class Friend{
	
	
	

	public static JSONObject addFriend(String pseudo, String userKey) throws JSONException, SQLException {
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
				return ErrorJSON.serviceRefused("Utilisateur inconnu", 1000);
			}
			//Verif de la clée
			if (!UserBDTools.checkConnexion(userKey, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur clé", 1000);
			}
			
			//Recup id de l'ami et Recup id du demandeur
			int friendID = UserBDTools.getUserId(pseudo, conn);
			int iD = UserBDTools.getUserIdfromKey(userKey, conn);
			
			//Insetion dans la BD de la relation
			if(!RelationBDTools.insertFriend(friendID, iD,conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Impsossible d'ajouter la relation", 1000);
			}
			
			//Great Succes !
			retour = ErrorJSON.serviceAccepted();
			conn.close();
		}
		
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme"+e.getMessage(), 1000);
		}
		
		return retour;
	}
	
	
	
	public static JSONObject removeFriend(String pseudo, String userKey) throws JSONException, SQLException {
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
			if(!UserBDTools.checkConnexion(UserBDTools.getUserId(userKey, conn), conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur non-connectï¿½", 1000);
			}

			//Recup id de l'ami et de l'utilisateur
			int relationID = UserBDTools.getUserId(pseudo, conn);
			int loginID = UserBDTools.getUserIdfromKey(userKey, conn);
			
			//Test si ami
			if(!RelationBDTools.checkFriend(loginID, relationID, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Suppression de la relation impossible", 1000);
			}
			
			//Suppression de la relation
			if(!RelationBDTools.remooveRelation(loginID, relationID, conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Suppression de la relation impossible", 1000);
			}
				
			//Great Succes
			retour = ErrorJSON.serviceAccepted();
			conn.close();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme"+e.getMessage(), 1000);
		}

		return retour;
	}
	
	
	public static JSONObject getListFriends(String userKey) throws JSONException, SQLException {
		JSONObject retour = new JSONObject();
		
		//Verif des parametres
		if(userKey == null) {
			return ErrorJSON.serviceRefused("Champs manquants", -1);
		}
		
		try {
			Connection conn = Database.getMySQLConnection();
			//Verif de la key
			if (!UserBDTools.checkConnexion(userKey,conn)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur non-connecte", 1000);
			}
			
			//Recup de l'id 
			int userID = UserBDTools.getUserIdfromKey(userKey, conn);
			
			//retour <- liste des relations
			retour = RelationBDTools.getFriends(userID,conn);
			//test si liste null
			if(retour == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Echec de creation de la liste d'ami", 1000);
			}
				
			//Great Succes
			retour.put("status", "OK");
			conn.close();
		}
		catch(JSONException e) {
			return ErrorJSON.serviceRefused("JSON probleme"+e.getMessage(), 100);
		}
		catch (SQLException e) {
			return ErrorJSON.serviceRefused("SQL probleme"+e.getMessage(), 1000);
		}

		
		return retour;
	}

}
