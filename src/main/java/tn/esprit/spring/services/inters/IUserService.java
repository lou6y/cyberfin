package tn.esprit.spring.services.inters;

import java.io.UnsupportedEncodingException;
import java.util.Optional;

import javax.mail.MessagingException;

import tn.esprit.spring.dao.entities.ERole;
import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;

public interface IUserService {
	  Optional<User> findByUsername(String username);
	  Boolean existsByUsername(String username);
	  Boolean existsByEmail(String email);
	  Optional<Role> findByName(ERole name);
	  User saveUser(User user);
	String verify(String verificationCode);
	void sendEmail(String email, String response) throws MessagingException, UnsupportedEncodingException;
	String forgotPassword(String email);
	String resetPassword(String token, String password);


}
