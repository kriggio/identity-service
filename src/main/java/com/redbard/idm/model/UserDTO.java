package com.redbard.idm.model;

import java.util.Set;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "users", itemRelation = "user")
public class UserDTO {

	private String id;
	private String externalId;
	private String username;
	private String displayName;
	private String nickName;
	private String profileUrl;
	private String userType;
	private String title;
	private String preferredLanguage;
	private String locale;
	private String timezone;
	private Boolean active;
	private String password;
	private String jwtToken;
	private Set<RoleDTO> roles;
	private Set<EmailDTO> emails;
	private Set<AddressDTO> addresses;
	private Set<PhoneNumberDTO> phoneNumbers;
	private Set<SocialLinkDTO> socialLinks;
	private NameDTO name;

}
