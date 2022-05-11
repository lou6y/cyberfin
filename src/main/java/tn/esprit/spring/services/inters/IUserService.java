package tn.esprit.spring.services.inters;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.mail.MessagingException;

import tn.esprit.spring.dao.entities.Account;
import tn.esprit.spring.dao.entities.ERole;
import tn.esprit.spring.dao.entities.Job;
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
	List<User> showAllUsers();
	List<User> showUsersByJob(Job job);
	List<User> showUsersByRole(Set<Role> roles);
	String updateUser(Long id, User user);
	String modifyRole(Long id, ERole role);
	String modifyName(String username, String name);
	String modifyEmail(String username, String Email);
	String deleteUser(Long id);
	Long balanceByJob(Job job);
	void scoring();
	Optional<Account> findAccount(Long idaccount);
	int countByJob(Job job);


}
