package com.taco.services;

import com.taco.models.Role;
import com.taco.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleServices {
    private final RoleRepository roleRepository;

    //creating new role into db
    public Role createRole(Role role){
        roleRepository.save(role);
        return role;
    }

    //getRoleByName
    public Role getRoleByName(String roleName){
        return roleRepository.findByRoleType(roleName);
    }
}
