package com.company.ellRes.service;


import com.company.ellRes.domian.Role;
import com.company.ellRes.domian.User;
import com.company.ellRes.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRepo userRepo;


    public UserService() {
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.userRepo.findByUsername(username);
    }

    public Iterable<User> allUser(){
        return userRepo.findAll();
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public void userSave(User user){
        userRepo.save(user);
    }

    public Iterable<User> userConfirms(Set<Role> roles){
        return userRepo.findAllByRolesIsIn(roles);
    }

}