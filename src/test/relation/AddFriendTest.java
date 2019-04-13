package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("Momo", "wye3aW5St8ZABSVihdf29mZ7WqG8hXUA1p5yV81sdjc1QePwlXyAWq981Isov5ZC");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
