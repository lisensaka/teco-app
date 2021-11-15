package com.taco.models.dtos;

import com.taco.models.Role;
import com.taco.models.User;
import com.taco.models.enums.RoleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
        private RoleType roleType;

        public static RoleDto fromRoleDto(Role role){
                RoleDto roleDto = new RoleDto();
                roleDto.roleType = role.getRoleType();
                return roleDto;
        }

}
