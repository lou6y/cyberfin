package tn.esprit.spring.services.impls;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
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
import tn.esprit.spring.dao.entities.ERole;
import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.repositories.RoleRepository;
import tn.esprit.spring.dao.repositories.UserRepository;
import tn.esprit.spring.services.inters.IRoleService;
import tn.esprit.spring.services.inters.IUserService;

@Service
public class UserServiceImpl implements IUserService, IRoleService{
	private static final long EXPIRE_TOKEN_AFTER_MINUTES = 30;

	@Autowired
	  UserRepository userRepository;

	  @Autowired
	  RoleRepository roleRepository;
	  
	  @Autowired
		private JavaMailSender mailSender;
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		
	  
	@Override
	public Optional<User> findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public Optional<Role> findByName(ERole name) {
		return roleRepository.findByName(name);
	}

	@Override
	public User saveUser(User user) {
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setVerified(false);
		return userRepository.save(user); }
	
	@Override
	public String verify(String verificationCode) {
	    User user = userRepository.findByVerificationCode(verificationCode);
	     
	    if (user == null || (user.getVerified()==true)) {
	        return "verify_fail";
	    } else {
	        user.setVerificationCode(null);
	        user.setVerified(true);
	        userRepository.save(user);
	         
	        return "verify_success";
	    }
	     
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
	
	public String generateToken() {
		StringBuilder token = new StringBuilder();

		return token.append(UUID.randomUUID().toString())
				.append(UUID.randomUUID().toString()).toString();
	}
	
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
