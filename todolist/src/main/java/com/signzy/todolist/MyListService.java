package com.signzy.todolist;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.signzy.todolist.mongofactory.MongoFactory;

/**
 * MyListService has all the operations performed in the application.
 * 
 * @author Gayatri
 *
 */
@Service("myListService")
@Transactional
public class MyListService {

	static String db_name = "ToDoDataBase", db_collection = "myList";
	private static Logger log = Logger.getLogger(MyListService.class);

	/**
	 * getAll method fetchs all users from the mongo database.
	 * 
	 * @return list
	 */
	public List getAll() {
		List user_list = new ArrayList();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBCursor cursor = coll.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			MyListModel listModel = new MyListModel();
			listModel.setTitle(dbObject.get("title").toString());
			listModel.setDesc(dbObject.get("desc").toString());
			user_list.add(listModel);
		}
		log.debug("Total records fetched from the mongo database are= " + user_list.size());
		return user_list;
	}

	/**
	 * Add method adds a new task to the mongo database.
	 * 
	 * @param mylistModel
	 * @return boolean
	 */
	public Boolean add(MyListModel mylistModel) {
		boolean output = false;
		log.debug("Adding a task in ToDoList" + mylistModel.getTitle());
		try {
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
			DBObject where_query = new BasicDBObject();
			where_query.put("title", mylistModel.getTitle());
			DBObject dbo = coll.findOne(where_query);
			if (Objects.isNull(dbo)) {
				BasicDBObject doc = new BasicDBObject();
				doc.put("title", mylistModel.getTitle());
				doc.put("desc", mylistModel.getDesc());
				coll.insert(doc);
				output = true;
			}
		} catch (Exception e) {
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return output;
	}

	/**
	 * edit method Updates the selected user in the mongo database.
	 * 
	 * @param myListModel
	 * @return Boolean
	 */
	public Boolean edit(MyListModel myListModel) {
		boolean output = false;
		log.debug("Updating the existing task in the mongo database; Entered task title is= " + myListModel.getTitle());
		try {
			BasicDBObject existing = (BasicDBObject) getDBObject(myListModel.getTitle());
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
			BasicDBObject edited = new BasicDBObject();
			edited.put("title", myListModel.getTitle());
			edited.put("desc", myListModel.getDesc());
			coll.update(existing, edited);
			output = true;
		} catch (Exception e) {
			System.out.println("An error has occurred while updating an existing user to the mongo database" + e);
		}
		return output;
	}

	/**
	 * delete method Delete's a user from the mongo database.
	 * 
	 * @param title
	 * @return Boolean
	 */
	public Boolean delete(String title) {
		boolean output = false;
		log.debug("Deleting an existing task from the mongo database; Entered task title is= " + title);
		try {
			BasicDBObject item = (BasicDBObject) getDBObject(title);
			DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
			coll.remove(item);
			output = true;
		} catch (Exception e) {
			output = false;
			log.error("An error occurred while deleting an existing user from the mongo database", e);
		}
		return output;
	}

	/**
	 * getDBObject method Fetches a particular record from the mongo database.
	 * 
	 * @param title
	 * @return DBObject
	 */
	private DBObject getDBObject(String title) {
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBObject where_query = new BasicDBObject();
		where_query.put("title", title);
		return coll.findOne(where_query);
	}

	/**
	 * MyListModel method Fetches a single task details from the mongo database.
	 * 
	 * @param title
	 * @return MyListModel
	 */
	public MyListModel findUserId(String title) {
		MyListModel myListModel = new MyListModel();
		DBCollection coll = MongoFactory.getCollection(db_name, db_collection);
		DBObject where_query = new BasicDBObject();
		where_query.put("title", title);
		DBObject dbo = coll.findOne(where_query);
		myListModel.setTitle(dbo.get("title").toString());
		myListModel.setDesc(dbo.get("desc").toString());
		myListModel.setMode(1);
		return myListModel;
	}
}
