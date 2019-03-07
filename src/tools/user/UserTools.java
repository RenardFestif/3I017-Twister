package tools.user;

public class UserTools {
	
	public static final int length = 64;

	public static boolean checkFormatMdp(String mdp) {

		if(mdp.length()<8)	
			return false;
		if (!mdp.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$"))
			return false; 
		return true;
	}

	public static boolean checkFormatMail(String mdp) {


		if(mdp.length()<8)	
			return false;
		if (!mdp.matches("^([\\w\\-\\.]+)@((\\[([0-9]{1,3}\\.){3}[0-9]{1,3}\\])|(([\\w\\-]+\\.)+)([a-zA-Z]{2,4}))$"))
			return false;
		return true;
	}
	
	public static String generateKey(int length)
	{
		    String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890"; 
		    StringBuilder pass = new StringBuilder(length);
		    for(int x=0;x<length;x++)
		    {
		       int i = (int)Math.floor(Math.random() * 62); // Si tu supprimes des lettres tu diminues ce nb
		       pass.append(chars.charAt(i));
		    }
		    return pass.toString();
	}


}
