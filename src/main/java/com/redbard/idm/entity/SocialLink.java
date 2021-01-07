package com.redbard.idm.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class SocialLink {

	private String value;
	private String type;
	
}

