package com.vytrack.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationReader {
    private static Properties configFile;
    static {
        try {
            FileInputStream file=new FileInputStream("configuration.properties");
            configFile=new Properties();
            configFile.load(file);
        }catch (IOException e){
            System.out.println("Failed to load properties file!" );
            e.printStackTrace();
        }
    }
    public  static  String getProperty(String key){
        return configFile.getProperty(key);
    }
}
