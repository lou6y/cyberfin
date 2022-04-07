package tn.esprit.spring.DAO.repositories;

import tn.esprit.spring.DAO.entities.PaymentHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface PaymentHistoryRepository extends CrudRepository<PaymentHistory, Long> {

    @Query(value = "SELECT * FROM v_payments WHERE account_id = :account_id", nativeQuery = true)
    List<PaymentHistory> getPaymentRecordsById(@Param("account_id")Long account_id);
}