package tn.esprit.spring.services.impls;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.repositories.RoleRepository;
import tn.esprit.spring.dao.repositories.UserRepository;
import tn.esprit.spring.services.interfaces.UserService;


@Service
public class UserServiceImpl implements UserService {
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;
	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
		}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setVerified(false);
		return userRepository.save(user); 
		
	}
	
	@Override
	public boolean verify(String verificationCode) {
	    User user = userRepository.findByVerificationCode(verificationCode);
	     
	    if (user == null || (user.getVerified()==true)) {
	        return false;
	    } else {
	        user.setVerificationCode(null);
	        user.setVerified(true);
	        userRepository.save(user);
	         
	        return true;
	    }
	     
	}
	
	@Override
	public List<User> retrieveAllUsers()
	{
		List<User> users= (List<User>) userRepository.findAll(); 
		return users;
	}
	
	@Override 
	public void deleteUser(Long id) 
	{ 
		userRepository.deleteById(id);
	}
	
	@Override
	public void addRoleToUser(String userName, String roleName)
	{
		User user = userRepository.findByUserName(userName);
		Role role = roleRepository.findByRole(roleName);
		user.getRoles().add(role);
		
	}
	
	
	public String forgotPassword(String email) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByEmail(email));

		if (!userOptional.isPresent()) {
			return "Invalid email id.";
		}

		User user = userOptional.get();
		user.setToken(generateToken());
		user.setTokenCreationDate(LocalDateTime.now());

		user = userRepository.save(user);

		return user.getToken();
	}

	public String resetPassword(String token, String password) {

		Optional<User> userOptional = Optional
				.ofNullable(userRepository.findByToken(token));

		if (!userOptional.isPresent()) {
			return "Invalid token.";
		}

		LocalDateTime tokenCreationDate = userOptional.get().getTokenCreationDate();

		if (isTokenExpired(tokenCreationDate)) {
			return "Token expired.";

		}

		User user = userOptional.get();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setToken(null);
		user.setTokenCreationDate(null);

		userRepository.save(user);

		return "Your password successfully updated.";
	}

	/**
	 * Generate unique token. You may add multiple parameters to create a strong
	 * token.
	 * 
	 * @return unique token
	 */
	public String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}

	/**
	 * Check whether the created token expired or not.
	 * 
	 * @param tokenCreationDate
	 * @return true or false
	 */
	public boolean isTokenExpired(final LocalDateTime tokenCreationDate) {

		LocalDateTime now = LocalDateTime.now();
		Duration diff = Duration.between(tokenCreationDate, now);

		return diff.toMinutes() >= EXPIRE_TOKEN_AFTER_MINUTES;
	}
	
	public void sendEmail(String recipientEmail, String msg) throws MessagingException, UnsupportedEncodingException {
	    MimeMessage message = mailSender.createMimeMessage();              
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	     
	    helper.setFrom("contact@cyberfin.tn", "CyberFin Support");
	    helper.setTo(recipientEmail);
	     
	    String subject = "CyberFin Verification";
	     
	    String content = "<p>Hello,</p>"
	            + "[[URL]]";
	    content = content.replace("[[URL]]", msg);
	             
	    helper.setSubject(subject);
	     
	    helper.setText(content, true);
	     
	    mailSender.send(message);
	}
}