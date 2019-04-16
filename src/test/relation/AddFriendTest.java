package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("dfgsdfg", "PXJIgkPEMtnD5dplncjrACD355euRk8EPBBeAEeJkYsl7rTsMbx167g8OLgwW1Ox");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
