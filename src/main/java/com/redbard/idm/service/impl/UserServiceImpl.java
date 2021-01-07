/**
 * 
 */
package com.redbard.idm.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.redbard.idm.entity.User;
import com.redbard.idm.model.UserDTO;
import com.redbard.idm.model.exception.CustomException;
import com.redbard.idm.repository.UserRepository;
import com.redbard.idm.security.JwtTokenProvider;
import com.redbard.idm.service.UserService;
import com.redbard.util.RBBeanUtils;

/**
 * @author kriggio
 *
 */
@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;
	private Mapper mapper;
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	public UserServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
		this.userRepository = userRepository;
		this.mapper = new DozerBeanMapper();
		this.jwtTokenProvider = jwtTokenProvider;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public UserDTO createUser(UserDTO user) {

		if (userRepository.existsByUsername(user.getUsername())) {
			throw new CustomException("Username is already in use", HttpStatus.UNPROCESSABLE_ENTITY);
		}
		User userEntity = mapper.map(user, User.class);
		userEntity.setPassword(passwordEncoder.encode(user.getPassword()));
		userEntity = userRepository.save(userEntity);
		UserDTO newUser = mapper.map(userEntity, UserDTO.class);
		
		newUser.setJwtToken(jwtTokenProvider.createToken(newUser.getUsername(), newUser.getRoles()));
		
		return newUser;
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
		User userEntity = mapper.map(user, User.class);
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
