package tn.esprit.spring.entity.repository;


import tn.esprit.spring.entity.*;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestRepository extends CrudRepository<Invest, Long>{

	
}