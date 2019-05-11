package resttemplate;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyApplicationController {

    // Handle / endpoint request
    @RequestMapping(value="/")
    public String getRoot(){
        return "Hello World";
    }

    // Handle /status endpoint request
    @RequestMapping(value="/status")
    public JSONObject getStatus(){

        JSONObject json = new JSONObject();

        json.put("version", ConfigUtil.getConfig("version"));
        json.put("description", ConfigUtil.getConfig("description"));
        json.put("lastcommitsha", ConfigUtil.getLastCommitSha());

        return json;
    }

}
