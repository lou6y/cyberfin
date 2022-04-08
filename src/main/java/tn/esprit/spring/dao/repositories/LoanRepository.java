package tn.esprit.spring.dao.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Loan;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long>{

	@Query("SELECT Count(l) FROM Loan l WHERE l.startdate > :date AND l.account = :accountid")
	int NbLoanLastdate(@Param("date") Date date, @Param("accountid") Long accountid);	
}
