package tn.esprit.spring.services.impls;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.dao.repositories.RoleRepository;
import tn.esprit.spring.dao.repositories.UserRepository;
import tn.esprit.spring.services.interfaces.UserService;


@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	private RoleRepository roleRepository;
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
	
}