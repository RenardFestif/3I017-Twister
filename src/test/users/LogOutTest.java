package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class LogOutTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= User.logout("URofN884AQxZAsYtY6EKHd5Htwi2ubEJOj2ghB34NZxNZyeKqMI4eKLdhdTEgRvu");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
