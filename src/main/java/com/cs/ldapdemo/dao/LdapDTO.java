package com.cs.ldapdemo.dao;


import com.cs.ldapdemo.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface LdapDTO {

   boolean verifyCredentials(User user);
   List<String> getRoles(User user);
}

