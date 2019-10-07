package com.sample.forumcreation.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CommentModel implements Serializable {
	private static final long serialVersionUID = -8986671072588015403L;

	private String name;
	private String userId;
	private String comment;
	private String commentId;
	private String parentId;
	private String reply;

}