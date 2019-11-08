package com.example.webApp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.webApp.model.User;

// Data Access Object
public interface UserRepo extends JpaRepository<User,Integer>  {

	//The name of method is given as per the JPA protocols
	//Method name should start with findBy or getBy and ends with name of property
	//If we enter anything else instead of team or some property not present 
	//then it will show error
	List<User> findByTeam(String team);
	
	//Again here Id is property and Greater than is as per protocol 
	//we cannot user GT or any other word if we intend to use Greater than query
	List<User> findByIdGreaterThan(int id);
	
	// provide JPQL(somewhat similar to SQL) query here
	@Query("from User where team=?1 order by name")
	List<User> findByTeamSorted(String team);
	
}
