package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.models.Logger;


@Repository
@Transactional
public class LoggerDao {


	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * Save the user in the database.
	   */
	  public void create(Logger logger) {
	    entityManager.persist(logger);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Logger logger) {
	    if (entityManager.contains(logger))
	      entityManager.remove(logger);
	    else
	      entityManager.remove(entityManager.merge(logger));
	    return;
	  }
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Logger> getAll() {
	    return entityManager.createQuery("from Logger").getResultList();
	  }
	  
	  /**
	   * Return the user having the passed email.
	   */
	  public Logger getByEmail(String email) {
	    return (Logger) entityManager.createQuery(
	        "from User where email = :email")
	        .setParameter("email", email)
	        .getSingleResult();
	  }

	  /**
	   * Return the user having the passed id.
	   */
	  public Logger getById(long id) {
	    return entityManager.find(Logger.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Logger logger) {
	    entityManager.merge(logger);
	    return;
	  }
	  
	  public boolean checker(Logger logger) {
		  int res = entityManager.createQuery("from Logger where username = :username and password = :password")
			        .setParameter("username", logger.getUsername()).setParameter("password", logger.getPassword())
			        .getResultList().size();
		  return (res > 0);
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
} // class LoggerDao
