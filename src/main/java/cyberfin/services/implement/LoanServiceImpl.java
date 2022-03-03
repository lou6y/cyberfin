package cyberfin.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyberfin.Repository.LoanRepository;
import cyberfin.entity.*;
import cyberfin.services.interfaces.LoanService;


@Service 
public class LoanServiceImpl implements LoanService {
	@Autowired
	LoanRepository loanRep;

	@Override
	public List<Loan> retrieveAllLoans() {
		List<Loan> loans=(List<Loan>) (loanRep.findAll());
		return loans ;
	}

	@Override
	public Loan addLoan(Loan l) {
		loanRep.save(l);
		return l;
	}

	@Override
	public Loan updateLoan(Loan lu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Loan retrieveLoan(Long idloan) {
		return loanRep.findById(idloan).get();
		
	}

}
