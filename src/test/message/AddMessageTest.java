package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("test ajout message 2 ", "MEe1J3DyYIgY6AgTPsIzILxeeAOrzvYMcrLXICzB2rmv8ztkE2sE7Z37h64zCoLl");												
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}

