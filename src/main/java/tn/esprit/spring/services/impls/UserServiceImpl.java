package tn.esprit.spring.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.repositories.UserRepository;
import tn.esprit.spring.services.interfaces.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public User findByUserName(String userName) {
		return userRepository.findByUserName(userName);
		}

	@Override
	public User saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setVerified(true);
		return userRepository.save(user); 
		
		
	}
}