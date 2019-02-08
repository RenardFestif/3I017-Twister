package test;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class CreatUserTest {



	public static void main (String[] args) {
		JSONObject newuserser = new JSONObject();
		JSONObject loin = new JSONObject();
		try {
			newuserser= User.createUser("Moustt", "admin", "oust45Cheaojkfejiad54","Eustache", "chocolat");
			System.out.println(newuserser.toString());
			
			loin = User.login("Moustt", "admin");
			System.out.println(loin.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
