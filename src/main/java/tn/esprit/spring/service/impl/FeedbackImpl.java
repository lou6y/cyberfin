package tn.esprit.spring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.DAO.entities.Feedback;
import tn.esprit.spring.DAO.repository.FeedbackRepository;
import tn.esprit.spring.service.inter.IFeedbackService;



@Service
public class FeedbackImpl implements IFeedbackService{
	
	
	

	@Autowired     
	FeedbackRepository FeedbackRepository ;

	
	
	@Override
	public void deleteFeedback(String id) {
		FeedbackRepository.deleteById(Long.parseLong(id));
		
	}
	
	@Override
	public Feedback  addFeedback(Feedback F) {
		Feedback FeedbackSaved = null;
		FeedbackSaved = FeedbackRepository.save(F);
		
		return FeedbackSaved;
	
	}
	
	@Override
	public Feedback retrieveFeedback(String id) {
		
		Feedback F = FeedbackRepository.findById(Long.parseLong(id)).get();
		
		return F;
			}

	@Override
	public Feedback updateFeedback(Feedback F) {
		Feedback FeedbackAdded = FeedbackRepository.save(F);
		return FeedbackAdded;
	}
	
	@Override
	public List<Feedback> retrieveAllFeedbacks() {
		List<Feedback> Feedbacks = (List<Feedback>) FeedbackRepository.findAll(); 	
		return Feedbacks;
	}

	
	
	
}
