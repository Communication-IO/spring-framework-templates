package com.ahlquist.util;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A singleton class that aids in Configuration
 * the file should be created at ~/.chat_test/rtest.properties
 * 
 * @author douglasahlquist
 */
public class TestAppConfig {
	
	private Properties props = new Properties();
	
	private static volatile TestAppConfig instance = null;
	private static Object mutex = new Object();
	
	private TestAppConfig() throws IOException {
		super();
		String configPath = System.getProperty("user.home") 
				+ File.separator + ".chat_test" + File.separator + "test.properties";
		
		final File initialFile = new File(configPath);
	    final InputStream stream = 
	        new DataInputStream(new FileInputStream(initialFile));
		props.load(stream);
	}

	public static TestAppConfig getInstance() throws IOException {
		TestAppConfig result = instance;
		if (result == null) {
			synchronized (mutex) {
				result = instance;
				if (result == null)
						instance = result = new TestAppConfig();
			}
		}
		return result;
	}
	
	public String getValue(final String key) {
		return props.getProperty(key);
	}
	
	public String getValue(final String key, final String defaultVal) {
		return props.getProperty(key,defaultVal);
	}
}
