package tn.esprit.spring.services.impls;

import java.util.ArrayList;
import java.util.Collection;

import  javax.transaction.Transactional;
import  org.springframework.beans.factory.annotation.Autowired;
import  org.springframework.security.core.authority.SimpleGrantedAuthority;
import  org.springframework.security.core.userdetails.UserDetails;
import  org.springframework.security.core.userdetails.UserDetailsService;
import  org.springframework.security.core.userdetails.UsernameNotFoundException;
import  org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.services.interfaces.UserService;
@Service @Transactional @Slf4j
public class MyUserDetailsService implements UserDetailsService{
	@Autowired
	private UserService userService;

	@Override
	public  UserDetails  loadUserByUsername(String  userName)  throws  UsernameNotFoundException  {
		User  user  =  userService.findByUserName(userName); 
		if (user==null)
		{
			throw new UsernameNotFoundException("User not found");
		}
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		user.getRoles().forEach(role -> { authorities.add(new SimpleGrantedAuthority(role.getRole()));});
		return  new  org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),authorities);	}

}
