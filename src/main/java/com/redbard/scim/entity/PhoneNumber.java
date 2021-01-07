package com.redbard.scim.entity;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
//@Entity
//@DynamicUpdate
//@Table(name = "phone_numbers")
public class PhoneNumber {

	private String value;
	private String type;
	
}
