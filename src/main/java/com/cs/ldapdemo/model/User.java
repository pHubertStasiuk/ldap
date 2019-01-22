package com.cs.ldapdemo.model;

import java.util.Arrays;
import java.util.List;

public class User {

    private String login;
    private Byte[] password;
    private List<String> roles;

    public User(String login, Byte[] password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Byte[] getPassword() {
        return password;
    }

    public void setPassword(Byte[] password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password=" + Arrays.toString(password) +
                ", roles=" + roles +
                '}';
    }
}
