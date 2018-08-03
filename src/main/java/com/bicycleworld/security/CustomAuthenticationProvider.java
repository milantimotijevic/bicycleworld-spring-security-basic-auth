package com.bicycleworld.security;

import com.bicycleworld.dao.UserDao;
import com.bicycleworld.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    UserDao userDao;

    @Override
    public Authentication authenticate(Authentication authentication) {

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        User user = userDao.getOneByUsername(name);

        if(user == null || !user.getPassword().equals(password)) {
            return null;
        }


        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + user.getRole()); //mind the format, it's ROLE_MyRole
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(grantedAuthority);
        return new UsernamePasswordAuthenticationToken(name, password, authorities);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}