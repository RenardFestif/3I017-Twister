package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("SpartAAAAAAAAAAAAA!", "KumhLRYPqHHlZXe0AHBYCDnAUnqRGhXRaejAWocN1PN3b3QXpJMlFHsp082Kz2s1");												
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}

