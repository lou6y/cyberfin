package tn.esprit.spring.DAO.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.Claim;
import org.springframework.transaction.annotation.Transactional;



@Transactional
@Repository
public interface ClaimRepository extends CrudRepository<Claim, Long> {

}
