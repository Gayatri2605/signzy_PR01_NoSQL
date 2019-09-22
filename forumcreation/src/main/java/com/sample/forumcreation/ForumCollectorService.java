package com.sample.forumcreation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.sample.forumcreation.model.ForumDataModel;
import com.sample.forumcreation.model.UserDetailModel;
import com.sample.forumcreation.mongofactory.MongoFactory;

@Service("forumCollectorService")
@Transactional
public class ForumCollectorService {
	static String db_name = "ForumCreateDataBase", db_user_collection = "userDetail",db_forum_collection = "forumData";
	static DBCollection userCol = MongoFactory.getCollection(db_name, db_user_collection);
	static DBCollection forumCol = MongoFactory.getCollection(db_name, db_forum_collection);
	private static Logger log = Logger.getLogger(ForumCollectorService.class);

	public boolean authenticateUser(UserDetailModel userDetailModel) {
		DBObject dbo = basicUserData(userDetailModel, true);
		if (Objects.nonNull(dbo))
			return true;
		return false;
	}
	
	private DBObject basicUserData(UserDetailModel userDetailModel, boolean passFlag) {
		DBObject where_query = new BasicDBObject();
		where_query.put("email", userDetailModel.getEmail());
		if (passFlag)
			where_query.put("pass", userDetailModel.getPass());
		DBObject dbo = userCol.findOne(where_query);
		if(Objects.nonNull(dbo))
			userDetailModel.setName(dbo.get("name").toString());
		return dbo;
	}

	public boolean registerUser(UserDetailModel userDetailModel) {
		boolean result = false;
		DBObject dbo = basicUserData(userDetailModel, false);
		try {
			if (Objects.isNull(dbo)) {
				BasicDBObject doc = new BasicDBObject();
				doc.put("name", userDetailModel.getName());
				doc.put("email", userDetailModel.getEmail());
				doc.put("pass", userDetailModel.getPass());
				userCol.insert(doc);
				result = true;
			}
		} catch (Exception e) {
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return result;
	}
	

	public List<ForumDataModel> getForumDetails() {
		List<ForumDataModel> forumDataModelList = new ArrayList();
		DBCursor cursor = forumCol.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			ForumDataModel forumDataModel = new ForumDataModel();
			forumDataModel.setName(dbObject.get("name").toString());
			forumDataModel.setEmail(dbObject.get("email").toString());
			forumDataModel.setComment(dbObject.get("comment").toString());
			forumDataModel.setCommentId(dbObject.get("commentId").toString());
			forumDataModel.setReplyList((List<String>)dbObject.get("replyList"));
			forumDataModelList.add(forumDataModel);
		}
		return forumDataModelList;
	}

	public void addComment(ForumDataModel forumDataModelList) {
		BasicDBObject doc = new BasicDBObject();
		doc.put("comment", forumDataModelList.getComment());
		doc.put("commentId", UUID.randomUUID().toString());
		doc.put("name", forumDataModelList.getName());
		doc.put("email", forumDataModelList.getEmail());
		forumCol.insert(doc);
	}

	public void addReply(ForumDataModel forumDataModelList, String reply) {
		DBObject where_query = new BasicDBObject();
		where_query.put("name", forumDataModelList.getName());
		where_query.put("email", forumDataModelList.getEmail());
		DBObject existing = forumCol.findOne(where_query);
		BasicDBObject edited = new BasicDBObject();
		if (Objects.isNull(existing.get("replyList")))
			edited.put("replyList", Arrays.asList(reply));
		else {
			List<String> newReplyList = (List<String>) existing.get("replyList");
			newReplyList.addAll(Arrays.asList(reply));
			edited.put("replyList", newReplyList);
		}
		edited.put("name", existing.get("name").toString());
		edited.put("email", existing.get("email").toString());
		edited.put("comment", existing.get("comment").toString());
		edited.put("commentId", existing.get("commentId").toString());
		forumCol.update(existing, edited);
	}

}
