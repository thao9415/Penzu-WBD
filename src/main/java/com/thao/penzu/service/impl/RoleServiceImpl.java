package com.thao.penzu.service.impl;

import com.thao.penzu.model.Role;
import com.thao.penzu.model.RoleName;
import com.thao.penzu.repository.IRoleRepository;
import com.thao.penzu.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleRepository roleRepository;
    @Override
    public Optional<Role> findByName(RoleName roleName) {
        return roleRepository.findByRoleName(roleName);
    }
}
