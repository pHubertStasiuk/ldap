package com.cs.ldapdemo.dao;

import java.util.Collections;
import java.util.List;

public class LdapDAOImpl implements LdapDAO {


    @Override
    public boolean verifyCredentials(String userLogin, Byte[] userPassword) {
        return false;
    }

    @Override
    public List<String> getRoles() {
        return Collections.EMPTY_LIST;
    }
}
