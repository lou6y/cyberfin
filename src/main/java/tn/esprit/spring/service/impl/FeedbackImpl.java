package tn.esprit.spring.service.impl;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import tn.esprit.spring.DAO.entities.Feedback;

import tn.esprit.spring.DAO.repository.FeedbackRepository;
import tn.esprit.spring.service.inter.IFeedbackService;



@Service
@Slf4j
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
	
		String  text = F.getDescriptionFeedback();
		String a =   
		        "Bastard \n" +    
		        "Bugger  \n" +   
		        "\n" +   
		        "Bollocks\n" +   
		        "Bloody hell\n" +   
		        "Shit \n" +   
		        "\n" +   
		        "Asshole\n" +   
		        "\n" +   
		        "Damn\n" +   
		        "Fair suck of the sav\n" +   
		        "Rubbish\n" +   
		        "nigga\n" +   
		        "hell\n" +   
		        "effing\n" +   
		        "\n" +   
		        "bullshit" ;
	 
	 
	 
	 Pattern pattern = Pattern.compile(text) ; 
	 Matcher matcher = pattern.matcher(a) ;
	 //Matcher m = p.matcher("abc");
	 while (matcher.find()) {  
	    System.out.println("testtest") ; 
	    return null;
	    
	 }
		
		FeedbackSaved = FeedbackRepository.save(F);
		return FeedbackSaved;
	
	}
	
	@Override
	public Feedback retrieveFeedback(String id) {
		
		
		log.info("in retrieveFeedback id = " + id);
		
		
		Feedback F = FeedbackRepository.findById(Long.parseLong(id)).get();
		
		log.info("Feedback returned = : " + F);
		
		
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
		for(Feedback feed : Feedbacks)
		{
			log.info("Feedback +++ :" + feed);
		}
		
		
		return Feedbacks;
	}

	
	
	
}
