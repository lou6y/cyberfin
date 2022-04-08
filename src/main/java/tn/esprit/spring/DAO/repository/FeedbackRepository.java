package tn.esprit.spring.DAO.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.DAO.entities.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long>  {

}
