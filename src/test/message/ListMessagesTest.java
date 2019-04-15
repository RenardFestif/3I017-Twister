package test.message;

import org.json.JSONException;
import org.json.JSONObject;

import services.Message;

public class ListMessagesTest {

public static void main (String[] args) {
		
		JSONObject newuserser = new JSONObject();
		
		try {
			System.out.println(" Tout à null");
			newuserser= Message.searchMessage(null, null, 0);
			System.out.println(newuserser.toString());
			
			System.out.println("///////////////////////////////");
			System.out.println(" Key n'est pas null ");
			//newuserser= Message.searchMessage(null, "ksTp6Ir5M8edWNlmavpIqbScUGTEd4UM2Zo19ohpImGwMKiQnhfA1wznyNQ8IjAJ" , 0);
			System.out.println(newuserser.toString());
			
			System.out.println("///////////////////////////////");
			System.out.println("user id n'est pas null");
			newuserser= Message.searchMessage("18", "8PBdHTr0AQlRxxEocccIyiiVm1hLJ0QEbzQqAJQBijgeZWfOewuneF0OhRF1a0uq", 6);
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

//aTgdti1kQOr9JdZcdo92b8X2kwX4OK75GHC84pKNEB6EFYCnOFOutS57ELadk5AM
	}
}
