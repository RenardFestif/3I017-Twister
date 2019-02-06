package tools.user;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserTools {

	public static boolean checkFormatMdp(String mdp) {
		
		Pattern format = Pattern.compile("(^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*\\W))");
		Matcher m = format.matcher(mdp);
		boolean b = m.matches();
		
		if(mdp.length()<8 && b == false)	
			return false;
		return true;
	}
	
public static boolean checkFormatMail(String mdp) {
		
		Pattern format = Pattern.compile("(^[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+$)");
		Matcher m = format.matcher(mdp);
		boolean b = m.matches();
		
		if(mdp.length()<8 && b == false)	
			return false;
		return true;
	}
	
	
}
