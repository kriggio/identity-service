package com.redbard.scim.controller;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.PagedModel.PageMetadata;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redbard.scim.controller.assembler.UserModelAssembler;
import com.redbard.scim.model.UserDTO;
import com.redbard.scim.model.exception.ResourceNotFoundException;
import com.redbard.scim.profiler.Profile;
import com.redbard.scim.service.UserService;

import lombok.Setter;

@RestController
@Setter
public class UserController {

	private UserModelAssembler assembler;
	private UserService userService;
	
	@Autowired
	public UserController(UserModelAssembler userModelAssembler, UserService userService) {
		this.assembler = userModelAssembler;
		this.userService = userService;
	}
	
	@Profile("UserController#getAllUsers")
	@GetMapping("/users")
	public PagedModel<EntityModel<UserDTO>> getAllUsers(
			@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
			@RequestParam(name = "size", required = false, defaultValue = "20") Integer size) {
		
		List<UserDTO> userDTOs = userService.getAllUsers(page, size);

		List<EntityModel<UserDTO>> users = new ArrayList<>();
		
		for (UserDTO user : userDTOs) {
			users.add(assembler.toModel(user));
		}
							   
		return new PagedModel<>(users, new PageMetadata(size, page, userService.getTotalCount()),
			    linkTo(methodOn(UserController.class).getAllUsers(page, size)).withSelfRel(),
			    linkTo(methodOn(UserController.class).getAllUsers(page + 1, size)).withRel("next"));
	}

	@Profile("UserController#getUserById")
	@GetMapping("/users/{id}")
	public EntityModel<UserDTO> getUserById(@PathVariable String id) {
		
		UserDTO user = userService.getUserById(id);
		
		if (user == null) {
			throw new ResourceNotFoundException(String.format("%s not found", id));
		}
		
		return assembler.toModel(user);
	}
	
	@Profile("UserController#createUser")
	@PostMapping("/users")
	public ResponseEntity<EntityModel<UserDTO>> createUser(@RequestBody UserDTO user) {
		EntityModel<UserDTO> entityModel = assembler.toModel(userService.createUser(user));
		
		return ResponseEntity //
				.created(entityModel.getRequiredLink("self").toUri()) 
				.body(entityModel);
	}
	
	@Profile("UserController#updateUser")
	@PutMapping("/users/{id}")
	public EntityModel<UserDTO> updateUser(@PathVariable String id, @RequestBody UserDTO user) {
		
		UserDTO user2 = userService.updateUser(id, user);
		
		if (user2 == null) {
			throw new ResourceNotFoundException(String.format("%s not found", id));
		}
		return assembler.toModel(user2);
	}
		
	
}
