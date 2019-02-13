package tools.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import tools.bd.Database;



public class UserBDTools {

	public static boolean checkUserExist(String login, Connection conn) throws SQLException {

		String query = "SELECT * FROM users WHERE user_login="+login+"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean keyExist = false;
		while(rs.next()) {
			keyExist = true;
		}
		rs.close();
		st.close();
		return keyExist;
	}
	
	public static String insertConnexion(int id, boolean root) throws SQLException{
		return "key";
	}
	
	public static int getUserId(String login) throws SQLException {
		return 3;
	}
	
	public static boolean checkUserMdp(String login,String mdp) throws SQLException{
		return true;
	}
	
	public static boolean insertUser(String login, String mdp, String mail, String nom, String prenom, Connection conn) throws SQLException {
		
		String query = "INSERT INTO USERS VALUES(null, "+login+","+mdp+","+mail+","+nom+","+prenom+")";
		Statement st = conn.createStatement();
		int rs = st.executeUpdate(query);
		if (rs != 0) {
			st.close();
			return true;
		}
			
		return false;
	}

	public static boolean checkKey(String key, Connection conn) throws SQLException {
		
		String query = "SELECT * FROM sessions WHERE session_key="+key+"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean keyExist = false;
		while(rs.next()) {
			keyExist = true;
		}
		rs.close();
		st.close();
		return keyExist;
	}
	
	public static boolean checkConnexion( String key, Connection conn) throws SQLException {
		return checkKey(key, conn);
	}
	
	public static boolean checkConnexion( int userID, Connection conn) throws SQLException {
		
		String query = "SELECT * FROM sessions WHERE user_id="+userID+"";
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
}
