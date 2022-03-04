package tn.esprit.spring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.DAO.entities.Feedback;
import tn.esprit.spring.service.inter.IFeedbackService;
 
 
@RestController
@RequestMapping("/feedback")
public class FeedbackRestController {

	@Autowired
	IFeedbackService FeedbackService;
	
	// http://localhost:8086/SpringMVC/feedback/addFeedback
	@PostMapping("/addFeedback")
	@ResponseBody	
	public Feedback addFeedback(@RequestBody Feedback f) {
	return FeedbackService.addFeedback(f);}
	
	
	// http://localhost:8086/SpringMVC/feedback/retrieveAllFeedbacks
	@GetMapping("/retrieveAllFeedbacks")
	@ResponseBody
	public List<Feedback> getFeedback() {
	 List<Feedback> f = FeedbackService.retrieveAllFeedbacks();
	 return f;
	 }
	
	
	// http://localhost:8086/SpringMVC/feedback/remove-feedback/{feedback-id}
	@DeleteMapping("/remove-feedback/{feedback-id}")
	@ResponseBody
	public void removeFeedback(@PathVariable("feedback-id") String feedbackId) {
	FeedbackService.deleteFeedback(feedbackId);
	}
	
	// http://localhost:8086/SpringMVC/feedback/retrieve-feedback/{feedback-id}
	@GetMapping("/retrieve-feedback/{feedback-id}")
	@ResponseBody
	public Feedback retrieveFeedback(@PathVariable("feedback-id") String feedbackId) {
	return FeedbackService.retrieveFeedback(feedbackId);
	}
	
	

}
