/**
 * Title : PropertyFileExtractor.java
 * Description : This Class is to 
 * Copyright: Ford Motor Company
 */
package com.hackthon.samurai.util;

/**
 * @project	:DataLoader
 * @author	:Sreedharan C (csreedh1)
 * @date	:Oct 29, 2013
 * @since 	:JDK 1.5
 */

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class PropertyFileExtractor {
	private static final String BUNDLE_NAME = "CSVLoaderProp"; 

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private PropertyFileExtractor() {
	}

	public static String getString(String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return "";
		}
	}
}
