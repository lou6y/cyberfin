package tn.esprit.spring.services.Interf;

public interface IReactService {

	
	
	public void addReactToComment(int reaction,int CommentId,int UserId);
	public void DislikeComment(int ReactID, int UserId);
	
	public void DislikePost(int ReactID, long UserId);
	public void addReactToPost(int reaction, int PostId, long UserId);
	public void LikeDislikePost(int reaction, int PostId, long UserId);


}
