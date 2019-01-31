package com.cs.ldapdemo.rest;

import com.cs.ldapdemo.model.User;
import com.cs.ldapdemo.service.LdapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.InvalidNameException;
import java.util.List;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/ldap")
public class LdapController {

	@Autowired
	private LdapService ldapService;



//
//	@InitBinder
//	public void test(WebDataBinder webd){
//		StringTrimmerEditor trimer = new StringTrimmerEditor(true);
//		webd.registerCustomEditor(String.class, trimer);
//	}


	@PostMapping("/user/authentication")
	public boolean verifyCredentials(@RequestBody User user) {
		return this.ldapService.verifyCredentials(user);
	}

	@PostMapping("/roles")
	public ResponseEntity<List<String>> getRoles(@RequestBody User user) throws InvalidNameException {
		return new ResponseEntity<>(ldapService.getRoles(user), HttpStatus.OK);

	}

}


















