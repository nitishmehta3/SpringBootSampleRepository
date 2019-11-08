package com.example.webApp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.webApp.dao.UserRepo;
import com.example.webApp.model.User;

//@Controller

//If we don’t want to using @ResponseBody annotation with method
//we can use @RestController annotation with controller class declaration.
@RestController
public class UserController {

	
	//Using annotation @Autowired will try to find the UserRepo type object with
	//inside the container and assign it to repo variable
	@Autowired
	UserRepo repo;
	
	// region WEBSITEWITHVIEW
	@RequestMapping("/")
	public String home() {
		System.out.println("home called");
		return "home.jsp";
	}
	
//	@RequestMapping("/addUser")
//	public String addUser(User user) {
//		repo.save(user);
//		return "home.jsp";
//	}
	
	@RequestMapping("/getUser")
	public ModelAndView getUser(@RequestParam int id) {
		
		System.out.println(repo.findByTeamSorted("ReactNative"));
		
		ModelAndView mv = new ModelAndView("showUser.jsp");
		User user = repo.findById(id).orElse(new User());
		mv.addObject(user);
		return mv;
	}
	
	@RequestMapping("/getUserByTeamSorted")
	public ModelAndView getUserByTeamSorted() {
		
		System.out.println(repo.findByTeamSorted("ReactNative"));
		return (new ModelAndView("showuser.jsp"));
	}
	// end region
	
	// region REST_API
//	@RequestMapping(path="/users",produces= {"application/xml"})
	@RequestMapping("/users")// by default Request Mapping is Get Mapping
//	@ResponseBody
	public List<User> getUsers() {
		
		return repo.findAll();
	}
	
	
	//jackson core dependency in maven is helping here 
	// to convert the User object to json
	@RequestMapping("/user/{id}")
//	@ResponseBody
	public Optional<User> getUserById(@PathVariable("id") int id) {
		
		return repo.findById(id);
	}
	
	@PostMapping(path="/user", consumes = {"application/json"})// by default Request Mapping is Get Mapping
	public User addUser(@RequestBody User user) {
		//@RequestBody annotation to enable accepting raw data for post request.
		
		repo.save(user);
		return user;
	}
	
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id) {
		User user = repo.getOne(id);// returns the object when primary key is passed
		
		repo.delete(user);
		
		return "User successfully deleted" ;
	}
	
	@PutMapping(path="/user", consumes = {"application/json"})// by default Request Mapping is Get Mapping
	public User saveOrUpdateUser(@RequestBody User user) {
		//@RequestBody annotation to enable accepting raw data for post request.
		
		repo.save(user);
		return user;
	}
	
	//end region
}
