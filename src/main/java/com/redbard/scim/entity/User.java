package com.redbard.scim.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

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
	private String userName;
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
	private Date createdOn;
	private Date modifiedOn;
	private Date deletedOn;
	
	
}
