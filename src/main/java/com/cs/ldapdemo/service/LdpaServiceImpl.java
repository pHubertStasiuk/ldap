package com.cs.ldapdemo.service;


import com.cs.ldapdemo.dao.LdapDTO;
import com.cs.ldapdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LdpaServiceImpl implements LdapService {

    @Autowired
    private LdapDTO ldapDTO;

    @Override
    public boolean verifyCredentials(User user) {
        return this.ldapDTO.verifyCredentials(user);
    }

    @Override
    public List<String> getRoles(User user) {
       return this.ldapDTO.getRoles(user);
    }


}
