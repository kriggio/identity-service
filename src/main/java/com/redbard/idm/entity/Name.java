package com.redbard.idm.entity;


import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class Name {
	
	private String familyName;
	private String givenName;
	private String middleName;
	private String honorificPrefix;
	private String honorificSuffix;
		
}
