package com.company.ellRes.repository;

import com.company.ellRes.domian.Role;
import com.company.ellRes.domian.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);

    Iterable<User> findAllByRolesIsIn(Set<Role> roles);


}
