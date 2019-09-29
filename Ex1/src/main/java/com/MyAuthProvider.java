package com;

import com.Beans.CheckUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.Collections;

/**
 * Created by Cat on 29.09.2019.
 */
@Component
public class MyAuthProvider implements AuthenticationProvider {
    private ApplicationContext context;
    @Autowired
    public void setContextHolder(ApplicationContext context) {
        this.context = context;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        CheckUser check = context.getBean(CheckUser.class);
        try {
            boolean isUser = check.isSuchUser(name, password);
            if(isUser) {
                return new UsernamePasswordAuthenticationToken(name, password, Collections.emptyList());
            } else {
                throw new BadCredentialsException("No such user");
            }
        } catch(SQLException e) {
            throw new BadCredentialsException("Problem with database");
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadCredentialsException("Error while authenticate");
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //обязательно true
        return true;
    }
}
