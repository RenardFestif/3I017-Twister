package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class DeleteFriendTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Friend.removeFriend("Moustt", "tQGzIEfFniM95biAwIxN0SePjrkQaYEzj8otUHICtX8BzrjPeNoxnXEdab4ZuERq");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
