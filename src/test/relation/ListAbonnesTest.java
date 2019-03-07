package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListAbonnesTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListAbonnes("dAnWGufdd0jgjSNe1gkGiHHYCq3i3wHI8I02BlwBcgoH3laKsqFfvEmQOf9kJ8RS");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
