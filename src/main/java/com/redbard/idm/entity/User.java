package com.redbard.idm.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
@Table(name = "users")
public class User extends BaseEntity {

	private String externalId;
	@Size(min = 4, max = 255, message = "Minimum username length: 4 characters")
	@Column(unique = true, nullable = false)
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
	@Size(min = 8, message = "Minimum password length: 8 characters")
	private String password;
	@Embedded
	private Name name;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Role> roles;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Email> emails;
	@ElementCollection(fetch = FetchType.EAGER)
	private Set<Address> addresses;
	@ElementCollection(fetch = FetchType.LAZY)
	private Set<PhoneNumber> phoneNumbers;
	@ElementCollection(fetch = FetchType.LAZY)
	private Set<SocialLink> socialLinks;
	
	
}
