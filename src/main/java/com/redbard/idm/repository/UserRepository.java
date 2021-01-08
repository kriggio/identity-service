package com.redbard.idm.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.redbard.idm.entity.User;
import com.redbard.idm.entity.projection.UserNamesOnly;

@Component
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	boolean existsByUsername(String username);

	User findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
	
	UserNamesOnly findByUsernameAndActiveTrue(String username);
	UserNamesOnly findByIdAndActiveTrue(String username);
}
