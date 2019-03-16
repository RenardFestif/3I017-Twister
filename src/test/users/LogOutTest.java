package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class LogOutTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= User.logout("bjArDCWRvxIdoO8x4SEHOQ7gOpneO7u0f074WCuboJuBUomOY6JGVn9Z7c45s5oo");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
