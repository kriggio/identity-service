package com.redbard.scim.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Version;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "emails", itemRelation = "email")
public class EmailDTO {
	
	private String id;
	private String value;
	private String type;
	private Boolean primary;
	
}
