package com.redbard.scim.model;

import lombok.*;

@Data
public class User {

	private String id;
	private String externalId;
	private String userName;
	//private Name name;
	private String displayName;
	private String nickName;
	private String profileUrl;
	//private Email[] emails;
	//private Address[] addresses;
	//private PhoneNumber[] phoneNumbers;
	//private Photo[] photos;
	private String userType;
	private String title;
	private String preferredLanguage;
	private String locale;
	private String timezone;
	private Boolean active;
	private String password;
	//private Group[] groups;
	//private X509Certificate[] x509Certificates;
	//private Meta meta;
		
}
