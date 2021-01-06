package com.redbard.scim.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import com.redbard.scim.entity.User;

@Component
public interface UserRepository extends PagingAndSortingRepository<User, String> {
	
}
