package tn.esprit.spring.services.interfaces;

import tn.esprit.spring.dao.entities.User;

public interface UserService {
	public User saveUser(User user);
	User findByUserName (String userName);
}
