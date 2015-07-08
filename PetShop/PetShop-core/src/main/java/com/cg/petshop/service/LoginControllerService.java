package com.cg.petshop.service;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.cg.petshop.User;

@Stateless
public class LoginControllerService implements ILoginController {

	
	
	

	@PersistenceContext(unitName = "defaultPersistenceUnit")
    private EntityManager entityManager;
	final static Logger logger = Logger.getLogger(productSearchImpl.class.getName());
	
	
	/**
	 * This Method is responsible for validating the User
	 * If valid Login Return Success
	 * else return Failed
	 */
	public String validateUser(User userObj) {
		
		
		User userResult=null;
		String resultString = null;
		try{
			
			
			userResult = entityManager.find(User.class, userObj.getUser_id());
			
			if(userResult!=null && userObj.getPassword().equals(userResult.getUser_id()))
				resultString=  "Success";
			else
				resultString = "Failed";
			
		}
		catch(Exception exp){
			
			System.out.println(exp);
			
		}
		
		return resultString;
		
		
		
	}
	
	/**
	 * Retrieve the User Object by User ID
	 * @param userId
	 * @return
	 */
	public User getUserById(String userId){
		
		logger.info("Getting User Object by User ID:::: Starts ");
		User userObj = null;
		
		try{
			
			userObj = entityManager.find(User.class, userId);
			
		}
		catch(Exception exp){
			
		}
		logger.info("Getting User Object by User ID:::: Ends ");
		return userObj;
	}
	/**
	 * Persisting New User
	 * @param newUserObj
	 */
	public void saveNewUser(User newUserObj){
		
		logger.info("Saving New User::::");
		
		try{
			
			entityManager.persist(newUserObj);
		}
		catch(Exception exp){
			
		}
	}
}
