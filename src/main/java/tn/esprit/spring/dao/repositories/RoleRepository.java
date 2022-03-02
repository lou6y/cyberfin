package tn.esprit.spring.dao.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Role;

@Repository

public interface RoleRepository extends CrudRepository<Role, Integer>{
	Role findByRole (String role);
}