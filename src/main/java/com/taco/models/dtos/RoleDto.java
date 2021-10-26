package com.taco.models.dtos;

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
        private Long id;
        private RoleType roleType;
//        private List<User> users;

}
