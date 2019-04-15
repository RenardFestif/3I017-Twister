package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("dfgsdfg", "kRCkYsk6lHyMfcJ9aajRCqUC4HOQFNaCWS7GKNM3A6ar1CNqV4Aw5rhIwvF39OJ2");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
