package com.sample.forumcreation;

import java.util.ArrayList;
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
import com.sample.forumcreation.model.CommentModel;
import com.sample.forumcreation.model.UserDetailModel;
import com.sample.forumcreation.mongofactory.MongoFactory;

@Service("forumCollectorService")
@Transactional
public class ForumCollectorService {
	static String db_name = "ForumCreateDataBase", db_user_collection = "userDetail",
			db_comment_collection = "commentData";
	static DBCollection userCol = MongoFactory.getCollection(db_name, db_user_collection);
	static DBCollection forumCol = MongoFactory.getCollection(db_name, db_comment_collection);
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
		if (Objects.nonNull(dbo)) {
			userDetailModel.setName(dbo.get("name").toString());
			userDetailModel.setUserId(dbo.get("userId").toString());
		}
		return dbo;
	}

	public boolean registerUser(UserDetailModel userDetailModel) {
		boolean result = false;
		DBObject dbo = basicUserData(userDetailModel, false);
		String userID = UUID.randomUUID().toString();
		try {
			if (Objects.isNull(dbo)) {
				BasicDBObject doc = new BasicDBObject();
				doc.put("name", userDetailModel.getName());
				doc.put("email", userDetailModel.getEmail());
				doc.put("pass", userDetailModel.getPass());
				doc.put("userId", userID);
				userCol.insert(doc);
				result = true;
			}
		} catch (Exception e) {
			log.error("An error occurred while saving a new user to the mongo database", e);
		}
		return result;
	}

	public List<CommentModel> getCommentDetails() {
		List<CommentModel> commentModelList = new ArrayList();
		DBCursor cursor = forumCol.find();
		while (cursor.hasNext()) {
			DBObject dbObject = cursor.next();
			CommentModel commentModel = new CommentModel();
			commentModel.setName(dbObject.get("name").toString());
			commentModel.setUserId(dbObject.get("userId").toString());
			commentModel.setComment(dbObject.get("comment").toString());
			commentModel.setCommentId(dbObject.get("commentId").toString());
			commentModel.setParentId(dbObject.get("commentId").toString());
			if (Objects.nonNull(dbObject.get("reply")))
				commentModel.setReply(dbObject.get("reply").toString());
			commentModelList.add(commentModel);
		}
		return commentModelList;
	}

	public void addComment(CommentModel commentModel) {
		BasicDBObject doc = new BasicDBObject();
		String commentId = UUID.randomUUID().toString();
		doc.put("name", commentModel.getName());
		doc.put("userId", commentModel.getUserId());
		doc.put("comment", commentModel.getComment());
		doc.put("commentId", commentId);
		doc.put("parentId", commentId);
		forumCol.insert(doc);
	}

	public void addReply(CommentModel commentModel, String reply) {
		DBObject where_query = new BasicDBObject();
		where_query.put("name", commentModel.getName());
		DBObject existing = forumCol.findOne(where_query);
		BasicDBObject edited = new BasicDBObject();
		edited.put("reply", reply);
		edited.put("name", existing.get("name").toString());
		edited.put("userId", existing.get("userId").toString());
		edited.put("comment", existing.get("comment").toString());
		edited.put("commentId", existing.get("commentId").toString());
		forumCol.update(existing, edited);
	}

}
