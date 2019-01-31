package com.cs.ldapdemo.service;

import com.cs.ldapdemo.model.User;

import javax.naming.InvalidNameException;
import java.util.List;

public interface LdapService {

    boolean verifyCredentials(User user);
    List<String> getRoles(User user) throws InvalidNameException;
}
