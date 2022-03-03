package tn.esprit.spring.sevices.Imp;

import java.util.List;
import java.util.Optional;

import tn.esprit.spring.entity.Invest;
import tn.esprit.spring.entity.repository.InvestRepository;

public class InvestServicesImp {

	  InvestRepository InvestRepository ;
		public List<Invest> getAllInvest() {
			return InvestRepository.findAll();
		}
		public int addInvest (Invest l) {
			
			 InvestRepository.save(l);
			return 1 ;
		}
		public void deletInvestByID(long id) {
			
			InvestRepository.deleteById(id);;
			
		}

		public Optional<Invest> getInvestByID(long id) {
			
			return InvestRepository.findById(id);
		}

		public Optional<Invest> findByuserID(long id) {
			
			return InvestRepository.findById(id);
		}

	}

