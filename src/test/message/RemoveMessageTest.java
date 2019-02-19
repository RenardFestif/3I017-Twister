package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class RemoveMessageTest {

	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.removeMessage(4, "g1LsPVd9aKO4lhOdyPPX4b0283J4Z6pJjbHKXSLwaigvVh6CfORA7ZGQLBNmwBDe");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
