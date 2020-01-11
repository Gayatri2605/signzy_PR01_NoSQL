package main.resources.job.listing.com.job.listing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


public class SampleJson {
public static void main(String[] args) {
	JSONParser jsonParser = new JSONParser();
    try (FileReader reader = new FileReader("C:\\Users\\test\\eclipse-workspace\\job.listing\\src\\main\\resources\\job\\listing\\com\\job\\listing\\positions.json"))
    {
        Object obj = jsonParser.parse(reader);
        JSONArray joblist = (JSONArray) obj;
        List<JobListModel> jobModelList = new ArrayList<>();
        		joblist.forEach( job -> parseEmployeeObject( (JSONObject) job,jobModelList ) );
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ParseException e) {
        e.printStackTrace();
    }
}
private static void parseEmployeeObject(JSONObject employee,List<JobListModel> jobModelList) 
{
	JobListModel model = new JobListModel();
    String jobTitle = (String) employee.get("title");    
    String location = (String) employee.get("location");  
    
    model.setJobTitle(jobTitle);
    model.setLocation(location);
     
}
}
