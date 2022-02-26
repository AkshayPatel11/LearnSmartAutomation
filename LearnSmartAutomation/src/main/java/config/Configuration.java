package config;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Configuration {
private static Properties config;
	
	private Configuration() {
	}
	
	public static Properties loadConfiguration() throws IOException {
		config = new Properties();
		InputStream configFile = new FileInputStream(
				new File("E:\\WinnersZoneAutomation\\winnerszone.org\\src\\main\\java\\org\\winnerszone\\resources\\application.properties").getCanonicalPath());
		config.load(configFile);
		return config;
	}
	
	public static String getBaseURL() {
		return config.getProperty("baseURL");
	}
	
	public static String getBrowser() {
		return config.getProperty("browser");
	}
	
	public static String getUserName() {
		return config.getProperty("userName");
	}
	
	public static String getPassword() {
		return config.getProperty("password");
	}
	
	public static String getExcelPath() {
		return config.getProperty("excelPath");
	}
	
	public static String getDriverPath() {
		return config.getProperty("driverPath").trim();
	}
	
	public static String getOTP(){
		return config.getProperty("OTP").trim();
	}
}
