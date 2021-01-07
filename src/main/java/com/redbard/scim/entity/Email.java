package com.redbard.scim.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@DynamicUpdate
@Table(name = "emails")
public class Email {
	
	@Id
	@GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "VARCHAR(255)")
	private String id;
	@Version
	private Integer version;
	@Column(unique = true, nullable = false)
	private String value;
	private String type;
	@Column(name = "is_primary")
	private Boolean primary = true;
	private Date createdOn;
	private Date modifiedOn;
	private Date deletedOn;
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable=false, updatable=false, nullable=false)
	private User user;
}
