package com.cs.ldapdemo.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Logger {

    @Before("execution(public boolean verifyCredentials(com.cs.ldapdemo.model.User))")
    public void beforeVeryfingCredentials(){
        System.out.println("Starting verification of user in LDAP");
    }
    @After("execution(public boolean verifyCredentials(com.cs.ldapdemo.model.User)))")
    public void afterVeryfingCredentials(){
        System.out.println("User has been verified!");
    }
}
