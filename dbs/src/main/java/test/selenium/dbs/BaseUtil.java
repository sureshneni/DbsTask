package test.selenium.dbs;


import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

import org.testng.log4testng.Logger;

import net.bytebuddy.asm.Advice.Return;

public class BaseUtil {

	FileInputStream fileInputStream;
	File file;
	Properties properties;
	public String envFileName = "env.properties";
	
	public String envFilePath;
	public static String configProp="";
	
	
	
	
	public String rootDirPath = System.getProperty("user.dir");
	
	public String getConfigValueFromEnvFile(String propKey) {
		
		try {
			
			envFilePath = System.getProperty("user.dir") + "\\src\\config\\"+ envFileName;
			
			System.out.println("envFilePath : "+envFilePath);
			file = new File(envFilePath);
			fileInputStream = new FileInputStream(file);
			properties = new Properties();
			properties.load(fileInputStream);
			configProp = properties.getProperty(propKey);
			System.out.print("config property: "+configProp);
			
		} catch (Exception exception) {
			
		}
		
		return configProp;
	}
	
	

   
}
