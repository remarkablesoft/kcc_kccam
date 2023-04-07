//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.remarkablesoft.site.kccam.web;

import com.remarkablesoft.framework.util.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public final class ApplicationPropertiesUtils2 {
		
		private static final String APPLICATION_FILE_PATH = "/envConfig/";
		private static final String SYSTEM_PROPERTIES_FILE_NAME = "/system";
		private static final String APPLICATION_PROPERTIES_FILE_NAME = "/application";
		private static final String APPLICATION_PROPERTIES_FILE_EXT = ".properties";
		static Properties applicationProperties = new Properties();
		static OutputStream output = null;
		
		public ApplicationPropertiesUtils2() {
		}
		
		public static void setSystemPropertis() {
				if (applicationProperties != null) {
						Iterator var0 = applicationProperties.keySet().iterator();
						
						while(var0.hasNext()) {
								Object key = var0.next();
								System.setProperty(String.valueOf(key), String.valueOf(applicationProperties.get(key)));
						}
						
				}
		}
		
		public static void setProperties(Properties properties) {
				applicationProperties = properties;
		}
		
		public static String getValue(String key) {
				return applicationProperties == null ? "" : applicationProperties.getProperty(key);
		}
		
		public static List<String> getListValue(String key) {
				return (List)(applicationProperties != null && !StringUtils.isEmpty(key) ? StringUtils.arrayToCommaDelimitedList(getValue(key)) : new ArrayList());
		}
		
		public static int getIntValue(String key) {
				return getIntValue(key, 0);
		}
		
		public static int getIntValue(String key, int defaultValue) {
				if (applicationProperties == null) {
						return defaultValue;
				} else {
						String result = applicationProperties.getProperty(key, String.valueOf(defaultValue));
						return Integer.parseInt(result);
				}
		}
		
		public static String getValue(String key, String defaultValue) {
				String result = getValue(key);
				return StringUtils.isNotEmpty(result) ? result : defaultValue;
		}
		
		public static boolean getBooleanValue(String key) {
				return applicationProperties != null && applicationProperties.getProperty(key) != null ? applicationProperties.getProperty(key).equalsIgnoreCase("true") : false;
		}
		
		public String[] getKeyValues() {
				List<String> names = new LinkedList();
				Iterator keys = applicationProperties.keySet().iterator();
				
				while(keys.hasNext()) {
						String key = (String)keys.next();
						String value = applicationProperties.getProperty(key);
						names.add(key + "=" + value);
				}
				
				Collections.sort(names);
				return (String[])names.toArray(new String[names.size()]);
		}
		
		static {
				try {
						String profile = System.getProperty("spring.profiles.active", "local");
						String bootConfig = System.getProperty("spring.config.location");
						String systemFilePath = "/system.properties";
						if (StringUtils.isNotEmpty(systemFilePath) && StringUtils.isEmpty(bootConfig)) {
								systemFilePath = "/envConfig/" + profile + "/system" + "-" + profile + ".properties";
								applicationProperties.load(ApplicationPropertiesUtils2.class.getResourceAsStream(systemFilePath));
						}
						
						if (StringUtils.isNotEmpty(bootConfig)) {
								systemFilePath = "/envConfig/" + profile + "/application" + "-" + profile + ".properties";
								
								FileInputStream fis = new FileInputStream( new File( systemFilePath) );
								applicationProperties.load( fis);
						}
						
						setSystemPropertis();
				} catch (Exception var3) {
				}
				
		}
}
