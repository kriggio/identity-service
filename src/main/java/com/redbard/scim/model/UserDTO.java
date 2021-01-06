package com.redbard.scim.model;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.*;

@Data
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "users", itemRelation = "user")
public class UserDTO {

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
