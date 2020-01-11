package main.resources.job.listing.com.job.listing;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

public class DetailService {
	static String db_name = "JobListing", db_collection = "jobList";

	public List getAll() {
		List job_list = new ArrayList();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			JobListModel jobListModel = new JobListModel();
			jobListModel.setJobTitle(dbObject.get("jobTitle").toString());
			jobListModel.setLocation(dbObject.get("location").toString());
			job_list.add(jobListModel);
		}
		return job_list;
	}

	public void add() {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		JSONParser jsonParser = new JSONParser();
        try (FileReader reader = new FileReader("C:\\\\Users\\\\test\\\\eclipse-workspace\\\\job.listing\\\\src\\\\main\\\\resources\\\\job\\\\listing\\\\com\\\\job\\\\listing\\\\positions.json"))
        {
            Object obj = jsonParser.parse(reader);
            JSONArray joblist = (JSONArray) obj;
            List<JobListModel> jobModelList = new ArrayList<>();
            		joblist.forEach( job -> parseEmployeeObject( (JSONObject) job,jobModelList ) );
            		jobModelList.stream().forEach(jobListModel -> {
            			BasicDBObject doc = new BasicDBObject();
            			doc.put("jobTitle", jobListModel.getJobTitle());
            			doc.put("location", jobListModel.getLocation());
            			coll.insert(doc);
					});
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
	public JobListModel findJob(String jobTitle, String location) {
		JobListModel jobListModel = new JobListModel();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBObject where_query = new BasicDBObject();
		where_query.put("jobTitle", jobTitle);
		where_query.put("location", location);
		DBObject dbo = coll.findOne(where_query);
		jobListModel.setJobTitle(dbo.get("jobTitle").toString());
		jobListModel.setLocation(dbo.get("location").toString());
		return jobListModel;
	}

}
