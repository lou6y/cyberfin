package cyberfin.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import cyberfin.entity.*;




@Repository
public interface LoanRepository extends CrudRepository<Loan, Long>
{

}
