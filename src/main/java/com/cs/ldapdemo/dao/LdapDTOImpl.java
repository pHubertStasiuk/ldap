package com.cs.ldapdemo.dao;

import com.cs.ldapdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;


import javax.naming.*;
import javax.naming.directory.*;

import java.util.*;

@Repository
@PropertySource({"classpath:ldap.properties"})
public class LdapDTOImpl implements LdapDTO {

    @Autowired
    private Environment env;
    private DirContext dirContext;

    @Override
    public boolean verifyCredentials(User user) {

        Boolean isAuthenticated;
        Hashtable<String, String> props = getProperties(user);
        try {
            dirContext = new InitialDirContext(props);
            isAuthenticated = dirContext != null;
            return isAuthenticated;
        } catch (NameNotFoundException nex) {
            System.out.println("UserName has not been found! " + user.getLogin());
            nex.printStackTrace();
            return false;
        } catch (NamingException ex) {
            System.out.println("Connection could not be established!");
            ex.printStackTrace();
            return false;
        }
    }

    private Hashtable<String, String> getProperties(User user) {
        Hashtable<String, String> props = new Hashtable<>();
        props.put(Context.INITIAL_CONTEXT_FACTORY, env.getProperty("LDAP.FACTORY"));
        props.put(Context.PROVIDER_URL, env.getProperty("LDAP.URL"));
        props.put(Context.SECURITY_AUTHENTICATION, env.getProperty("LDAP.SECURITY.AUTHENTICATION"));
        props.put(Context.SECURITY_CREDENTIALS, user.getPassword());
        props.put(Context.SECURITY_PRINCIPAL, user.getLogin() + env.getProperty("LDAP.DOMAIN"));
//        props.put(Context.LANGUAGE, "pl-PL");
        return props;
    }

    @Override
    public List<String> getRoles(User user) {

        String filter = "(&(memberOf=CN=phabricator,OU=Groups,DC=coreservices,DC=pl)(sAMAccountName=hstasiuk))";

        if (verifyCredentials(user)) {
            try {
                SearchControls controls = new SearchControls();
                controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                System.out.println("before numeration");
                NamingEnumeration answer = dirContext.search("DC=coreservices,DC=pl", filter, controls);
         

            } catch (NamingException ex) {
                System.out.println("Connection could not be established!");
                ex.printStackTrace();
            }
            return Collections.EMPTY_LIST;
        } else {
            return Collections.EMPTY_LIST;
        }
    }
}