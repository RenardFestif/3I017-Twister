package services;

import java.sql.Connection;
import java.sql.SQLException;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import tools.bd.Database;
import tools.message.MessageBDTools;
import tools.message.MessageTools;
import tools.user.UserBDTools;


public class Message {

	public static JSONObject addMessage(String message, String userKey) throws JSONException {
		JSONObject retour = new JSONObject();
		//verif des parametres 
		if(message == null || userKey == null) 
			return ErrorJSON.serviceRefused("Champs manquants", -1);

		//verif longueur message
		if(!MessageTools.checkMesslength(message))
			return ErrorJSON.serviceRefused("Message trop long (<140 caracteres)", -1);

		try {

			MongoClient mongo = MongoClients.create();
			MongoDatabase db = mongo.getDatabase("thoirey_saadi");
			
			MongoCollection<Document> message_collection = db.getCollection("messages");
			Document query = new Document();
			
			
			
			Connection conn = Database.getMySQLConnection();
			

			//verif de la key
			String key = UserBDTools.checkKeyUpdate(userKey, conn);
			if (key == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur de correspondance ou timestamp depasse", 1000);
			}

			//Insertion
			String id_mess = MessageBDTools.insertMessage(message, key, conn, query, message_collection);
			if( id_mess == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Insertion Impossible", 1000);
			}

			//Great Succes !
			retour = ErrorJSON.serviceAccepted();
			retour.put("user_id", UserBDTools.getUserIdfromKey(key, conn));
			retour.put("message", message);
			retour.put("message_id", id_mess);
			retour.put("new_key", key);
			conn.close();

		} catch (SQLException e) {

			return ErrorJSON.serviceRefused("Erreur SQL // "+e.getMessage(), 1000);
		}

		return retour;
	}
	


	public static JSONObject removeMessage(String iDMessage, String userKey) throws JSONException{
		JSONObject retour = new JSONObject();

		//Faut bien faire commencer l'entr�e id de la table message a 1 sous peine de generer des erreurs
		if( iDMessage == null ||userKey == null) 
			return ErrorJSON.serviceRefused("erreur de parametres", -1);

		try {
			
			MongoClient mongo = MongoClients.create();
			MongoDatabase db = mongo.getDatabase("thoirey_saadi");
			
			MongoCollection<Document> message_collection = db.getCollection("messages");
			Document query = new Document();
			
			Connection conn = Database.getMySQLConnection();

			//Verif de la key
			String key = UserBDTools.checkKeyUpdate(userKey, conn);
			if (key == null) {
				conn.close();
				return ErrorJSON.serviceRefused("Erreur de correspondance ou timestamp depasse", 1000);
			}
		
			
			//Verif de l'auteur du message + Verif que le message existe
			if(!MessageBDTools.checkAuteur(key, iDMessage, conn,query,message_collection)) {
				conn.close();
				return ErrorJSON.serviceRefused("Utilisateur non auteur du message ou id message non existant", 1000);
			}
			System.out.println("la");

			//Suppression du message de la BD
			if(!MessageBDTools.removeMessage(iDMessage, conn, query,message_collection)) {
				conn.close();
				return ErrorJSON.serviceRefused("Suppression Impossible", 1000);
			}

			//Great Succes !
			retour = ErrorJSON.serviceAccepted();
			retour.put("new_key", key);
			conn.close();

		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}


		return retour;
	}







	public static JSONObject searchMessage(String pattern, String userKey, int userId) throws JSONException {
		JSONObject retour = new JSONObject();
		
		//Verif parametres
		
		
		try {
			
			Connection conn = Database.getMySQLConnection();
			MongoClient mongo = MongoClients.create();
			MongoDatabase db = mongo.getDatabase("thoirey_saadi");
			
			MongoCollection<Document> message_collection = db.getCollection("messages");
			Document query = new Document();
			
			if(pattern == null && userKey == null && userId == 0 ) {
				
				//Recup de tout les messages
				retour = MessageBDTools.getMessages(query, message_collection);
				conn.close();
				retour.put("status", "OK");
				return retour;
			}
			
			if(userId == 0 && userKey != null) {
				//Verif de la key
				String key = UserBDTools.checkKeyUpdate(userKey, conn);
				if (key == null) {
					conn.close();
					return ErrorJSON.serviceRefused("Erreur de correspondance ou timestamp depasse", 1000);
				}

				retour = MessageBDTools.getMessages(key, conn, pattern, query, message_collection);
				retour.put("new_key", key);
				retour.put("status", "OK");
				conn.close();
				return retour;

			}

			if(userId!=0) {
				String key = UserBDTools.checkKeyUpdate(userKey, conn);
				if (key == null) {
					conn.close();
					return ErrorJSON.serviceRefused("Erreur de correspondance ou timestamp depasse", 1000);
				}
				
				retour = MessageBDTools.getMessages(key, pattern, userId , query, message_collection);
				conn.close();
				retour.put("new_key", key);
				retour.put("status", "OK");
				return retour;
			}
			
			
			
			if(retour.length() == 0)
				retour.put("Resultat", "Pas de resultat pour "+userKey);
				
			//Great Succes
			retour.put("status","OK");
			conn.close();

		} catch (SQLException e) {
			return ErrorJSON.serviceRefused(e.getMessage(), 1000);
		}


		return retour;
	}



	

}
