package tn.esprit.spring.services.Interf;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;


import tn.esprit.spring.entity.Invest;

@Service
public interface InvestServices  {
	
	public List<Invest> getAllInvest();
	
    public Invest addLInvest(Invest l);
	
	public void deleteInvestByID(long id);
	
	public Optional<Invest> getInvestByID(long id);
	
	public Optional<Invest> findByUserID(long id);

	Optional<Invest> findByuserID(long id);
	
}