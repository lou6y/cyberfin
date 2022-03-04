package tn.esprit.spring.service.inter;

import java.util.List;

import tn.esprit.spring.DAO.entities.Feedback;



public interface IFeedbackService {
	
	 Feedback addFeedback( Feedback F);
	 void deleteFeedback(String id);
	 Feedback updateFeedback(Feedback F);
	 Feedback retrieveFeedback(String id);
	 List<Feedback> retrieveAllFeedbacks();
	

}