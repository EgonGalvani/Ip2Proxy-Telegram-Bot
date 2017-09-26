import java.io.File;
import java.io.IOException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import ip2proxy.IP2Proxy;
import ip2proxy.ProxyResult;
import ip2proxy.IP2Proxy.IOModes;

import com.vdurmont.emoji.EmojiParser;

/**
 * 
 * @author Egon Galvani
 */
public class ProxyBot extends TelegramLongPollingBot {
    // Instance of IP2Proxy used to reasearch requested ips  
    private IP2Proxy ip2Proxy = new IP2Proxy(); 
    
    // last command 
    private String lastCommand = ""; 
    
    public ProxyBot() {
		try {		
			ip2Proxy.Open(BotSettings.BinDatabasePath, IOModes.IP2PROXY_MEMORY_MAPPED);	
			
			System.out.println("Loading data in memory");
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("Analyzing data..."); 
    
		ChartsBuilder.CHART_TYPE requestedCharts = null; 		
		if(!Utils.existsFile(BotSettings.LocationChartPath))
			requestedCharts = ChartsBuilder.CHART_TYPE.PROXY_LOCATION; 
		if(!Utils.existsFile(BotSettings.TypeChartPath))
			requestedCharts = (requestedCharts == ChartsBuilder.CHART_TYPE.PROXY_LOCATION) 
				? ChartsBuilder.CHART_TYPE.BOTH : ChartsBuilder.CHART_TYPE.PROXY_TYPE; 
		
		if(requestedCharts != null) {
			ChartsBuilder chartsBuilder = new ChartsBuilder(BotSettings.CsvDatabasePath); 
			chartsBuilder.parse();
			chartsBuilder.createChart(requestedCharts);
		}		
    }
    
    // return Bot Username 
	@Override
	public String getBotUsername() {
		return BotSettings.BotUsername; 
	}
	
	// return Telegram Bot Token 
	@Override
	public String getBotToken() {
		return BotSettings.BotToken; 
	}

	// onupdate
	@Override
	public void onUpdateReceived(Update update) {
		if(update.hasMessage() && update.getMessage().hasText()) {
			
			Message message = update.getMessage(); 
			String text = message.getText(); 
			
			SendMessage reply = new SendMessage(); 
			reply.setChatId(message.getChatId()); 
			
			if(Utils.isCommand(text)) {
				lastCommand = text; 
				
				switch(text) {
					case Commands.startCommand: 
							reply.setText(EmojiParser.parseToUnicode("Please select an option :point_down:")); 
							reply.setReplyMarkup(new Keyboards.InitialKeyboard()); 
							
						break; 
					case Commands.infoCommand: 
							reply.setText(getBotInfo()); 
						break; 
					case Commands.isProxyComamnd: 
							reply.setText("Insert an ip: "); 
						break; 
					case Commands.chartsCommand: 
							reply.setText(EmojiParser.parseToUnicode("Please select an option :point_down:")); 
							reply.setReplyMarkup(new Keyboards.ChartsKeyboard()); 
						break; 
					case Commands.typeChartCommand: 
							reply.setText(EmojiParser.parseToUnicode("Uploading file...")); 
							
							sendImageUploadingFile(BotSettings.TypeChartPath + ".png", reply.getChatId());
							
							reply.setReplyMarkup(new Keyboards.InitialKeyboard()); 
						break; 
					case Commands.locationChartCommand: 
							reply.setText(EmojiParser.parseToUnicode("Uploading file...")); 
						
							sendImageUploadingFile(BotSettings.LocationChartPath + ".png", reply.getChatId());
						
							reply.setReplyMarkup(new Keyboards.InitialKeyboard()); 
						break; 
				}
				
				replyMessage(reply);
			} else if(lastCommand.equals(Commands.isProxyComamnd)) {
				
				if(Utils.isIp(text)) {
					
					try {
						ProxyResult result = ip2Proxy.GetAll(text);
					
						if(result.getIs_Proxy() == 1) 
							reply.setText(result.toString()); 
						else 
							reply.setText("The ip you have inserted is not a proxy."); 
						
						reply.setReplyMarkup(new Keyboards.InitialKeyboard()); 
					} catch (IOException e) {
						
						e.printStackTrace();
					}
					
				} else {
					reply.setText("The ip you have inserted is not valid. \n\tPlease insert a valid ip address: ");
				}
				
				replyMessage(reply);
			}
			
			
		}	
	}
		
	// reply to message 
	@SuppressWarnings("deprecation")
	private void replyMessage(SendMessage reply) {
		try {			
			sendMessage(reply); 
		}catch(TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	// send image uploading file 
	private void sendImageUploadingFile(final String filePath, final String chatId) {
		// Create send method
		SendPhoto sendPhotoRequest = new SendPhoto(); 
		// Set destination chat id
		sendPhotoRequest.setChatId(chatId); 
        // Set the photo file as a new photo 
		sendPhotoRequest.setNewPhoto(new File(filePath)); 
		
		try {
			sendPhoto(sendPhotoRequest); 
		} catch (TelegramApiException e) {
			e.printStackTrace();
		}
	}
	
	// return database info 
	private String getBotInfo() {
		return "Bot settings info: "+ 
				"\n\tDatabase: " + ip2Proxy.GetDatabaseVersion() + 
				"\n\tModule: " + ip2Proxy.GetModuleVersion() + 
				"\n\tPackage: " + ip2Proxy.GetPackageVersion(); 
	}
}
