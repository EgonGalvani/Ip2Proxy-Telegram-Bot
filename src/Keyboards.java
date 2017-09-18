import java.util.ArrayList;

import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;

/**
 * 
 * @author Egon Galvani
 * @brief Bot Keyboards Markup 	
 */
public class Keyboards {
	// Telegram Basic Keyboard Markup
	private static class BaseKeyboard extends ReplyKeyboardMarkup{
		private static final long serialVersionUID = 1L;

		public BaseKeyboard() {
			
			setSelective(true); 
			setResizeKeyboard(true); 
			setOneTimeKeyboard(false); 
			
		}
		
	}
	
	// Main menu Keyboard Markup
	public static class InitialKeyboard extends BaseKeyboard {
		private static final long serialVersionUID = 1L;

		public InitialKeyboard() {
			
			KeyboardRow firstRow = new KeyboardRow(); 
			firstRow.add(Commands.isProxyComamnd); 
			firstRow.add(Commands.chartsCommand);
			
			KeyboardRow secondRow = new KeyboardRow(); 
			secondRow.add(Commands.infoCommand);

			ArrayList<KeyboardRow> rows = new ArrayList<>(); 
			rows.add(firstRow); 
			rows.add(secondRow); 
			
			setKeyboard(rows); 
		}
		
	}

	// Charts menu Keyboard Markup 
	public static class ChartsKeyboard extends BaseKeyboard {
		private static final long serialVersionUID = 1L;

		public ChartsKeyboard() {
			KeyboardRow firstRow = new KeyboardRow(); 
			firstRow.add(Commands.typeChartCommand); 
			firstRow.add(Commands.locationChartCommand);
			
			ArrayList<KeyboardRow> rows = new ArrayList<>(); 
			rows.add(firstRow); 
			
			setKeyboard(rows);
		}
	}
}
