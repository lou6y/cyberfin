package tn.esprit.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.spring.services.Interf.*;


@RestController
@RequestMapping("/React")
public class ReactController {
	@Autowired
	IReactService ReactService;
	
	// http://localhost:8089/SpringMVC/React/create/2/1/1
	@PutMapping("ReactToPost/{reactionId}/{PostId}/{UserId}")
	@ResponseBody
	public void addReactToPost(@PathVariable("reactionId")int reaction,@PathVariable("PostId") int PostId,@PathVariable("UserId") long UserId) {
		ReactService.addReactToPost(reaction, PostId, UserId);
	}
	
	
	// http://localhost:8089/SpringMVC/React/DislikePost/1/1
	@DeleteMapping("/DislikePost/{ReactID}/{UserId}")
	public void DislikePost(@PathVariable("ReactID") int ReactID, @PathVariable("UserId") int UserId) {
		ReactService.DislikePost(ReactID,UserId);
	}


	
	// http://localhost:8089/SpringMVC/React/ReactToPost1/2/1/1
	@GetMapping("LikeDislikePost/{reactionId}/{PostId}/{UserId}")
	public void LikeDislikePost(@PathVariable("reactionId")int reaction,@PathVariable("PostId") int PostId,@PathVariable("UserId") int UserId) {
		ReactService.LikeDislikePost(reaction, PostId, userId);
	}
}
