/**
 * 
 * @author Egon Galvani
 */
public class BotSettings {
	// Fill with absolute project folder (es. "C:\\Users\\User\\workspace\\Ip2Proxy\\"
	private static final String projectFolder = ""; 

	// Project source folder
	private static final String srcFolder = projectFolder + "src\\"; 
	
	// Project statistics folder
	private static final String statisticsFolder = projectFolder + "statistics\\"; 
	
	// Bot username 
	public static final String BotUsername = "IsProxyBot"; 
    
	// Fill the field with bot token 
	public static final String BotToken = ""; 
    
	// Bin Database Path
    public static final String BinDatabasePath = srcFolder + "ip2proxy\\IP2PROXY-LITE-PX4.BIN"; 

    // Csv File Path
    public static final String CsvDatabasePath = srcFolder + "ip2proxy\\IP2PROXY-LITE-PX4.CSV"; 

    // Proxy Type Chart Image Location 
    public static final String TypeChartPath = statisticsFolder + "typeChart"; 
    
    // Proxy Location Chart Image Path 
    public static final String LocationChartPath = statisticsFolder + "locationChart"; 
}
