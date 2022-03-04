package tn.esprit.spring.DAO.repository;

import org.springframework.data.repository.CrudRepository;


import tn.esprit.spring.DAO.entities.Feedback;

public interface FeedbackRepository extends CrudRepository<Feedback, Long>  {

}
