package com.jm.u312.service;

import com.jm.u312.model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByName(String roleName);
}
