package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class LogUserTest {

	public static void main(String[] args) {
		JSONObject login = new JSONObject();
		try {
			login = User.login("Moustt", "asDF1234");
			System.out.println(login);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
	}

}
