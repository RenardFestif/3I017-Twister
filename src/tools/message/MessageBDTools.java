package tools.message;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import org.bson.Document;
import org.json.JSONException;
import org.json.JSONObject;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;

import tools.user.UserBDTools;

public class MessageBDTools {

	public static boolean insertMessage(String message, String userKey, Connection conn, Document query, MongoCollection<Document> message_collection) throws SQLException{

		int id = UserBDTools.getUserIdfromKey(userKey, conn);
		query.append("user_id",id);
		query.append("date", new Date());
		query.append("content", message);
		query.append("user_name", UserBDTools.getLogin(id, conn));

		message_collection.insertOne(query);

		FindIterable<Document> fi = message_collection.find(query);
		MongoCursor<Document> cur = fi.iterator();

		boolean res = false;
		while(cur.hasNext()) {
			cur.next();
			res = true;
		}
		return res;
	}

	
	public static boolean removeMessage(int idMessage, Connection conn,Document query, MongoCollection<Document> message_collection) throws SQLException {
		//ICI §§§§§
		query.append("_id",idMessage);
		
		message_collection.findOneAndDelete(query);
		
		FindIterable<Document> fi = ;
		MongoCursor<Document> cur = fi.iterator();

		boolean res = false;
		while(cur.hasNext()) {
			cur.next();
			res = true;
		}
		
		if(!res)
			return res;
		
		quer

		
		
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

	public static JSONObject getMessage(String pattern, Connection conn) throws SQLException, JSONException{
		JSONObject retour = new JSONObject();

		String query = "SELECT * FROM messages WHERE message_content LIKE '%"+pattern+"%'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			// Pas du tout sure de celle la, j'ai mis un peu au pif
			retour.put(rs.getString("message_id"), rs.getString("message_content"));
		}
		rs.close();
		st.close();
		return retour;
	}

	public static boolean checkAuteur(String userKey, int iDMessage, Connection conn) throws SQLException {
		
		int userID = UserBDTools.getUserIdfromKey(userKey, conn);
		
		String query = "Select * FROM messages WHERE message_id='"+iDMessage+"' AND user_id='"+userID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			rs.close();
			st.close();
			return true;
		}
		rs.close();
		st.close();
		return false;
	}



}
