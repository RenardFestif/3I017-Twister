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
			newuserser= Message.searchMessage(null, "Cs16ek8KZsYnfJw1LgRlKuvOdVX4TB5wda9UoEBLeY77pahBq1yy9IRIEv9E0LPR" , 0);
			System.out.println(newuserser.toString());
			
			System.out.println("///////////////////////////////");
			System.out.println("user id n'est pas null");
			//newuserser= Message.searchMessage("18", "3AjE8p5ua8jaJiLFqENUH5jHxI2wSHfQs0Z2Of0LqJ6cSkoQmTFIBmk9hCO43JxP", 6);
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

//aTgdti1kQOr9JdZcdo92b8X2kwX4OK75GHC84pKNEB6EFYCnOFOutS57ELadk5AM
	}
}
