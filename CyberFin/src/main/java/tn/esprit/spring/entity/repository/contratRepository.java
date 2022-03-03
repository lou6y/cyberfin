package tn.esprit.spring.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.contrat;
@Repository
public interface contratRepository extends JpaRepository<contrat, Long>  {

}
