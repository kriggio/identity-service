package com.redbard.scim.model;


import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "addresses", itemRelation = "address")
public class AddressDTO {
	
	private String id;
	private String type;
	private String streetAddress;
	private String streetAddress2;
	private String locality;
	private String region;
	private String postalCode;
	private String country;
	private Boolean primary;
	
}
