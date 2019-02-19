package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;
import services.User;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("Hello World!", "g1LsPVd9aKO4lhOdyPPX4b0283J4Z6pJjbHKXSLwaigvVh6CfORA7ZGQLBNmwBDe");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
