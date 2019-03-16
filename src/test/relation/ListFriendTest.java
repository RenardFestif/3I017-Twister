package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListFriendTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListFriends("loNH9BcNcdyWUlcG9Z7aW7mkNdKqtaAHeK0cXk8b3V7Twky0iAzMWly6w3PHopcj");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
