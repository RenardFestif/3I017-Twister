package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class SearchFriendTest {
public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.searchFriend("Momo", "nlXCQBMuS9ScDNnjHiBmb1oTPgCVtEsFZysZIu9GfKjGAtSJ3YWjMisdVQ4V8FHD");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
