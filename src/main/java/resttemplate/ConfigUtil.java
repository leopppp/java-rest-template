package resttemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigUtil {

    public static String getConfig(String propertyName) {

        try (InputStream input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("The config.properties file does not exist!");
                return null;
            }

            //load a properties file from class path, inside static method
            prop.load(input);

            //get the property value and return
           return prop.getProperty(propertyName);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }

}