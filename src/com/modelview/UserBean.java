package com.modelview;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserBean {

	private int id;
	private String user_name;
	private String gender;
	private String userid;

//public UserBean(String userName, String userId, String gender) {
//    this.user_name = userName;
//    this.userid = userId;
//    this.gender = gender;
//}
//
	public UserBean(String name, String gender, String userid) {
		super();
		this.user_name = name;
		this.gender = gender;
		this.userid = userid;
	}

}
