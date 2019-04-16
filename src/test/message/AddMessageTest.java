package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("test ajout message ", "wg7RAzEXjZ7qw106mQqnNGx8r7BjTMTFEaMQfsourP8Qz1Jd34Pb9OxTSv1e6QJk");												
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}

