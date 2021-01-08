package com.redbard.idm.service;

import java.util.List;

import com.redbard.idm.model.UserDTO;

public interface UserService {
	UserDTO createUser(UserDTO user);
	List<UserDTO> getAllUsers(Integer page, Integer size);
	UserDTO getUserById(String id);
	UserDTO updateUser(String id, UserDTO user);
	Long getTotalCount();
	UserDTO authenticateUser(String username, String password);
}
