package com.sample.forumcreation.model;

import java.io.Serializable;

import lombok.Data;

@Data

public class UserDetailModel implements Serializable {
	private static final long serialVersionUID = -8986671072588015403L;
	
	private String name;
	private String email;
	private String pass;
	private String userId;
}
