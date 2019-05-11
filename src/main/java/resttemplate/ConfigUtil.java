package resttemplate;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Config util class to read application properties.
 *
 */
public class ConfigUtil {

	/**
	 * Get property value from config file.
	 * @param propertyName the property name
	 * @return the specified property value
	 */
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
    
    /**
     * Get the last commit SHA value.
     * @return the last commit SHA value
     */
    public static String getLastCommitSha() {
    	// Read the last commit SHA value from the environment variable which is set during travis ci building process
        String sha = System.getenv("LAST_COMMIT_SHA");

    	return sha != null ? sha : "e28d5a5a215d4aca35666c93f4b31b5c29ea9c72";
    }
}