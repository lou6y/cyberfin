package tn.esprit.spring.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.dao.entities.Account;
import tn.esprit.spring.dao.entities.ERole;
import tn.esprit.spring.dao.entities.Job;
import tn.esprit.spring.dao.entities.Role;
import tn.esprit.spring.dao.entities.User;
import tn.esprit.spring.services.inters.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

	 @Autowired
	  IUserService userService;
	 
	 @GetMapping("getAllUsers") 
	 //@PreAuthorize("hasRole('ADMIN')")
        public List<User> getAllUsers() 
		{
			return userService.showAllUsers();
		} 
		
	@GetMapping("getUser/{username}")
	// @PreAuthorize("hasRole('ADMIN')")
        public Optional<User> getUser(@PathVariable("username")String username)
		{
		return userService.findByUsername(username);
		}
		
	@GetMapping("/getusersbyjob/{job}")
	//@PreAuthorize("hasRole('ADMIN')")
        public List<User> getUsersByJob(@PathVariable("job") Job job)
		{
			return userService.showUsersByJob(job);
		}
	
	@GetMapping("/countbyjob/{job}")
	//@PreAuthorize("hasRole('ADMIN')")
        public int countByJob(@PathVariable("job") Job job)
		{
			return userService.countByJob(job);
		}
		
	@GetMapping("/getusersbyrole/{roles}")
	//@PreAuthorize("hasRole('ADMIN')")
        public List<User> getUsersByRole(@PathVariable("roles") Set<Role> roles)
		{
			return userService.showUsersByRole(roles);
		}
	
	@GetMapping("/getAccount/{iduser}")
	//@PreAuthorize("hasRole('ADMIN')")
    public Optional<Account> getAccount(@PathVariable("iduser")Long iduser)
    {
		return userService.findAccount(iduser);
    	
    }
	
	@PutMapping("/updateUser/{idUser}")
	//@PreAuthorize("hasRole('ADMIN')")
	public String updateUser(@PathVariable("idUser")Long idUser, @RequestBody User user)
	{
		return userService.updateUser(idUser, user);
	}
	 
	@PutMapping("/modifyRole/{idUser}")
	//@PreAuthorize("hasRole('ADMIN')")
	public String modifyRole(@PathVariable("idUser")Long idUser, @RequestParam ERole role)
	{
		return userService.modifyRole(idUser, role);
	}
	
	@PutMapping("/modify-name/{username}")
	//@PreAuthorize("hasRole('ADMIN')")
	   public String modifyName(@PathVariable("username")String username, @RequestParam String name) 
	   {
		return userService.modifyName(username, name);
       }
	
	@PutMapping("/modify-email/{username}")
	//@PreAuthorize("hasRole('ADMIN')")
	   public String modifyEmail(@PathVariable("username")String username, @RequestParam String email) 
	   {
		return userService.modifyEmail(username, email);
       }
	
	@DeleteMapping("deleteUser/{idUser}") 
	//@PreAuthorize("hasRole('ADMIN')")
	public String DeleteUser(@PathVariable("idUser")Long idUser )
	{
		return userService.deleteUser(idUser);
	}
	
	@GetMapping("getbalancebyjob/{job}") 
	//@PreAuthorize("hasRole('ADMIN')")
	public Long  getbalancebyjob(@PathVariable("job")Job job )
	{
		return userService.balanceByJob(job);
	}
	
	@PutMapping("/scoring")
	//@PreAuthorize("hasRole('ADMIN')")
	public void  Scoring()
	{
	userService.scoring();	
	}
	
}
