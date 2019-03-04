package test.relation;


import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;
import services.Message;

public class AddFriendTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.addFriend("Moustt", "I2cjo2JUZFvfKvLqmQ7aa6g3QfCJNjNXVFZcxEuvdsgqzlp9FHB6JOwzeJeBJdkm");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
