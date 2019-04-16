package tools.message;

import java.nio.file.DirectoryStream.Filter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.management.InstanceAlreadyExistsException;

import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.Filters;

import tools.relation.RelationBDTools;
import tools.user.UserBDTools;

public class MessageBDTools {

	public static String insertMessage(String message, String userKey, Connection conn, Document query, MongoCollection<Document> message_collection) throws SQLException{

		int id = UserBDTools.getUserIdfromKey(userKey, conn);
		boolean res = false;


		query.append("user_id",id);
		query.append("date", new Date());
		query.append("content", message);
		query.append("user_name", UserBDTools.getLogin(id, conn));

		message_collection.insertOne(query);



		FindIterable<Document> fi = message_collection.find(query);
		MongoCursor<Document> cur = fi.iterator();

		while(cur.hasNext()) {
			cur.next();
			res = true;
		}
		ObjectId retour = query.getObjectId("_id");	
		if (!res)
			return null;
		return retour.toString();
	}


	public static boolean removeMessage(String idMessage, Connection conn,Document query, MongoCollection<Document> message_collection) throws SQLException {
		//ICI 

		query.append("_id",new ObjectId(idMessage));

		message_collection.deleteOne(query);

		FindIterable<Document> fi = message_collection.find(query);
		MongoCursor<Document> cur = fi.iterator();

		boolean res = true;
		while(cur.hasNext()) {
			System.out.println(cur.next());
			res = false;
		}
		return res;

	}

	public static JSONObject getMessages(int userID,Connection conn) throws SQLException, JSONException {
		JSONObject retour = new JSONObject();

		String query = "SELECT * FROM messages WHERE user_id='"+userID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			retour.put(rs.getString("message_id"), rs.getString("message_content"));
		}
		rs.close();
		st.close();
		return retour;
	}



	public static boolean checkAuteur(String userKey, String iDMessage, Connection conn, Document query, MongoCollection<Document> message_collection) throws SQLException {

		int userID = UserBDTools.getUserIdfromKey(userKey, conn);

		query.append("user_id", userID);
		query.append("_id", new ObjectId(iDMessage));

		message_collection.find(query);

		FindIterable<Document> fi = message_collection.find(query);
		MongoCursor<Document> cur = fi.iterator();


		while(cur.hasNext()) {
			return true;
		}


		return false;
		

	}


	public static JSONObject getMessages(Document query, MongoCollection<Document> message_collection) throws JSONException {
		JSONObject retour = new JSONObject();

		FindIterable<Document> fi = message_collection.find().sort(new Document("date", -1));
		MongoCursor<Document> cur = fi.iterator();

		while(cur.hasNext()) {

			Document obj = cur.next();

			retour.put(obj.getObjectId("_id").toString(), obj.getString("content"));
		}

		return retour;
	}


	public static JSONObject getMessages(String userKey,Connection conn,String pattern, Document query, MongoCollection<Document> message_collection) throws SQLException, JSONException {
		JSONObject retour = new JSONObject();
		int userID = UserBDTools.getUserIdfromKey(userKey, conn);
		JSONObject listFriend = RelationBDTools.getFriends(userID, conn);
		List<JSONObject> messages = new ArrayList<JSONObject>();
		
		JSONArray friends = (JSONArray) listFriend.get("amis") ;
		
	
		ArrayList<Integer> concerned = new ArrayList<>();
		concerned.add(userID);
		
		for(int i = 0; i<friends.length(); i++) {
			@SuppressWarnings("unchecked")
			ArrayList<JSONObject> x = (ArrayList<JSONObject>) friends.get(0);
			
			concerned.add(UserBDTools.getUserId((String) x.get(0).getString("login"), conn));
		}
		
		

		FindIterable<Document> fi = null;
		if(pattern == null) {
			fi = message_collection.find((Filters.in("user_id",concerned))).sort(new Document("date", -1));
		}else {
			fi = message_collection.find(Filters.and(Filters.in("user_id",concerned),Filters.regex("content", pattern))).sort(new Document("date", -1));
		}
		
		MongoCursor<Document> cur = fi.iterator();
		
		while(cur.hasNext()) {
			JSONObject mess = new JSONObject();
			Document obj = cur.next();
			System.out.println(obj);
			mess.put("auteur",obj.getString("user_name"));
			mess.put("date",obj.getDate("date").toString());
			mess.put("content",obj.getString("content"));
			mess.put("messID",obj.getObjectId("_id").toString());
			messages.add(mess);
		}
		retour.put("messages", messages);
		return retour;
	}


	public static JSONObject getMessages(String userKey, String pattern, int userId, Document query,
			MongoCollection<Document> message_collection) throws JSONException {

		JSONObject retour = new JSONObject();
		List<JSONObject> messages = new ArrayList<JSONObject>();
		
		query.append("user_id",userId);
		FindIterable<Document> fi = null;
		
		if(pattern == null) {
			fi = message_collection.find(query).sort(new Document("date", -1));
		}else {
			fi = message_collection.find(Filters.and(query,Filters.regex("content", pattern))).sort(new Document("date", -1));
		}
		MongoCursor<Document> cur = fi.iterator();

		while(cur.hasNext()) {

			JSONObject mess = new JSONObject();
			Document obj = cur.next();
			System.out.println(obj);
			mess.put("auteur",obj.getString("user_name"));
			mess.put("date",obj.getDate("date").toString());
			mess.put("content",obj.getString("content"));
			mess.put("messID",obj.getObjectId("_id").toString());
			
			messages.add(mess);
		}
		retour.put("messages", messages);
		return retour;
	}





}
