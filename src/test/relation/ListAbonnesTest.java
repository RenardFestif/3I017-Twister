package test.relation;

import org.json.JSONException;
import org.json.JSONObject;

import services.Friend;

public class ListAbonnesTest {

	public static void main(String[] args) {
		JSONObject newuserser = new JSONObject();

		try {
			newuserser= Friend.getListAbonnes("tvpTUhohIUoXTcdYzToe7TDhlOtctBAWka2gFwVdqOKETjTTznbUnqJDggudg0pI");
			System.out.println(newuserser.toString());
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
