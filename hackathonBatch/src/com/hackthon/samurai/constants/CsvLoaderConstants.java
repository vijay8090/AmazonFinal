/**
 * 
 */
package com.hackthon.samurai.constants;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.hackthon.samurai.util.PropertyFileExtractor;

/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 19, 2015
 * @java Version : Java 8
 */
public class CsvLoaderConstants {

	static Logger logger = LogManager.getLogger(CsvLoaderConstants.class.getName());
	
	public static String EXCLUDE_SPECIAL_CHARS="(,), ,{,},;,@,&";

	public static final String SEMICOLON = ";";
	public static final String COMMA_STRING = ",";
	public static final String AMPERSAND_SYMBOL = "&";
	public static final String AT_SYMBOL = "@";
	public static final String UNDERSCORE_STRING = "_";
	public static final String CIRCLE_BRACKET_STRING = "\\(";
	public static final String CIRCLE_BRACKETEND_STRING = "\\)";
	public static final String CURL_BRACKET_STRING = "\\{";
	public static final String CURL_BRACKETEND_STRING = "\\}";

	
	
	
	public static int CSV_PARSER_MONITOR_SLEEP;	
	
	public static String DIR_SUBMIT_FAILED = PropertyFileExtractor.getString("dir.submit.failed");
	public static String DIR_SUBMIT_SUCCESS = PropertyFileExtractor.getString("dir.submit.success");	
	public static String DIR_SCAN = PropertyFileExtractor.getString("dir.scan");
	public static String DIR_SUBMIT_WORKING = PropertyFileExtractor.getString("dir.submit.working");
	public static String FILE_STOP_CSV_LOADER = PropertyFileExtractor.getString("file.stop.monitor");;
	
	public static final String FILE_EXT = PropertyFileExtractor.getString("file.ext");
	
	
	static{

		try{
			
			CSV_PARSER_MONITOR_SLEEP = Integer.parseInt(PropertyFileExtractor.getString("job.sleep.time"));
		}
		catch(Exception ex){
			logger.debug("Error in Parsing sleeper time "+ ex.getMessage());
		}
	}

}
