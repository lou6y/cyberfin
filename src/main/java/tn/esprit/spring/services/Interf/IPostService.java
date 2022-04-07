package tn.esprit.spring.services.Interf;

import java.io.IOException;
import java.util.List;

import tn.esprit.spring.entity.Post;


public interface IPostService {
	//CRUD
	
	void deletePost(int  idPost) ;
	void updatePost(Post Post);
	public List<Post> getAllPosts();
	
	public void setForbiddenWords(String words);
	public String getForbiddenWords() ;
	
	public List<Post> GetSharedPosts(int PostId);
	void createPostForbidden(Post Post, long userId);
	void createPost(Post Post, long userId);

	void SharePost(int PostId, long UserId);
	void createPostForbidden(int postId, long userId);
	

}
