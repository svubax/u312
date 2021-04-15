package com.jm.u312.service;

import com.jm.u312.model.User;
import com.jm.u312.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
    }
    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }
    @Override
    public void addUser(User user, String... roles){
        if (roles != null)
            user.setRoles(Arrays.stream(roles)
                    .map(roleService::getRoleByName)
                    .collect(Collectors.toSet()));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
    @Override
    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    @Override
    public void updateUser(User user, String... roles) {
        if (roles != null)
            user.setRoles(Arrays.stream(roles)
                    .map(roleService::getRoleByName)
                    .collect(Collectors.toSet()));
        userRepository.save(user);
    }
    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public User getUser(String email) {
        return userRepository.findUserByEmail(email);
    }
}
