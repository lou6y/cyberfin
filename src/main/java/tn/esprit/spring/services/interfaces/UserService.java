package tn.esprit.spring.services.interfaces;

import java.util.List;

import tn.esprit.spring.dao.entities.User;

public interface UserService {
	public User saveUser(User user);
	public User findByUserName (String userName);
	public List<User> retrieveAllUsers();
	public void deleteUser(Long id);
	public void addRoleToUser(String userName, String roleName);
}
