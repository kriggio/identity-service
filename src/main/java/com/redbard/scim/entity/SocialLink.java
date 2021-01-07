package com.redbard.scim.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
//@Entity
//@DynamicUpdate
//@Table(name = "social_links")
public class SocialLink {

	private String value;
	private String type;
	
	
}

//@ManyToOne(fetch = FetchType.LAZY)
//@JoinColumn(name = "user_id", nullable=false)
//private User user;