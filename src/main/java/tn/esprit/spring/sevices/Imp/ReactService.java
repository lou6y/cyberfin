 package tn.esprit.spring.sevices.Imp;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.spring.entity.repository.ReactRepository;
import tn.esprit.spring.entity.repository.InvestRepository;

import tn.esprit.spring.entity.repository.PostRepository;
import tn.esprit.spring.services.Interf.*;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.React;
import tn.esprit.spring.entity.Reaction;
import tn.esprit.spring.entity.Invest;

@Service
public class ReactService implements IReactService {
	@Autowired
	ReactRepository ReactRepository;
	@Autowired
	PostRepository PostRepository;
	@Autowired
	InvestRepository userRepository;

	public boolean Decision(int PostId, long userId) {
		// true =dislike
		// false like
		Invest userConnected = userRepository.findById(userId).orElse(null);
		Post post = PostRepository.findById(PostId).orElse(null);
		List<React> reactByUser = ReactRepository.findReactByUserId(userConnected);
		List<React> reactByPost = ReactRepository.findReactByPostId(post);
		boolean liked = false;
		if (!(reactByUser.isEmpty())) {
			for (React ru : reactByUser) {
				if (reactByPost.contains(ru)) {
					liked = true;
					System.out.print("rue   ******************************************");
				} else
					liked = false;
			}
		}
		return liked;
	}

	@Override
	public void LikeDislikePost(int reaction, int PostId, long UserId) {
		Invest userConnected = userRepository.findById(UserId).orElse(null);
		Post post = PostRepository.findById(PostId).orElse(null);
		boolean liked = false;
		List<React> userlikes = userConnected.getLikes();
		System.out.println("userlikes    " + userlikes.size() + "******************************************");
		List<React> postlikes = post.getLikes();
		List<React> reactByUser = ReactRepository.findReactByUserId(userConnected);
		List<React> reactByPost = ReactRepository.findReactByPostId(post);

		if (Decision(PostId, UserId)) {
			// dislike
			for (React ru : reactByUser) {
				if (reactByPost.contains(ru)) {
					int nombre = post.getNlikes();
					nombre = nombre - 1;
					post.setNlikes(nombre);
					postlikes.remove(ru);
					userlikes.remove(ru);
					ReactRepository.delete(ru);
					break;
				}
			}

		} else {// like

			React react = new React();
			Reaction myVar = Reaction.LIKE;
			switch (reaction) {
			case 0:
				myVar = Reaction.LIKE;

				break;

			case 1:
				myVar = Reaction.ADORE;

				break;
			case 2:
				myVar = Reaction.HAHA;

				break;
			case 3:
				myVar = Reaction.WOW;
				break;
			case 4:
				myVar = Reaction.CRY;
				break;
			case 5:
				myVar = Reaction.ANGRY;
				break;
			}
			react.setReaction(myVar);
			react.setPostLike(post);
			react.setUserLike(userConnected);
			System.out.print("tasti lajout   ******************************************");
			ReactRepository.save(react);
			System.out.print("tasti lajout 22222222222222222   ******************************************");
			userlikes.add(react);
			post.setNlikes(post.getNlikes() + 1);
			postlikes.add(react);

			PostRepository.save(post);
			System.out.print("userlikes  final  " + userlikes.size() + "******************************************");
			System.out.print("postlikes   final  " + postlikes.size() + "******************************************");

		}
	}

	@Override
	@Transactional
	public void addReactToPost(int reaction, int PostId, long UserId) {
		React react = new React();
		Post post = PostRepository.findById(PostId).orElse(null);
		Invest user = userRepository.findById(UserId).orElse(null);
		Reaction myVar = Reaction.LIKE;
		switch (reaction) {
		case 0:
			myVar = Reaction.LIKE;

			break;

		case 1:
			myVar = Reaction.ADORE;

			break;
		case 2:
			myVar = Reaction.HAHA;

			break;
		case 3:
			myVar = Reaction.WOW;
			break;
		case 4:
			myVar = Reaction.CRY;
			break;
		case 5:
			myVar = Reaction.ANGRY;
			break;
		}
		react.setReaction(myVar);
		react.setPostLike(post);
		react.setUserLike(user);
		ReactRepository.save(react);
		post.setNlikes(post.getNlikes() + 1);

		if (post.getLikes().isEmpty()) {
			List<React> list = new ArrayList<React>();
			list.add(react);
		} else {
			List<React> listr = (List<React>) post.getLikes();
			listr.add(react);
		}
		PostRepository.save(post);

	}

	@Override
	@Transactional
	public void DislikePost(int ReactID, long UserId) {
		React react = ReactRepository.findById(ReactID).orElse(null);
		long userreact = react.getUserLike().getUserId();
		Invest userConnected = userRepository.findById(UserId).orElse(null);
		boolean reacttopost = PostRepository.findById(react.getPostLike().getPostId()).isPresent();
		if ((userConnected.getUserId() == userreact) && (reacttopost)) {
			Post post = PostRepository.findById(react.getPostLike().getPostId()).orElse(null);
			post.setNlikes(post.getNlikes() - 1);
			List<React> list = post.getLikes();
			list.remove(react);
			PostRepository.save(post);
			ReactRepository.delete(react);
		}
	}

	@Override
	public void addReactToComment(int reaction, int CommentId, int UserId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DislikeComment(int ReactID, int UserId) {
		// TODO Auto-generated method stub
		
	}


}
