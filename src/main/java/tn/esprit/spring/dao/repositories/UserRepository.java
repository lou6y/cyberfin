package tn.esprit.spring.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long>{
	User findByUserName (String userName);
	User findByEmail(String email);
	User findByToken(String token);
    User findByVerificationCode(String verificationCode);
}
