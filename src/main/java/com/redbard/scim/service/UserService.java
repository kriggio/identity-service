package com.redbard.scim.service;

import java.util.List;

import com.redbard.scim.model.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO user);
	List<UserDTO> getAllUsers(Integer page, Integer size);
	UserDTO getUserById(String id);
	UserDTO updateUser(String id, UserDTO user);
	Long getTotalCount();
}
