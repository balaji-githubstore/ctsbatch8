package com.google.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropUtils {

	
	
	public static String getValueUsingKey(String fileDetails,String key) throws IOException
	{
		//properties
        FileInputStream file=new FileInputStream(fileDetails);
		Properties prop=new Properties();
		prop.load(file);
		String value = prop.getProperty(key);
		
		return value;
	}
	
	
	
}
