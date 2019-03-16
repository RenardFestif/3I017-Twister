package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("Momo", "GazWUsK3gTcnA9dPoTlTDOp7cvuW7aFaAPKmgucZO74itIuT5ad6wklny1pkiwVx");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
