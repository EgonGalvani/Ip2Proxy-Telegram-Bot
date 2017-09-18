import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;

/*
 * 
 * @author Egon Galvani
 * @brief Some basic utils functions 
 */
public class Utils {
	/**
	 * @param ip
	 * @return true if connection succeed otherwise false
	 */
	public static boolean isIp(String ip) {
		try {
			InetAddress.getByName(ip);
			return true; 
		} catch (UnknownHostException e) {
			return false; 
		} 
	}
	
	/**
	 * 
	 * @param text - Text of recived message 
	 * @return true if is Bot command otherwise false
	 */
	public static boolean isCommand(String text) {		
		return Arrays.asList(text.split(" ")).size() == 1 && text.startsWith(Commands.commandInitChar); 
	}
	
	/**
	 * 
	 * @param path
	 * @return true if file exists otherwise false
	 */
	public static boolean existsFile(final String path) {
		return new File(path).exists(); 
	}
}
