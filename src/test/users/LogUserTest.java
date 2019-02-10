package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.users.LogUser;

public class LogUserTest {

	public static void main(String[] args) {
		JSONObject login = new JSONObject();
		try {
			login = LogUser.login("Eustache", "chococo");
			System.out.println(login);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
