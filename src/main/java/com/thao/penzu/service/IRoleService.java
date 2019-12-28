package com.thao.penzu.service;

import com.thao.penzu.model.Role;
import com.thao.penzu.model.RoleName;

import java.util.Optional;

public interface IRoleService {
    Optional<Role> findByName(RoleName roleName);
}
