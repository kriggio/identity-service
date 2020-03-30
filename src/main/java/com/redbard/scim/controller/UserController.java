package com.redbard.scim.controller;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.redbard.scim.controller.assembler.UserModelAssembler;
import com.redbard.scim.model.User;

import lombok.Setter;

@RestController
@Setter
public class UserController {

	private UserModelAssembler assembler;
	
	public UserController(UserModelAssembler userModelAssembler) {
		this.assembler = userModelAssembler;
	}
	
	@GetMapping("/users")
	public CollectionModel<EntityModel<User>> getAllUsers() {
		User user = new User();
		user.setId("1");
		user.setNickName("RedBard");
		
		List<EntityModel<User>> users = new ArrayList<>();
		
		users.add(assembler.toModel(user));
					   
		return new CollectionModel<>(users,
			    linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
	}

	@GetMapping("/users/{id}")
	public EntityModel<User> getUserById(@PathVariable String id) {
		User user = new User();
		user.setId(id);
		user.setNickName("RedBard");
		return assembler.toModel(user);
	}
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		user.setId("new-user");
		user.setNickName("RedBard");
		return user;
	}
	
	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable String id, @RequestBody User user) {
		user.setId(id);
		user.setNickName("RedBard");
		return user;
	}
		
	
}
