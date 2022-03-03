package tn.esprit.spring.DAO.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

}
