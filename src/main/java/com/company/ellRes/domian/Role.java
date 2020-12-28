package com.company.ellRes.domian;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN,
    RECORTED,
    TABLIN,
    CONFIRM,
    DROP,
    HEAD,
    ACTCREATED;

    private Role() {
    }

    public String getAuthority() {
        return this.name();
    }
}
