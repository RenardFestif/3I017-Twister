package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("test ajout message ", "Q587Ftdqa4YnbT0Pyli1VKpFCXvWaSrtN93zsy1iBI8yJSRa2kaLmFdXuaGNY5L3");												
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}

