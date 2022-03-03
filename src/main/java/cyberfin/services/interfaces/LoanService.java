package cyberfin.services.interfaces;

import java.util.List;

import cyberfin.entity.*;



public interface LoanService {
List<Loan> retrieveAllLoans();

	

	Loan addLoan(Loan l);

	Loan updateLoan(Loan lu);

	Loan retrieveLoan(Long idloan);
}
