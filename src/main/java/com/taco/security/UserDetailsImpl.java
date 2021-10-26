package com.taco.security;

import com.taco.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private User user;

    public UserDetailsImpl(User user) {
        this.user = user;
    }


    //kjo metod na kthen authorities/permissionat qe do ket useri i loguar ne app
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //ktu po krijojme nje list qe do mbaj roles/authorities te userit
        List<GrantedAuthority> authorities = new ArrayList<>();
     //   String role = String.valueOf(this.user.getRole().getRoleType());
     //   authorities.add(role);

        //nga attributi i listes se authorities qe kemi tek klasa user marrim authorities qe ka ky user dhe i shtojme tek authorities
        this.user.getAuthoritisList().forEach(a ->{
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(a);
            authorities.add(grantedAuthority);
        });

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled()==1;
    }
}
