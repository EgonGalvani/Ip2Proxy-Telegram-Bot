import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

/**
 * @author Egon Galvani
 * @version 1.0 
 * @brief Main class to create ProxyBot
 * @date 10 of September of 2017 
 */

public class Main {

    public static void main(String[] args) {
    	// Initialize Api Context
    	ApiContextInitializer.init();
		
    	// Instantiate Telegram Bots API
    	TelegramBotsApi botsApi = new TelegramBotsApi(); 

    	// Register our bot
		try {
			botsApi.registerBot(new ProxyBot()); 
		}catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
}