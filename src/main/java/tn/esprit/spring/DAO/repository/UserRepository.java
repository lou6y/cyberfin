package tn.esprit.spring.DAO.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.User;


@Repository
public interface UserRepository  extends CrudRepository<User, Long>{
public User findByUserName(String username);
	
	
}
