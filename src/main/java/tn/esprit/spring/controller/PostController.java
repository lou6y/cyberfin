package tn.esprit.spring.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import tn.esprit.spring.services.Interf.*;
import tn.esprit.spring.entity.Post;

@RestController
@RequestMapping("/Post")
public class PostController {
	@Autowired
	IPostService PostService;


	
	// http://localhost:8089/SpringMVC/Post/create

	@PutMapping("create/{userId}")
	@ResponseBody
	public void createPost(@RequestBody Post Post,@PathVariable("userId") long userId) {
		PostService.createPost(Post,userId);
	}
	// http://localhost:8089/SpringMVC/Post/SharePost

	@PutMapping("SharePost/{PostId}/{userId}")
	@ResponseBody
	public void SharePost(@PathVariable("PostId") int PostId,@PathVariable("userId") long userId) {
		PostService.SharePost(PostId,userId);
	}
	
	// http://localhost:8089/SpringMVC/Post/GetSharedPosts/1
	@GetMapping("/GetSharedPosts/{PostId}")
	@ResponseBody
	public List<Post> GetSharedPosts(@PathVariable("PostId") int PostId) {

		List<Post> Posts = PostService.GetSharedPosts(PostId);
		return Posts;
	}
	
	// http://localhost:8089/SpringMVC/Post/getAllPosts
	@GetMapping("/getAllPosts")
	@ResponseBody
	public List<Post> getAllPosts() {

		List<Post> Posts = PostService.getAllPosts();
		return Posts;

	}
	// http://localhost:8089/SpringMVC/Post/delete/1

	@DeleteMapping("/delete/{id}")
	public void deletePost(@PathVariable("id") int PostId) {

		PostService.deletePost(PostId);		
	}
	// http://localhost:8089/SpringMVC/Post/update

	@PutMapping("/update")
	public void updatePost(@RequestBody Post Post) {
		PostService.updatePost(Post);
	}
	
	//http://localhost:8089/SpringMVC/Post/getforbidden
    @GetMapping("/getforbidden")
    @ResponseBody
    public String getFobiddenWords() {
        return PostService.getForbiddenWords();
    }
    //http://localhost:8083/SpringMVC/Post/setforbidden
    @PostMapping("/setforbidden")
    @ResponseBody
    public void setForbiddenWords(@RequestBody String forbiddenWords) {
        if (forbiddenWords==null)
            forbiddenWords="";
        PostService.setForbiddenWords(forbiddenWords);
    }
	// http://localhost:8089/SpringMVC/Post/createPostForbidden/1

	@PutMapping("createPostForbidden/{userId}")
	@ResponseBody
	public void createPostForbidden(@RequestParam("postId") int postId ,@PathVariable("userId") long userId) {
		PostService.createPostForbidden(postId,userId);
	}
}