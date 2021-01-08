package com.redbard.idm.entity.projection;

import java.util.Set;

import com.redbard.idm.entity.Role;

public interface UserNamesOnly {

	String getUsername();
	String getDisplayName();
	Set<Role> getRoles();
	
}
