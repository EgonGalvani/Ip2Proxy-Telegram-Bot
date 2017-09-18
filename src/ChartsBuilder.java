import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.knowm.xchart.BitmapEncoder;
import org.knowm.xchart.BitmapEncoder.BitmapFormat;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;

/**
 * 
 * @author Egon Galvani
 * @brief Class used to create proxy data charts 
 */
public class ChartsBuilder extends CSVParser{
	// 	
    private HashMap<String, AtomicInteger> typeCounter = new HashMap<>(); 
    
    //
    private HashMap<String, AtomicInteger> locationCounter = new HashMap<>(); 	
    
    // fields column position inside the csv file 
    private final static int TYPE_POSITION = 2; 
    private final static int COUNTRY_POSITION = 3; 
    
    // chart types 
    public enum CHART_TYPE {
    	PROXY_TYPE, 
    	PROXY_LOCATION, 
    	BOTH
    }
    
	public ChartsBuilder(String csvFilePath) {
		super(csvFilePath);
	}
	
	/*
	 * @see CSVParser#parsingAlgorithm(java.util.List)
	 */
	@Override
	protected void parsingAlgorithm(List<String> line) {
		try {
			String proxyType = line.get(TYPE_POSITION); 
			String proxyCountry = line.get(COUNTRY_POSITION); 
			
			if(typeCounter.containsKey(proxyType))
				typeCounter.get(proxyType).incrementAndGet(); 
			else typeCounter.put(proxyType, new AtomicInteger(1)); 
			
			if(locationCounter.containsKey(proxyCountry))
				locationCounter.get(proxyCountry).incrementAndGet(); 
			else locationCounter.put(proxyCountry, new AtomicInteger(1)); 
		}catch(IndexOutOfBoundsException e) {
			e.printStackTrace();
		}		
	}

	/**
	 * 
	 * @param requestedChart The type of the requested chart(s)  
	 */
	public void createChart(final CHART_TYPE requestedChart) {		
		if(requestedChart == CHART_TYPE.PROXY_TYPE || requestedChart == CHART_TYPE.BOTH) {
    		PieChart proxyTypeChart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();
    		
    		for(Map.Entry<String, AtomicInteger> entry : typeCounter.entrySet()) {
    			proxyTypeChart.addSeries(entry.getKey(), entry.getValue()); 
    		}
    			    	    
    	    try {
				BitmapEncoder.saveBitmap(proxyTypeChart, BotSettings.TypeChartPath, BitmapFormat.PNG);
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}

		if(requestedChart == CHART_TYPE.PROXY_LOCATION || requestedChart == CHART_TYPE.BOTH) {
			PieChart locationTypeChart = new PieChartBuilder().width(800).height(600).title(getClass().getSimpleName()).build();
			int other = 0; 
    		int all = 0; 
    	    		
    		for(Map.Entry<String, AtomicInteger> entry : locationCounter.entrySet()) {
    			all += Integer.parseInt(entry.getValue().toString()); 
    		}
    		
    		// Inside the location chart are visible only the countries with a percentage bigger than 2%  
    		// Countries with a percentage smaller than 2% are inserted into "Other" section 
    		int min = all/100*2; 
    		for(Map.Entry<String, AtomicInteger> entry : locationCounter.entrySet()) {
    			int entryValue = Integer.parseInt(entry.getValue().toString()); 
    			
    			if(entryValue < min) 
    				other += entryValue; 
    			else locationTypeChart.addSeries(entry.getKey(), entry.getValue()); 
    		}
    			    	    
    		locationTypeChart.addSeries("Other", other); 
    		
    	    try {
				BitmapEncoder.saveBitmap(locationTypeChart, BotSettings.LocationChartPath, BitmapFormat.PNG);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}	
}
