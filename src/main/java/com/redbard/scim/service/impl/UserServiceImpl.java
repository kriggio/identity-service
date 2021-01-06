/**
 * 
 */
package com.redbard.scim.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.redbard.scim.entity.User;
import com.redbard.scim.model.UserDTO;
import com.redbard.scim.repository.UserRepository;
import com.redbard.scim.service.UserService;
import com.redbard.util.RBBeanUtils;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author kriggio
 *
 */
@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;
	private Mapper mapper;
	
	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
		this.mapper = new DozerBeanMapper();
	}
	
	
	@Override
	public UserDTO createUser(UserDTO user) {
		User userEntity =  
		    mapper.map(user, User.class);
		userEntity = userRepository.save(userEntity);		 
		return mapper.map(userEntity, UserDTO.class);
	}

	@Override
	public List<UserDTO> getAllUsers(Integer page, Integer size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<User> usersPage = userRepository.findAll(pageable);
		final List<UserDTO> users = new ArrayList<>();
		for (User s : usersPage) {
		    users.add(mapper.map(s, UserDTO.class));
		}
		return users;
	}

	@Override
	public UserDTO getUserById(String id) {
		Optional<User> userEntityOp = userRepository.findById(id);
		if (userEntityOp.isEmpty()) {
			return null;
		}
		User userEntity = userEntityOp.get();
		return mapper.map(userEntity, UserDTO.class);
	}

	@Override
	public UserDTO updateUser(String id, UserDTO user) {
		Optional<User> userEntityOp = userRepository.findById(id);
		if (userEntityOp.isEmpty()) {
			return null;
		}
		User found = userEntityOp.get();
		User userEntity =  
		mapper.map(user, User.class);
		RBBeanUtils<User> util = new RBBeanUtils<>();
		util.copyNonNullProperties(found, userEntity);
		userEntity = userRepository.save(found);
		return mapper.map(userEntity, UserDTO.class);
	}


	@Override
	public Long getTotalCount() {
		return userRepository.count();
	}

}
