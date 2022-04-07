package tn.esprit.spring.sevices.Imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import tn.esprit.spring.entity.Invest;
import tn.esprit.spring.entity.repository.InvestRepository;
import tn.esprit.spring.services.Interf.InvestServices;



@Service
public class InvestServiceImp implements InvestServices{


	@Autowired
	InvestRepository Investrep;
	@Override
	public List<Invest> retrieveAllInvests() {
		List<Invest> Invests= (List<Invest>) Investrep.findAll();
		return Invests;
	}

	@Override
	public Invest addClient(Invest c) {
		Investrep.save(c);
		return c;
	}
	
	@Override
	public void deleteInvest(Long id) {
		Investrep.deleteById(id);		
		}

	@Override
	public Invest updateInvest(Invest c) {
		Investrep.save(c);
		return null;
	}

	@Override
	public Invest retrieveInvest(Long userId) {
		return Investrep.findById(userId).get();
}
	@Override
    public double Simulator(double montant,int mode) {
		
            double PMTV1 = montant*1.06;
            double PMTV2 = montant*1.1;
            if (mode ==1 )
                return Math.round(PMTV1);
        return  Math.round(PMTV2);
}

	@Override
	public List<Invest> retrieveAllClients() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double ajouterInterets(int getSolde) {
		// TODO Auto-generated method stub
		return 0;
	}



	
                   
       
   
	/**
	
	@Override
	public double ajouterInterets( int getSolde ,int duree)
	{ 
	
		   if (diffday<30 )
		   return getSolde ;
		   
		   double PMTV =getSolde*(1+10/100);
		   
		    if (diffday>30 )
               return Math.round(PMTV);
    
}
    }
    
    
    (import static java.time.temporal.ChronoUnit.SECONDS)
    @Test
    
public void diffday( Date firstDate)
  throws ParseException {
 
    
    long diffInMillies = Math.abs(LocalDateTime.now() - firstDate.getTime());
    long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);

    assertEquals(6, diff);
}


	 */
	
}
