package tools.message;

public class MessageTools {
	
	public static boolean checkMesslength (String mess) {
		if (mess.length()>140)
			return false;
		return true;
	}

}
