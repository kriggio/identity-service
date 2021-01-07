package com.redbard.scim.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
//@Entity
//@DynamicUpdate
//@Table(name = "addresses")
public class Address {
	
	private String type;
	private String streetAddress;
	private String streetAddress2;
	private String locality;
	private String region;
	private String postalCode;
	private String country;
	@Column(name = "is_primary")
	private Boolean primary;

}
