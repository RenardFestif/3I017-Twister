package tools.user;

import java.sql.SQLException;

public class UserBDTools {

	public static boolean checkUserExist(String login) throws SQLException {
		return true;
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

	public static boolean checkKey(String key) {
		return false;
	}
	
	public static boolean checkConnexion() {
		return true;
	}
}
