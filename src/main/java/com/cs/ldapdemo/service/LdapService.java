package com.cs.ldapdemo.service;

import java.util.List;

public interface LdapService {

    boolean verifyCredentials(String userLogin, Byte[] userPassword);
    List<String> getRoles();
}
