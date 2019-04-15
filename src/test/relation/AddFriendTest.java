package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("romie", "4XMYmtAOVfZfrrfVAiHvipvbnXsDL6RaywYNwWQb2tGNzmvJoGlMiAE7TR6EcsNk");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
