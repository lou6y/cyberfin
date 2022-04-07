
package tn.esprit.spring.sevices.Imp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import tn.esprit.spring.services.Interf.IPostService;

import tn.esprit.spring.sevices.Imp.InvestServiceImp;
import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.Invest;
import tn.esprit.spring.entity.repository.PostRepository;
import tn.esprit.spring.entity.repository.InvestRepository;

@Service
public class PostService implements IPostService {

	@Autowired
	PostRepository PostRepository;
	@Autowired
	InvestRepository userRepository;

	@Override
	@Transactional
	public void createPost(Post Post, long userId) {
		// TODO Auto-generated method stub
		Invest user = userRepository.findById(userId).orElse(null);
		List<Post> userposts = user.getPosts();
		userposts.add(Post);
		// userRepository.save(user);
		Post.setUser(user);
		Post.setNlikes(0);
		PostRepository.save(Post);

	}

	@Override
	public void deletePost(int idPost) {
		// TODO Auto-generated method stub

		Post c = PostRepository.findById(idPost).orElse(null);
		PostRepository.delete(c);

	}

	@Override
	public void updatePost(Post Post) {
		// TODO Auto-generated method stub
		PostRepository.save(Post);

	}

	@Override
	public List<Post> getAllPosts() {
		// TODO Auto-generated method stub
		List<Post> Posts = (List<Post>) PostRepository.findAll();
		return Posts;
	}

	@Override
	public String getForbiddenWords() {
		String content = "";
		try {
			
			File file = new File("C:\\Users\\dell\\Desktop\\forbiddenwords.txt");
			content = new String(Files.readAllBytes(file.toPath()));
			System.out.println(content);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return content;

	}

	@Override
	public void setForbiddenWords(String words) {
		try {
			String filePath = "C:\\Users\\dell\\Desktop\\forbiddenwords.txt";
			FileOutputStream f = new FileOutputStream(filePath, true);
			String lineToAppend = getForbiddenWords() + "\t"
					+ words.replaceAll("[\\t\\n\\r]+", " ").replaceAll(" +", " ");
			byte[] byteArr = lineToAppend.getBytes();// converting string into byte array
			// pw.write(getForbiddenWords() + words.replaceAll("[\\t\\n\\r]+", "
			// ").replaceAll(" +", " "));
			f.write(byteArr);
			f.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void createPostForbidden(int postId, long userId) {
		Invest user = userRepository.findById(userId).orElse(null);

		String[] forbiddenWords = getForbiddenWords().split(" ");
		boolean forbiden = false;
		Post Post = new Post();
		Post = PostRepository.findById(postId).get();
		String[] content = Post.getText().split(" ");
		String newwcontent = "";
		for (String c : content) {
			System.out.println(c);
						
			for (String forbiddenWord : forbiddenWords) {
				if (c.contains(forbiddenWord)) {
					System.out.println("true");
					System.out.println(forbiddenWord);
					// c.replaceAll(c, "********");
					//c.replace(forbiddenWord, "********");
					Post.setText("********");
					System.out.println(newwcontent);
					forbiden = true;
					// newwcontent=+ String.join(" ", c);
					// newwcontent.concat(String.join(" ", c));
				}
			}
		}
		System.out.println("====================================");
		System.out.println(newwcontent);

		if (forbiden) {
			PostRepository.delete(Post);

		} else { // false
			Post.setText(String.join(" ", content));
			Post.setUser(user);
			Post.setDate(new Date());
			List<Post> userposts = user.getPosts();
			userposts.add(Post);
			Post.setNlikes(0);
			PostRepository.save(Post);
		}
	}

	@Override
	public void SharePost(int PostId, long UserId) {

		Post PostParent = PostRepository.findById(PostId).orElse(null);
		Invest userShare = userRepository.findById(UserId).orElse(null);
		Post Share = new Post();
		Share.setDate(new Date());
		Share.setNlikes(0);
		Share.setPost(PostParent);
		Share.setText(PostParent.getText());
		Share.setUser(userShare);
		List<Post> shares = PostParent.getChildren();
		shares.add(Share);
		PostRepository.save(Share);

	}

	@Override
	public List<Post> GetSharedPosts(int PostId) {
		// TODO Auto-generated method stub
		Post PostParent = PostRepository.findById(PostId).orElse(null);
		List<Post> Shared = PostParent.getChildren();
		return Shared;

	}

	@Override
	public void createPostForbidden(Post Post, long userId) {
		// TODO Auto-generated method stub
		
	}

}
