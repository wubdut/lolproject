package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.models.Register;


@Repository
@Transactional
public class RegisterDao {


	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * Save the user in the database.
	   */
	  public void create(Register register) {
	    entityManager.persist(register);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Register register) {
	    if (entityManager.contains(register))
	      entityManager.remove(register);
	    else
	      entityManager.remove(entityManager.merge(register));
	    return;
	  }
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Register> getAll() {
	    return entityManager.createQuery("from Register").getResultList();
	  }
	  
	  /**
	   * Return the user having the passed email.
	   */
	  public Register getByUsername(String username) {
	    return (Register) entityManager.createQuery(
	        "from Register where username = :username")
	        .setParameter("username", username)
	        .getSingleResult();
	  }

	  /**
	   * Return the user having the passed id.
	   */
	  public Register getById(long id) {
	    return entityManager.find(Register.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Register register) {
	    entityManager.merge(register);
	    return;
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
} //class Register
