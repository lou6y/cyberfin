package cyberfin.services.interfaces;

import java.util.List;

import cyberfin.entity.*;

public interface LoanBillService {
List<LoanBill> retrieveAllLoanBills();

	

	LoanBill addLoanBill(LoanBill lb);

	LoanBill updateLoanBill(LoanBill lbu);

	LoanBill retrieveLoanBill(Long ref);

}
