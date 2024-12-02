package com.pando.rest_todo.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class UserDto {
    @NotNull
    @NotEmpty
    private String username;


    @NotNull
    @NotEmpty
    private String password;

    public UserDto(String username, String password){
       this.username = username;
         this.password = password;
    }
    // standard getters and setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}