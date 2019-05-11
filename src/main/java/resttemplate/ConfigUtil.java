package resttemplate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config util class to read application properties.
 *
 */
public class ConfigUtil {

    public static String getConfig(String propertyName) {
    	InputStream input = null;
        try {
        	input = ConfigUtil.class.getClassLoader().getResourceAsStream("config.properties");
            Properties prop = new Properties();

            if (input == null) {
                System.out.println("The config.properties file does not exist!");
                return null;
            }

            prop.load(input);

            //get the property value and return
           return prop.getProperty(propertyName);

        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
        	if(input != null) {
            	try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
        	}
        }
        
        return null;
    }
    
    public static String getLastCommitSha() {
    	BufferedReader br = null;
    	try {
    		// Read the last commit sha value from the lastCommitSha file which is created during travis ci building process
    		br = new BufferedReader(new FileReader("lastCommitSha"));
    	    String line = br.readLine();

    	    return line.toString();    
    	} catch (Exception ex) {
    		ex.printStackTrace();
    	} finally {
    		if (br != null) {
    			try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
    		}
    	} 
    	
    	return null;
    }

}