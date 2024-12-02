package com.pando.rest_todo.service;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

import javax.sql.DataSource;

@Service
public class UserService {

//    public UserDetailsService userDetailService(DataSource dataSource) {
//
//        var user = User.withUsername("ibo")
//                .password("123")
//                .passwordEncoder(str -> passwordEncoder().encode(str))
//                .roles("USER")
//                .build();
//
//        var admin = User.withUsername("admin")
//                .password("admin123")
//                .passwordEncoder(str -> passwordEncoder().encode(str))
//                .roles("ADMIN", "USER")
//                .build();
//
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        jdbcUserDetailsManager.createUser(user);
//        jdbcUserDetailsManager.createUser(admin);
//
//        return jdbcUserDetailsManager;
//    }

    //to create new user into DB
    public UserDetails createNewUser(
            String username,
            String password,
            String role
    ) {

        System.out.println("createNewUser Service");
        var user = User.withUsername(username)
                .password(password)
                .passwordEncoder(str -> passwordEncoder().encode(str))
                .roles(role)
                .build();
        // write user to DB
//        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource());
//        jdbcUserDetailsManager.createUser(user);

        System.out.println("User created=> " + user.getUsername() + " with role " + role);
        return user;
    }

    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
