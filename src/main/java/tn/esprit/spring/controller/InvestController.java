package tn.esprit.spring.controller;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import tn.esprit.spring.entity.Invest;
import tn.esprit.spring.services.Interf.InvestServices;





@RestController
public class InvestController {
	@Autowired
	InvestServices Investser;
	//http://localhost:80/SpringMVC/getAllInvests
 @GetMapping("/getAllInvests")
 public List<Invest> getAllInvests()
 {return Investser.retrieveAllInvests();}

@GetMapping("/retrieve-Invest/{Invest-id}")
@ResponseBody
public Invest retrieveInvest(@PathVariable("Invest-id") Long InvestId) {
return Investser.retrieveInvest(InvestId); }


@PostMapping("/add-Invest")
@ResponseBody
public Invest addClient(@RequestBody Invest  c)
{
Invest Invest = Investser.addClient(c);
return Invest;
}
@DeleteMapping("/remove-Invest/{Invest-id}")
@ResponseBody
public void removeInvest(@PathVariable("client-id") Long InvestId) {
Investser.deleteInvest(InvestId);
}
@PutMapping("/modify-client")
@ResponseBody
public Invest modifyInvest(@RequestBody Invest Invest) {
return Investser.updateInvest(Invest);
}
@PostMapping("/simulator")
@ResponseBody
public double simulator(@RequestParam(name="montant") int montant ,@RequestParam(name="mode" ) int mode){
        return Investser.Simulator(montant,mode);
}
@PostMapping("/epagne")
@ResponseBody
public double ajouterInterets(@RequestParam(name="montant") int getSolde ) {
        return Investser.ajouterInterets(getSolde);
}

}
