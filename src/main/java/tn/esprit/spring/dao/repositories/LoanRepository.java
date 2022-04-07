package tn.esprit.spring.dao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

}
