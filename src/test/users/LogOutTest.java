package test.users;

import org.json.JSONException;
import org.json.JSONObject;

import services.User;

public class LogOutTest {
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= User.logout("l3nByzFyyZmbrd5QPV0cmjj0iTZ1WhHJH97Lzr57Ay7ptkfBo7NZtkWkD2uxKpil");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
