package com.taco.services;

import com.taco.models.Role;
import com.taco.models.User;
import com.taco.models.enums.RoleType;
import com.taco.repository.RoleRepository;
import com.taco.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class DbInitializer implements CommandLineRunner {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;

    public DbInitializer(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        Role clientRole = new Role();
        clientRole.setRoleType(RoleType.CLIENT);

        Role adminRole = new Role();
        adminRole.setRoleType(RoleType.ADMIN);

        List<Role> roles = Arrays.asList(clientRole,adminRole);
       // this.roleRepository.saveAll(roles);


        User user = new User("user2",passwordEncoder.encode("1234"),null,"READ","user2@atis.al","test2","User2");
        User user2 = new User("user3",passwordEncoder.encode("1234"),null,"READ","user3@atis.al","test3","User3");
        User user3 = new User("user4",passwordEncoder.encode("1234"),null,"READ","user4@atis.al","test4","User4");

        User admin = new User("admin123",passwordEncoder.encode("1234"),adminRole,"ADMIN","admin123@atis.al","123","Admin");
        List<User> users = Arrays.asList(user,user3,user2);
      //  this.userRepository.saveAll(users);
    }
}
