package com.redbard.scim.controller.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import com.redbard.scim.controller.UserController;
import com.redbard.scim.model.UserDTO;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserDTO, EntityModel<UserDTO>> {

	  @Override
	  public EntityModel<UserDTO> toModel(UserDTO user) {

	    return new EntityModel<>(user,
	      linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
	      linkTo(methodOn(UserController.class).getAllUsers(null, null)).withRel("users"));
	  }
}


