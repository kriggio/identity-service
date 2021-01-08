package com.redbard.idm.model;

import org.springframework.hateoas.server.core.Relation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
@Relation(collectionRelation = "authResponses", itemRelation = "authResponse")
public class AuthResponseDTO {
	private String jwtToken;
}
