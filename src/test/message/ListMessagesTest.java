package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class ListMessagesTest {

public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.getMessages("g1LsPVd9aKO4lhOdyPPX4b0283J4Z6pJjbHKXSLwaigvVh6CfORA7ZGQLBNmwBDe");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
