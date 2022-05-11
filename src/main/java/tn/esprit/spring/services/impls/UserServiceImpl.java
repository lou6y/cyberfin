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
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import tn.esprit.spring.dao.entities.Account;
import tn.esprit.spring.dao.entities.ERole;
import tn.esprit.spring.dao.entities.Job;
import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.repositories.AccountRepository;
import tn.esprit.spring.dao.repositories.AssociationRepository;
import tn.esprit.spring.dao.repositories.InvestRepository;
import tn.esprit.spring.dao.repositories.LoanRepository;
import tn.esprit.spring.dao.repositories.RoleRepository;
import tn.esprit.spring.dao.repositories.TransactionRepository;
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
	  AccountRepository accountRepository;
	  
	  @Autowired
	  TransactionRepository transactRepository;
	  
	  @Autowired
	  InvestRepository investRepository;
	  
	  @Autowired
	  LoanRepository loanRepository;
	  
	  @Autowired
	  AssociationRepository associationRepository;
	  
	  
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
	public Optional<Account> findAccount(Long iduser)
	{
		Optional<User> userOptional = userRepository.findById(iduser);
		if (!userOptional.isPresent()) {
			return null;
		}
		else 
		{
		User _user = userOptional.get();
		return accountRepository.findById(_user.getAccount().getId());
		}
	}
	
	@Override
	public User saveUser(User user) {
		String randomCode = RandomString.make(64);
	    user.setVerificationCode(randomCode);
	    user.setVerified(false);
	    user.setCreationDate(new Date(System.currentTimeMillis()));
	    Account acc = new Account(10L);
	    accountRepository.save(acc);
	    user.setAccount(acc);
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
	public int countByJob(Job job)
	{
		return userRepository.countByJob(job);
	}

	@Override
	public List<User> showUsersByRole(Set<Role> roles) {
		return userRepository.findUsersByRole(roles);
	}
	
	@Override
	public String updateUser(Long id, User user) {
		Optional<User> userOptional = userRepository.findById(id);
		if (!userOptional.isPresent()) {
			return "Invalid id.";
		}
		else 
		{
		User _user = userOptional.get();
		_user.setUsername(user.getUsername());
		_user.setName(user.getName());
		_user.setLastname(user.getLastname());
		_user.setEmail(user.getEmail());
		_user.setPassword(user.getPassword());
		_user.setPassword(bCryptPasswordEncoder.encode(_user.getPassword()));
		_user.setJob(user.getJob());
		_user.setDateBirth(user.getDateBirth());
		_user.setProfilePic(user.getProfilePic());
		_user.setGender(user.getGender());
		userRepository.save(_user);
		return "Your account successfully updated.";
		}
	}
	
	@Override
	@Transactional
	public String modifyRole(Long id, ERole role) {
		Optional<User> userOptional = userRepository.findById(id);
		Optional<Role> roleOptional = roleRepository.findByName(role);
		if (!userOptional.isPresent()) {
			return "Invalid id.";
		}
		else 
		{
		User _user = userOptional.get();
		Role _role = roleOptional.get();
		_user.getRoles().add(_role);
		userRepository.save(_user);
		return "Role successfully updated."; 
		}
		
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
			userRepository.save(user);
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
			userRepository.save(user);
			return "Your email successfully updated.";
		}
		
	}
	
	@Override 
	public String deleteUser(Long id) 
	{ 
		userRepository.deleteById(id);
		return "User has been successfully deleted !";
	}
	
	@Override
	public Long balanceByJob (Job job)
	{
		List<User> ListUsersByJob = userRepository.findUsersByJob(job);
		Long somme = 0L, i = 0L;
		for(User user : ListUsersByJob)
		{somme = somme + user.getAccount().getBalance();
		i++;
		}
		return (somme/i);
	}
	
	@Scheduled(cron = " 0 0 0 * * * ")
	@Override
	public void scoring()
	{
		List<User> ListUsers = userRepository.findAll();
		for(User user : ListUsers)	
		{	
			int score=0;
			String badge = "";
			// score seniority ( 0 -> 1 )
			long month = 2628000000L;
			Date date = new Date(System.currentTimeMillis() - month);
			if(user.getCreationDate().compareTo(date) > 0)
			{ badge="NewClient";}
			else
			{
			    long year = 31560000000L;
				Date date2 = new Date(System.currentTimeMillis() - year);	
			    if (user.getCreationDate().compareTo(date2) < 0)
			    {
				score += 1;
			    }
			 // score balance ( 0 -> 2 
                Job job = user.getJob();
			    if (user.getAccount().getBalance() > balanceByJob(job))
			    	score += 1;
			    if (user.getAccount().getBalance() > 500 )
			    	score += 1;
			 // score nb transactions last 1 month ( 0 -> 2 )
			    int nbtransactions = transactRepository.NbTransactionLastdate(date, user.getAccount().getId());
			    if (nbtransactions < 3)
				    score +=1;
					else score +=2;
			 // score nb investment for investors last 1 year ( 0 -> 4 )
			    if (user.getRoles().contains("ROLE_INVESTOR"))
			    { int nbinvest= investRepository.NbTransactionLastdate(date2, user.getAccount().getId());
			    if (nbinvest < 3)
					score+=1;
					else if (nbinvest < 5)
					score+=2;
					else if (nbinvest < 7)
					score+=3;
					else score+=4;
			    }
			    if (user.getRoles().contains("ROLE_CLIENT"))
			    {
			    // score nb loans for clients ( 0 -> 2) & score nb associations for clients last 1 year ( 0 -> 2)
			    // ( 0 -> 4)
			    int nbloan= loanRepository.NbLoanLastdate(date2, user.getAccount().getId());
			    if (nbloan < 3)
			    score +=1;
				else score +=2;
			    int nbassociation= associationRepository.NbAssociationLastdate(date2, user.getAccount().getId());
			    if (nbassociation < 3)
		    	score +=1;
			    else score +=2;
			    }
			    
		if (score < 3)
		badge=("Inactive");
		else if (score < 5)
		badge=("Normal");
		else if (score < 7)
		badge=("Good");
		else if (score < 9)
		badge=("Verygood");
		else badge=("Excellent");
		}
		user.setBadge(badge);	
		userRepository.save(user);
		}

}
	
}
