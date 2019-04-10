package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListFriendTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListFriends("FbhXXxyDFQioCuS71w70aMLf1g3Lybj8ZKddmPRLs9iVYuA7BuuEc9ahW6ID2maP");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
