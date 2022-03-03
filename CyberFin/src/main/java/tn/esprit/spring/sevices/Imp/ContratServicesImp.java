package tn.esprit.spring.sevices.Imp;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;



import tn.esprit.spring.entity.contrat;

@Service
public interface ContratServicesImp  {
	
	public List<contrat> getAllcontrat();
	
    public contrat addLoan(contrat l);
	
	public void deletecontratByID(long id);
	
	public Optional<contrat> getcontratByID(long id);
	
	public Optional<contrat> findByUserID(long id);
	
}