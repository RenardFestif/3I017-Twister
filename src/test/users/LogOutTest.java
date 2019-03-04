package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class LogOutTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= User.logout("o9eVBqu1wtjsrNY60v9S6W2b5LKMUHxmEXwq0FDPfKjEQbTICNbJFTD1fKBYU5Qh");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
