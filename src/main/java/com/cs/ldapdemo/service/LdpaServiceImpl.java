package com.cs.ldapdemo.service;


import com.cs.ldapdemo.dao.LdapDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LdpaServiceImpl implements LdapService {

    @Autowired
    private LdapDAO ldapDAO;

    @Override
    public boolean verifyCredentials(String userLogin, Byte[] userPassword) {
        return this.ldapDAO.verifyCredentials(userLogin, userPassword);
    }

    @Override
    public List<String> getRoles() {
       return this.ldapDAO.getRoles();
    }


}
