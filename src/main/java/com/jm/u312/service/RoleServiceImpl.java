package com.jm.u312.service;

import com.jm.u312.repository.RoleRepository;
import com.jm.u312.model.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }
    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }
    @Override
    public Role getRoleByName(String roleName) {
        return roleRepository.findRoleByName(roleName);
    }
}
