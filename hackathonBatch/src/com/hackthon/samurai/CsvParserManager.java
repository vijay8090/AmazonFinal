
package com.hackthon.samurai;

import java.io.IOException;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

import com.hackthon.samurai.util.DateUtil;


/**
 * 
 * @author Vijayakumar Anbu
 * @project : Hackathon
 * @Date :Sep 19, 2015
 * @java Version : Java 8
 */

public class CsvParserManager {

	static Logger logger = LogManager.getLogger(CsvParserManager.class.getName());
	
	static {
		String targetLog = "CsvParser_"+DateUtil.getCurrentDateTime(DateUtil.YYYYMMDD_BLANK+DateUtil.HHMMSS)+".log";

		FileAppender apndr;
		try {
			
			//%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
			//%d %-5p [%c{1}] %m%n
			apndr = new FileAppender(new PatternLayout("%d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n"),targetLog,true);
			apndr.setAppend(true);
			LogManager.getRootLogger().addAppender(apndr);
			LogManager.getRootLogger().setLevel((Level) Level.ALL);
			//LogManager.getRootLogger().setLevel((Level) Level.DEBUG);
			//LogManager.getRootLogger().setLevel((Level) Level.ERROR);
			//LogManager.getRootLogger().setLevel((Level) Level.INFO);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
	}

	/**
	 * This Method is  main
	 * @param args
	 */
	public static void main(String[] args) {
		new CsvLoaderBatchManagerThread();
	}
}