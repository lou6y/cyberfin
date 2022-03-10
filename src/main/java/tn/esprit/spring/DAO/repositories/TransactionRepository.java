package tn.esprit.spring.DAO.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import tn.esprit.spring.DAO.entities.Transaction;

import java.util.Date;
import java.util.List;
import tn.esprit.spring.DAO.entities.TransactionType;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {
	//select
	/*@Query("SELECT t FROM Transaction t WHERE t.transactiontype= :transactiontype")
	List<Transaction> retrieveTransactionsByTransactionType(@Param("transactiontype") TransactionType transactiontype);
	//update
	@Modifying
	@Query("update Transaction t set t.transactionType = :transactiontype")
	int updateClientCategorieByTransactionType(@Param("transactiontype") TransactionType transactiontype);

	//delete
	@Modifying
	@Query("DELETE FROM Transaction t WHERE t.transactiontype = :transactiontype")
	int deleteTransactionByTransactionType(@Param("transactiontype") TransactionType transactiontype); */
	
	//insert
	//@Modifying
	//@Query(value = "INSERT INTO Client (nom, prenom, dateNaissance,email,password,profession,categorieClient) VALUES (:nom, :prenom, :dateN, :email, :password, :profession, :categorieClient)", nativeQuery = true)
	//void insertClient(@Param("nom") String nom, @Param("prenom") String prenom,
	//@Param("dateN") Date dateNaissance, @Param("email") String email,
	//@Param("password") String password, @Param("profession") Profession profession, 
	
	
	@Query
    ("SELECT t From Transaction t Where t.dateTransaction BETWEEN :date1 AND :date2 ORDER BY t.dateTransaction ASC")
List<Transaction> ListTransactionOfweekAgo(@Param("date1") Date date1,@Param("date2") Date date2);
}
