package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListFriendTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListFriends("bd7jchMD1QXwee4UEqoGNYYX2j5gOMdrWrbqWpZCYvxT3OLSxLOIGWeNBQDfuc8i");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
