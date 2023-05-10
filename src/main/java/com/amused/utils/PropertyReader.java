package com.amused.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    // Singleton instance
    private static PropertyReader instance = null;

    private static Logger logger = LoggerFactory.getLogger(PropertyReader.class);

    // Properties to load the property values
    private static Properties prop = new Properties();

    private PropertyReader() {
    }

    /**
     * Method to return the singleton instance of property reader If there is no
     * existing instance an instance is created and returned
     *
     * @return property reader
     */
    public static PropertyReader getInstance() {
        if (instance == null) {
            synchronized (PropertyReader.class) {
                if (instance == null) {
                    instance = new PropertyReader();
                    loadPropertyFile();
                }
            }
        }
        return instance;
    }

    /**
     * Sets the property
     * @param key
     * @param value
     */
    public void setProp(String key, String value) {
        if (instance != null)
            prop.setProperty(key, value);
    }

    /**
     * Method to read and return the property value from the file
     *
     * @param key property name
     * @return property value corresponding to the key
     */
    public String getProperty(String key) {
        return prop.getProperty(key);
    }

    /**
     * Function responsible for loading the property file
     */
    private static void loadPropertyFile() {
        InputStream input = instance.getClass().getClassLoader().getResourceAsStream("application.properties");
        try {
            prop.load(input);
            InputStream propertyFileToLoad = instance.getClass().getClassLoader().getResourceAsStream(prop.getProperty("env")+"-env"+".properties");
            prop.load(propertyFileToLoad);
        } catch (IOException e) {
           logger.error("Error in property reader class {} {}",e.getMessage(), e.getStackTrace());
        }
    }
}
