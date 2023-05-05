package com.check24.services;

import com.check24.exceptions.NotFoundException;
import com.check24.model.User;
import com.check24.repository.UserRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public void createUser(User user) {
        userRepo.save(user);
    }

    public User getUser(String name) {
        return this.findUser(name);
    }

    public User findUser(String username) {
        var user = userRepo.findById(username);
        if (user.isEmpty()) {
            throw new NotFoundException("No user found");
        }
        return user.get();
    }

    public List<User> getAllUsers() {
        var users = userRepo.findAll();
        if (users.isEmpty()) {
            throw new NotFoundException("No films found");
        }

        return users;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.findUser(username);
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                new ArrayList<>());
    }
}
