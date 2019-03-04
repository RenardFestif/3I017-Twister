package tools.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.annotation.Generated;

import tools.bd.Database;



public class UserBDTools {

	public static boolean checkUserExist(String login, Connection conn) throws SQLException {

		String query = "SELECT user_login FROM users WHERE user_login='"+login+"'";
		Statement st;
		boolean keyExist = false;
		st = conn.createStatement();
		ResultSet rs = st.executeQuery(query); 	

		while(rs.next()) {
			keyExist = true;
		}
		rs.close();
		st.close();

		return keyExist;
	}

	public static String insertConnexion(int id, int root, Connection conn) throws SQLException{

		String key = UserTools.generateKey(64);
		//Solution de fortune


		String query = "INSERT INTO sessions VALUES('"+key+"','"+id+"', '"+root+"',NOW())";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return key;
		}

		return null;

	}
	
	public static boolean deleteConnexion(String key, Connection conn) throws SQLException{

		String query = "DELETE FROM sessions WHERE session_key='"+key+"'";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true ;
		}

		return false;

	}

	public static int getUserId(String login, Connection conn) throws SQLException {

		int id = 0 ;
		String query = "SELECT user_id FROM users WHERE user_login='"+login+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);

		while(rs.next()) {
			id = rs.getInt("user_id");
		}
		rs.close();
		st.close();
		return id;
	}

	public static boolean checkUserMdp(String login,String mdp, Connection conn) throws SQLException{

		String query = "SELECT * FROM users WHERE user_login='"+login+"' AND user_password='"+mdp+"'";
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(query);

		boolean mdpCorrecte = false;
		while(rs.next()) {
			mdpCorrecte = true;
		}
		rs.close();
		st.close();
		return mdpCorrecte;
	}


	public static boolean insertUser(String login, String mdp, String mail, String nom, String prenom, Connection conn) throws SQLException  {

		String query = "INSERT INTO users (user_id, user_login, user_password, user_mail, user_prenom, user_nom) VALUES(null, '"+login+"','"+mdp+"','"+mail+"','"+prenom+"','"+nom+"')";

		Statement st;

		st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}
		st.close();
		return false;

	}

	public static boolean checkKey(String key, Connection conn) throws SQLException {
		
		String query = "SELECT * FROM sessions WHERE session_key='"+key+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean keyExist = false;
		if(rs.next()) {
			keyExist = true;	
		}		
		rs.close();
		st.close();
		return keyExist;
	}
	
	public static String checkKeyUpdate(String key, Connection conn) throws SQLException {
		String query = "SELECT * FROM sessions WHERE session_key='"+key+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean keyExist = false;
		String newKey = null ;
		if(rs.next()) {
			keyExist = true;
			
			Date date = rs.getTime("session_start");
			Date now = new Date();
			
			Long diff = (long) Math.abs(now.getMinutes() - date.getMinutes());
			
			
			// si connexion inferieur à 10 minutes 
			System.out.println(diff);
			if(diff < 10) {
				boolean present = true;
				
				
				
				while(present) {
					//Generation nouvelle key 
					newKey = UserTools.generateKey(UserTools.length);
					

					//verif si elle est pas dans la base 
					query = "SELECT * FROM sessions WHERE session_key='"+newKey+"'";
					ResultSet rs2 = st.executeQuery(query);
					present = false;
					while(rs2.next()) {
						
						present = true;
					}
				}
				
				query = "UPDATE sessions SET session_key = '"+newKey+"' WHERE session_key='"+key+"'";
				st.executeUpdate(query);
				query = "UPDATE sessions SET session_start = NOW() WHERE session_key='"+newKey+"'";
				st.executeUpdate(query);
				
			}
			else {
				
				deleteConnexion(key, conn);
				rs.close();
				st.close();
				return null;
				
			}
			
		}		
		rs.close();
		st.close();
		return newKey;
	}

	public static boolean checkConnexion( String key, Connection conn) throws SQLException {
		return checkKey(key, conn);
	}
	

	

	public static boolean checkConnexion( int userID, Connection conn) throws SQLException {

		String query = "SELECT * FROM sessions WHERE user_id='"+userID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean userConnected = false;
		while(rs.next()) {
			userConnected = true;
		}
		rs.close();
		st.close();
		return userConnected;
	}

	public static int getUserIdfromKey(String userKey, Connection conn) throws SQLException{


		String query = "SELECT user_id FROM sessions WHERE session_key='"+userKey+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		int id= -1;
		while(rs.next()) {
			id = rs.getInt("user_id");
		}
		rs.close();
		st.close();
		return id;

	}

	public static String getLogin(int userID, Connection conn) throws SQLException{
		String query = "SELECT user_login FROM users WHERE user_id='"+userID+"'";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		String login= null;
		while(rs.next()) {
			login = rs.getString("user_login");
		}
		rs.close();
		st.close();
		return login;
	}
}
