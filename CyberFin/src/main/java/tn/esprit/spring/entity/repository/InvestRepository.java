package tn.esprit.spring.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Invest;
@Repository
public interface InvestRepository extends JpaRepository<Invest, Long>  {

}
