package tn.esprit.spring.dao.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findByUsername(String username);

  Boolean existsByUsername(String username);

  Boolean existsByEmail(String email);

  User findByVerificationCode(String verificationCode);

  User findByEmail(String email);

  User findByToken(String token);
  
}
