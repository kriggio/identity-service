package com.redbard.scim.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
//@Entity
//@DynamicUpdate
//@Table(name = "emails")
public class Email {
	
	@Column(unique = true, nullable = false)
	private String value;
	private String type;
	@Column(name = "is_primary")
	private Boolean primary = true;

}
