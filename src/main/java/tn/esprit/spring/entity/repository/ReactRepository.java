package tn.esprit.spring.entity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.entity.Post;
import tn.esprit.spring.entity.React;
import tn.esprit.spring.entity.Invest;

@Repository
public interface ReactRepository extends CrudRepository<React,Integer>{
	


	@Query("SELECT r FROM React r WHERE r.PostLike= :post")
	public List<React> findReactByPostId(@Param("post") Post Post);
	
	@Query("SELECT r FROM React r WHERE r.UserLike= :user")
	public List<React> findReactByUserId(@Param("user") Invest User);



	

}
