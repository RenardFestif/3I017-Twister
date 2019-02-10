package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.users.CreateUser;

public class CreatUserTest {



	public static void main (String[] args) {
		JSONObject newuserser = new JSONObject();
		JSONObject loin = new JSONObject();
		try {
			newuserser= CreateUser.createUser("Moustt", "admin", "oust45Cheaojkfejiad54","Eustache", "chocolat");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
