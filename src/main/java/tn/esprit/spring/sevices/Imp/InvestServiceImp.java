package tn.esprit.spring.sevices.Imp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

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


@Override
	public float EvaluateSeniority(Long id_account) {
		Invest pc = retrieveInvest(id_account);
		LocalDate ld = pc.getInveststart();
		long Seniority = ld.until(LocalDate.now(), ChronoUnit.YEARS);
		if (Seniority > 5) {
			return 1;
		} else {
			if (Seniority > 3)
				return 0.5f;
		}

		return 0;
	}
@Override
public String scoreAccount(Long idClient) {
	Invest pc = retrieveInvest(idClient);
	
	 float score =  (float) (0.5 * EvaluateSeniority(idClient)+  0.5 * pc.getAmount()
	
	);
	
	if (score>100) {
		
		return "his account is a premium account with a score of :\t"+score;
		
	}else if (score<100 && score > 70) {
		
		return "This account is a golden account with a score of :\t"+score;
	}else if (score<70 && score > 40) {
		
		return "This account is a silver account with a score of :\t"+score;
	}else if (score<40) {
		
		return "This account is a bronze account with a score of : \t"+score;
	}
	return null;
		
}
	
  
	@Override
	public float ajouterInterets(long accountId){ 
	       LocalDate today = LocalDate.now();
		   if (today.getMonth() == Month.APRIL && today.getDayOfMonth()==8) {
			   float PMTV = retrieveInvest(accountId).getAmount()*(1+10/100);
			   return PMTV;
		   }
		return 0;
		
		  
              
			
    
}
    }
    
   

	 
	

