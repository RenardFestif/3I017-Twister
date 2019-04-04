package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class CreatUserTest {
	public static void main (String[] args) {
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= User.createUser("moiuyfd", "asDF123454321", "momomo@mail.fr","Maurice", "LeSinge");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
