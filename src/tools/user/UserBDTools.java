package tools.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class UserBDTools {

	public static boolean checkUserExist(String login) throws SQLException {
		Connection conn = Database.getMySQLConnxexion();
		String query = "SELECT * FROM users WHERE user_login="+login+"";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		boolean userExist = false;
		while(rs.next()) {
			userExist = true;
		}
		return userExist;
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
	
	public static boolean insertUser(String login, String mdp, String mail, String nom, String prenom) throws SQLException {
		return true;
	}

	public static boolean checkKey(String key) throws SQLException {
		return false;
	}
	
	public static boolean checkConnexion(int userID) throws SQLException {
		return true;
	}
}
