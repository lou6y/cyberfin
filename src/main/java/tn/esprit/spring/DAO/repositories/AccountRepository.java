package tn.esprit.spring.DAO.repositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import tn.esprit.spring.DAO.entities.Account;

import org.springframework.transaction.annotation.Transactional;



@Repository
public interface AccountRepository extends CrudRepository<Account, Long>{
	
	
	//ba3d njarreb nhezhom l TRANSACTIONREPOSITORY
	//wHere acc.id_account (nafs l esm fel database)
	@Modifying
	    @Query(value ="UPDATE Account acc SET acc.balance = :new_balance WHERE acc.id_account = :id_account" , nativeQuery = true)
	    @Transactional
	    void changeAccountBalanceById(@Param("new_balance") double new_balance, @Param("id_account") Long id_account);

	@Query(value = "SELECT balance FROM Account WHERE id_account = :id_account", nativeQuery = true)
    double getAccountBalance(@Param("id_account") Long id_account);
	
	
	
	
}
