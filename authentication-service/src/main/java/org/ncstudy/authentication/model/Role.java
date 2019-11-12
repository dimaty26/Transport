package org.ncstudy.authentication.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN;

    public String getRoleName() {
        return this.name();
    }

    @Override
    public String getAuthority() {
        return this.name();
    }
}