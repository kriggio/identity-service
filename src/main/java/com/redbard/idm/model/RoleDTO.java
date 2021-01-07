package com.redbard.idm.model;

import org.springframework.security.core.GrantedAuthority;

public enum RoleDTO implements GrantedAuthority {
  ROLE_ADMIN, ROLE_CLIENT;

  public String getAuthority() {
    return name();
  }

}
