package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListFriendTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListFriends("j6UMugnbSSezTuHSQBwsqGFH123gUjcgBIXFlGPkxZX90xQ8TZvhnARguJTrjzYV");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
