package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	Properties prop=new Properties();
	
	public ConfigReader() throws IOException {
		FileInputStream file=new FileInputStream("src/test/resources/config.properties");
	
		prop.load(file);
	}
	
	public String getProperty(String data) {

		return prop.getProperty(data);
		
	}
}
