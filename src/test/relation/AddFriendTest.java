package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;
import services.Message;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("Moustt", "UvXlRPYkgxsE92d7TpOdmzyMqexyyjQ796c0WL9aF6VhTEYXwfCX9i90ZreMAgHG");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
