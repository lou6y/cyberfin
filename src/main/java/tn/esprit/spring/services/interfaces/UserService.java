package tn.esprit.spring.services.interfaces;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.List;

import javax.mail.MessagingException;

import tn.esprit.spring.dao.entities.User;

public interface UserService {
	public User saveUser(User user);
	public User findByUserName (String userName);
	public List<User> retrieveAllUsers();
	public void deleteUser(Long id);
	public void addRoleToUser(String userName, String role);
	public String forgotPassword(String email);
	public String resetPassword(String token, String password);
	public String generateToken();
	public boolean isTokenExpired(final LocalDateTime tokenCreationDate);
	public void sendEmail(String email, String msg) throws MessagingException, UnsupportedEncodingException;
	public boolean verify(String verificationCode);
}
