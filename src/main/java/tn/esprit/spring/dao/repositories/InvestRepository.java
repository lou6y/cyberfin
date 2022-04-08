package tn.esprit.spring.dao.repositories;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.dao.entities.Invest;

@Repository
public interface InvestRepository extends JpaRepository<Invest, Long>{

	@Query("SELECT Count(i) FROM Invest i WHERE i.Investstart > :date AND i.accountinvest = :accountid")
	int NbTransactionLastdate(@Param("date") Date date, @Param("accountid") Long accountid);	
}
