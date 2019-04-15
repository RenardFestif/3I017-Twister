package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class RemoveMessageTest {

	
	public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			newuserser= Message.removeMessage("5cb4ae6be9cbe00b79ec3eaf", "GBKcAW8lF9qymaJzJo3IWgs1YfdULRz2rlBYQIIgjEY6yORu82KAxNz5KPsx6bIO");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}


	}
}
