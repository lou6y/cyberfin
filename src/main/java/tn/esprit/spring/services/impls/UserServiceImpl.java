package tn.esprit.spring.services.impls;

import java.io.UnsupportedEncodingException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.dao.entities.ERole;
import tn.esprit.spring.dao.entities.Job;
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
	    user.setCreationDate(new Date(System.currentTimeMillis()));
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
	
	
	@Override
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
	
	@Override
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
	
	@Override
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
	
	@Override
	public List<User> showAllUsers()
	{
		List<User> users= (List<User>) userRepository.findAll(); 
		return users;
	}
	
	@Override
	public List<User> showUsersByJob(Job job) {
		return userRepository.findUsersByJob(job);
	}

	@Override
	public List<User> showUsersByRole(Set<Role> roles) {
		return userRepository.findUsersByRole(roles);
	}
	
	@Override
	public String modifyName(String username, String name )
	{
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (!userOptional.isPresent()) {
			return "Invalid username.";
		}
		else 
		{
			User user = userOptional.get();
			user.setName(name);
			return "Your name successfully updated.";
		}
		
	}
	
	@Override
	public String modifyEmail(String username, String Email )
	{
		Optional<User> userOptional = userRepository.findByUsername(username);
		if (!userOptional.isPresent()) {
			return "Invalid username.";
		}
		else 
		{
			User user = userOptional.get();
			user.setEmail(Email);
			return "Your email successfully updated.";
		}
		
	}
	
	@Override 
	public String deleteUser(Long id) 
	{ 
		userRepository.deleteById(id);
		return "User has been successfully deleted !";
	}
	
	@Scheduled(cron = " 0 0 0 * * * ")
	@Override
	public void scoring()
	{
		List<User> ListUsers = userRepository.findAll();
		for(User user : ListUsers)	
		{	
			int score=0;
			// score seniority ( 0 -> 1 )
			long month = 2628000000L;
			Date date = new Date(System.currentTimeMillis() - month );
			if(user.getCreationDate().compareTo(date) > 0)
			{ user.setBadge("NewClient");
			userRepository.save(user);
			}
			else
			{
			    long year = 31560000000L;
				Date date2 = new Date(System.currentTimeMillis() - year );	
			    if (user.getCreationDate().compareTo(date2) < 0)
			    {
				score += 1;
			    }
			 // score balance ( 0 -> 2 )
			    if (user.getAccount().getBalance() > 1000)
			    	score += 2;
			    	else if (user.getAccount().getBalance() > 500 )
			    	score += 1;
			 // score nb investment for investors ( 0 -> 4 )
			    if (user.getRoles().contains("ROLE_INVESTOR"))
			    { int nbinvest= user.getAccount().getInvests().size();
			    	switch (nbinvest)
			    	{
			    	case (0):
			    	break;
			    	case (1):
			    		score +=1;
			    	break;
			    	case (2):
			    		score +=1;
			    	break;
			    	case (3):
			    		score +=2;
			    	break;
			    	case (4):
			    		score +=2;
			    	break;
			    	case (5):
			    		score +=3;
			    	break;
			    	case (6):
			    		score +=3;
			    	break;
			    	default:
			    	score +=4;
			    	}
			    }
			    if (user.getRoles().contains("ROLE_CLIENT"))
			    {
			    // score nb loans for clients ( 0 -> 2) & score nb associations for clients ( 0 -> 2)
			    // ( 0 -> 4)
			    int nbloan=user.getAccount().getLoans().size();
			    switch (nbloan)
		    	{
		    	case (0):
		    	break;
		    	case (1):
		    		score +=1;
		    	break;
		    	case (2):
		    		score +=1;
		    	break;
		    	default:
		    	score +=2;
		    	}
			    int nbassociation=user.getAccount().getAssociations().size();
			    switch (nbassociation)
		    	{
		    	case (0):
		    	break;
		    	case (1):
		    		score +=1;
		    	break;
		    	case (2):
		    		score +=1;
		    	break;
		    	default:
		    	score +=2;
		    	}
			    }
			    
			    
			
		switch (score)
		{
		case (0):
			user.setBadge("Inactive");
		userRepository.save(user);
			break;
		case (1):
			user.setBadge("Inactive");
		userRepository.save(user);
			break;
		case (2):
			user.setBadge("Inactive");
		userRepository.save(user);
			break;
		case (3):
			user.setBadge("Normal");
		userRepository.save(user);
			break;
		case (4):
			user.setBadge("Normal");
		userRepository.save(user);
			break;
		case (5):
			user.setBadge("Good");
		userRepository.save(user);
			break;
		case (6):
			user.setBadge("Good");
		userRepository.save(user);
			break;
		case (7):
			user.setBadge("Verygood");
		userRepository.save(user);
			break;
		case (8):
			user.setBadge("Verygood");
		userRepository.save(user);
			break;
		case (9):
			user.setBadge("Excellent");
		userRepository.save(user);
			break;		
		}
		}
		}

}
}
