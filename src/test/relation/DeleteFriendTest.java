package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class DeleteFriendTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.removeFriend("Momo", "g1LsPVd9aKO4lhOdyPPX4b0283J4Z6pJjbHKXSLwaigvVh6CfORA7ZGQLBNmwBDe");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
