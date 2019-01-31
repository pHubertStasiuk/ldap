package com.cs.ldapdemo.dao;

import com.cs.ldapdemo.model.User;
import com.sun.jndi.ldap.LdapName;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.naming.*;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
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
        props.put(Context.SECURITY_CREDENTIALS, decodePassword(user.getPassword()));
        props.put(Context.SECURITY_PRINCIPAL, user.getLogin() + env.getProperty("LDAP.DOMAIN"));
        props.put(Context.LANGUAGE, "pl-PL");
        return props;
    }


    @Override
    public List<String> getRoles(User user) throws InvalidNameException {

        String filter = "sAMAccountName=" + user.getLogin();


        if (verifyCredentials(user)) {
            try {
                SearchControls controls = new SearchControls();
                controls.setSearchScope(SearchControls.SUBTREE_SCOPE);
                NamingEnumeration answer = dirContext.search("DC=coreservices,DC=pl", filter, controls);

                String userOutput = answer.next().toString();

                return getUserRolesFromOutput(userOutput);


            } catch (NamingException ex) {
                System.out.println("Connection could not be established!");
                ex.printStackTrace();
            }
            return Collections.EMPTY_LIST;
        } else {
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public String decodePassword(String string) {

        Base64 decoder = new Base64();
        byte[] decodedBytes = decoder.decode(string);
        String toReturn = new String(decodedBytes);
        return toReturn;

    }

    public static List<String> getUserRolesFromOutput(String userOutput) throws InvalidNameException {

        final String ROLE_PREFIX = "CN=";
        final int ROLE_PREFIX_INDEX = 3;
        final String MEMBER_OF_PREFIX = "memberof=memberOf: ";

        String memberOf = userOutput.substring(userOutput.indexOf(MEMBER_OF_PREFIX));
        LdapName ldapName = new LdapName(memberOf);
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < ldapName.size(); i++) {
            if (ldapName.get(i).contains(ROLE_PREFIX)) {
                sb.append(ldapName.get(i).substring(ldapName.get(i).indexOf(ROLE_PREFIX)).substring(ROLE_PREFIX_INDEX)+" ");
            }
        }
        String userRoles = sb.toString();
        List<String> lista = new ArrayList<>(Arrays.asList(userRoles.split(" ")));
        System.out.println(lista.size());
        return lista;
    }

}