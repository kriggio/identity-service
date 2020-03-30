package com.redbard.scim.controller.assembler;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


import com.redbard.scim.controller.UserController;
import com.redbard.scim.model.User;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<User, EntityModel<User>> {

	  @Override
	  public EntityModel<User> toModel(User user) {

	    return new EntityModel<>(user,
	      linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel(),
	      linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
	  }
}


