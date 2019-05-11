package resttemplate;

import resttemplate.*;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ConfigUtilTest{

    @Test
    public void getVersionTest() throws Exception{
        String actual = ConfigUtil.getConfig("version");

        String expected = "1.0";
        Assert.assertEquals(expected, actual);
    }

}