package tn.esprit.spring.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	
	@GetMapping("showUser/{userName}")
	public User showUser(@PathVariable("userName")String userName)
	{
	return userService.findByUserName(userName);
	}
	
	@GetMapping("showAllUsers") 
	public List<User> getAllUsers() 
	{
		return userService.retrieveAllUsers();
	} 
	
	@DeleteMapping("deleteUser/{idUser}") 
	public String DeleteUser(@PathVariable("idUser")Long idUser )
	{
		userService.deleteUser(idUser);
		String msg="User Deleted!";
		return msg;
	}
	
	@PostMapping("addRoleToUser/{userName}/{roleName}")
	public String addRoleToUser(@PathVariable("userName")String userName, @PathVariable("roleName")String roleName)
	{
		userService.addRoleToUser(userName, roleName);
		String msg="Role added!";
		return msg;
	}
	
}