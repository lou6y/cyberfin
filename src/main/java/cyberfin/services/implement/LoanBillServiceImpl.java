package cyberfin.services.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cyberfin.Repository.LoanBillRepository;
import cyberfin.entity.*;

import cyberfin.services.interfaces.LoanBillService;

@Service
public class LoanBillServiceImpl implements LoanBillService {
	
	@Autowired 
	LoanBillRepository loanbillRep;

	@Override
	public List<LoanBill> retrieveAllLoanBills() {
		List<LoanBill> loanbills=(List<LoanBill>) (loanbillRep.findAll());
		return loanbills ;
	}

	@Override
	public LoanBill addLoanBill(LoanBill lb) {
		loanbillRep.save(lb);
		return lb;
	}

	@Override
	public LoanBill updateLoanBill(LoanBill lbu) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoanBill retrieveLoanBill(Long ref) {
		return loanbillRep.findById(ref).get();
	}
	
	

}
