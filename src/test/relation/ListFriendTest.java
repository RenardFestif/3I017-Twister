package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListFriendTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListFriends("2jPqMAL3tnKbJBkC6yPPieH4nY7YA1CH4Z7ppizPseLcKbkS1nX9fppPyO1xixs2");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
