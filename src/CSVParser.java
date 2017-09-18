import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 
 * @author Egon Galvani
 *
 */
public abstract class CSVParser {
	// Default separator and quote 
	private static final char DEFAULT_SEPARATOR = ','; 
	private static final char DEFAULT_QUOTE = '"'; 
	
	// Csv data file location 
	private String csvFilePath;

	protected abstract void parsingAlgorithm(final List<String> line); 
	
	public CSVParser(final String csvFilePath) {
		 this.csvFilePath = csvFilePath; 
	}

	/*
	 * Start parse process 
	 * every line of csvFile is parsed and passed to the function "parsingAlgorithm" 
	 * that will be implemented in the extended class (ChartsBuilder.class) 
	 */
	public void parse() {
		try {
			Scanner scanner = new Scanner(new File(csvFilePath));
			while(scanner.hasNext()) {
				List<String> line = parseLine(scanner.nextLine()); 
				parsingAlgorithm(line);
			}
		
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
    public List<String> parseLine(String cvsLine) {
        return parseLine(cvsLine, DEFAULT_SEPARATOR, DEFAULT_QUOTE);
    }

    public List<String> parseLine(String cvsLine, char separators) {
        return parseLine(cvsLine, separators, DEFAULT_QUOTE);
	}

    /*
     * @param csvLine - single line of file 
     * @param separators 
     * @param customQuote 
     * @return a list of single fields value
     */
	public List<String> parseLine(String csvLine, char separators, char customQuote) {
		List<String> result = new ArrayList<>(); 
		
		if(csvLine == null || csvLine.isEmpty())
			return result; 
		
		if(customQuote == ' ') {
			customQuote = DEFAULT_QUOTE; 
		}
		
		
		if(separators == ' ') {
			separators = DEFAULT_SEPARATOR; 
		}
		
		StringBuffer currentValue = new StringBuffer(); 
		boolean inQuotes = false; 
		boolean startCollectChar = false; 
		boolean doubleQuotesInColumn = false; 
		
		char[] chars = csvLine.toCharArray(); 
		
		for(char ch : chars) {
			
			if(inQuotes) {
				startCollectChar = true; 
				
				if(ch == customQuote) {
					inQuotes = false; 
					doubleQuotesInColumn = false; 
				} else {
					
					if(ch == '\"') {
						if(!doubleQuotesInColumn) {
							currentValue.append(ch); 
							doubleQuotesInColumn = true; 
						}
					} else {
						currentValue.append(ch); 
					}
				}
				
			} else {
				if(ch == customQuote) {
					inQuotes = true; 
					
					if(chars[0] != '"' && customQuote == '\"') {
						currentValue.append('"'); 
					}
					
					if(startCollectChar) {
						currentValue.append('"'); 
					}
				} else if(ch == separators) {
					result.add(currentValue.toString()); 
					
					currentValue = new StringBuffer(); 
					startCollectChar = false; 
					
				} else if(ch == '\r') {
					continue; 
				} else if(ch == '\n') {
					break; 					
				} else {
					currentValue.append(ch); 
				}
			}
			
		}
		
		result.add(currentValue.toString()); 
		return result; 
	}

}
