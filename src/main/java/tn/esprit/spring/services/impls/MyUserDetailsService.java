package tn.esprit.spring.services.impls;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import  javax.transaction.Transactional;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.security.core.GrantedAuthority;
import  org.springframework.security.core.authority.SimpleGrantedAuthority;
import  org.springframework.security.core.userdetails.UserDetails;
import  org.springframework.security.core.userdetails.UserDetailsService;
import  org.springframework.security.core.userdetails.UsernameNotFoundException;
import  org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.services.interfaces.UserService;
@Service
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;

	@Override
	@Transactional
	public  UserDetails  loadUserByUsername(String  userName)  throws  UsernameNotFoundException  {
		User  user  =  userService.findByUserName(userName); 
		List<GrantedAuthority>  authorities  =  getUserAuthority(user.getRoles());
		return  new  org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),user.getActive(),  true,  true,  true,  authorities);	}
	
	private  List<GrantedAuthority>  getUserAuthority(Set<Role>  userRoles)  { 
		Set<GrantedAuthority>  roles  =  new  HashSet<GrantedAuthority>(); 
	for  (Role  role  :  userRoles) {
		roles.add(new  SimpleGrantedAuthority(role.getRole())); }
		 
	List<GrantedAuthority>  grantedAuthorities =  new  ArrayList<>(roles);
	return grantedAuthorities;
}}
