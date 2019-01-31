package com.cs.ldapdemo.dao;


import com.cs.ldapdemo.model.User;

import javax.naming.InvalidNameException;
import java.util.List;


public interface LdapDTO {

   boolean verifyCredentials(User user);
   List<String> getRoles(User user) throws InvalidNameException;
   String decodePassword(String string);
}

