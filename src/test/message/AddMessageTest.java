package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;
import services.User;

public class AddMessageTest {
	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.addMessage("TESSSSSST!", "pxNXnO9lJLto8fcFXzExSWYKzgL8FAoLhKyfeC0e9gnDfbleQezIBo0dRNBJkDsK");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}

}
