package com.cs.ldapdemo.model;

import java.util.List;

public class User {

    private String login;
    private String password;
    private List<String> memberOf;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getMemberOf() {
        return memberOf;

    }

    public void setMemberOf(List<String> memberOf) {
        this.memberOf = memberOf;

    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", memberOf=" + memberOf +
                '}';
    }
}
