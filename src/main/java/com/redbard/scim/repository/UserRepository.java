package com.redbard.scim.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.redbard.scim.entity.User;

@Component
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
	boolean existsByUsername(String username);

	User findByUsername(String username);

	@Transactional
	void deleteByUsername(String username);
}
