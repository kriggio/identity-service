package com.redbard.scim.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.Size;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.*;

@Data
@Entity
@DynamicUpdate
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "user_id", columnDefinition = "VARCHAR(255)")
	private String id;
	@Version
	private Integer version;
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
	private Date createdOn;
	private Date modifiedOn;
	private Date deletedOn;
	@ElementCollection(fetch = FetchType.EAGER)
	private List<Role> roles;
	
	
}
