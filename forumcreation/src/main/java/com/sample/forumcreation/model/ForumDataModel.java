package com.sample.forumcreation.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ForumDataModel implements Serializable {
	private static final long serialVersionUID = -8986671072588015403L;

	private String name;
	private String email;
	private String comment;
	private String commentId;
	private List<String> replyList;
}
