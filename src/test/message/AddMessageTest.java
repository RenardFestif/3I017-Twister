package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;
import services.User;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("Hello World!", "UvXlRPYkgxsE92d7TpOdmzyMqexyyjQ796c0WL9aF6VhTEYXwfCX9i90ZreMAgHG");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
