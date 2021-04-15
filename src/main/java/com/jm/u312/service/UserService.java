package com.jm.u312.service;

import com.jm.u312.model.User;

import java.util.List;

public interface UserService {
    List<User> getUsers();
    void addUser(User user, String[] roles);
    void updateUser(User user, String[] roles);
    void deleteUser(int id);
    User getUser(int id);
    User getUser(String email);
}
