package resttemplate;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import org.json.simple.JSONObject;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MyApplicationController implements ErrorController {

    // @Autowired
    // private MyApplicationService service;

    @RequestMapping(value="/")
    public String getRoot(){
        return "Hello World";
    }

    @RequestMapping(value="/status")
    public JSONObject getStatus(){

        JSONObject json = new JSONObject();

        json.put("version", ConfigUtil.getConfig("version"));
        json.put("description", ConfigUtil.getConfig("description"));

        return json;
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public JSONObject handleMissingParams(MissingServletRequestParameterException ex) {
        String name = ex.getParameterName();
        JSONObject json = new JSONObject();
        json.put("statusCode", 422);
        json.put("message", name + " parameter is missing");
        return json;

    }

    @RequestMapping("/*")
    public JSONObject invalidUrl(){
        JSONObject json = new JSONObject();
        json.put("statusCode", 404);
        json.put("message", "The url endpoint is not correct. Please try this endpoint -> /getPeople");
        return json;
    }

    @RequestMapping("/error")
    @ResponseBody
    public JSONObject handleError(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        json.put("statusCode", 500);
        json.put("message", "Internal Server error occurred. Most probably the result should have been an empty array. Sadly, there wasn't enough time to check all cases");
        json.put("data", new String[]{});
        return json;

    }

    @Override
    public String getErrorPath() {
        return "/error";
    }


}
