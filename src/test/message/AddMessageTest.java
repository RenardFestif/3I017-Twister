package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("test ajout message ", "w98rF7mA8zHm2FiCaNKOFryVjkj4nvsTlwvdN1Fth4qSJLCWhqEj7VaTzkXzgbdL");												
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}

