package com.cs.ldapdemo.dao;


import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LdapDAO {

   boolean verifyCredentials(String userLogin, Byte[] userPassword);
   List<String> getRoles();
}

