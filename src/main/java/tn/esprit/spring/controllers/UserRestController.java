package tn.esprit.spring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.services.interfaces.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	UserService userService ; 
	@PostMapping("/registration")
	public String createNewUser( @RequestBody User user) {
	String msg="";
	User userExists = userService.findByUserName(user.getUserName());
	if (userExists != null) {
	msg="There is already a user registered with the user name provided";
	} else {
	userService.saveUser(user);
	msg="OK, User added !"; }
	return msg; }
	
}