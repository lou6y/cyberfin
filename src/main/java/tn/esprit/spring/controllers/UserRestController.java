package tn.esprit.spring.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import com.fasterxml.jackson.databind.ObjectMapper;

import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.entities.Role;
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
	
	@GetMapping("/token/refresh")
	public void refreshToken (HttpServletRequest request, HttpServletResponse response)throws IOException{
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION) ;
		if (authorizationHeader != null && authorizationHeader.startsWith("before ") ){
			try {
				String refresh_token = authorizationHeader.substring("before ".length()) ; 
				Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
				JWTVerifier verifier = JWT.require(algorithm).build() ; 
				DecodedJWT decodedJWT = verifier.verify(refresh_token) ; 
				String username = decodedJWT.getSubject() ; 
				User user = userService.findByUserName(username) ; 
				String access_token = JWT.create()
				        .withSubject(user.getUserName())
				        .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
				       .withIssuer (request.getRequestURL ().toString())
				        .withClaim( "roles", user.getRoles().stream().map(Role::getRole).collect(Collectors.toList()))
				        .sign(algorithm);
				response.setHeader("access_token",access_token );
				response.setHeader("refresh_token",refresh_token );
				
			}
			catch(Exception exception){	
				response.setHeader("error" , exception.getMessage());
				response.setStatus(HttpStatus.FORBIDDEN.value());
				Map<String , String> error = new HashMap<>() ; 
				error.put("error_messsage",exception.getMessage() ) ; 
				response.setContentType(MediaType.APPLICATION_JSON_VALUE) ; 
				new ObjectMapper().writeValue(response.getOutputStream(),error) ; 
			}	
			
		}else { 
			throw new  RuntimeException("refresh token is missing");
		}
	}
	
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