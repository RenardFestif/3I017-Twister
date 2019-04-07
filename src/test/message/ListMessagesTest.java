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
			newuserser= Message.searchMessage(null, "DOPFQEeKm468Z0A6ZpEWQdwwsdliAzkJ88pGgddEafxyGQgYDHtZ53HGcfcvRW3F" , 0);
			System.out.println(newuserser.toString());
			
			System.out.println("///////////////////////////////");
			System.out.println("user id n'est pas null");
			newuserser= Message.searchMessage("He", "aTgdti1kQOr9JdZcdo92b8X2kwX4OK75GHC84pKNEB6EFYCnOFOutS57ELadk5AM", 1);
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

//aTgdti1kQOr9JdZcdo92b8X2kwX4OK75GHC84pKNEB6EFYCnOFOutS57ELadk5AM
	}
}
