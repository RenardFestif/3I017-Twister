package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.users.CreateUser;

public class CreatUserTest {



	public static void main (String[] args) {
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= CreateUser.createUser("Moustt", "asDF1234", "eust.choco@etu.upmc.fr","Eustache", "chocolat");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
