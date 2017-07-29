package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.models.Authority;


@Repository
@Transactional
public class AuthorityDao {


	  // ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * Save the user in the database.
	   */
	  public void create(Authority authority) {
	    entityManager.persist(authority);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Authority authority) {
	    if (entityManager.contains(authority))
	      entityManager.remove(authority);
	    else
	      entityManager.remove(entityManager.merge(authority));
	    return;
	  }
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Authority> getAll() {
	    return entityManager.createQuery("from Authority").getResultList();
	  }
	  
	  /**
	   * Return the user having the passed email.
	   */
	  public Authority getByName(String name) {
	    return (Authority) entityManager.createQuery(
	        "from User where name = :name")
	        .setParameter("name", name)
	        .getSingleResult();
	  }

	  /**
	   * Return the user having the passed id.
	   */
	  public Authority getById(long id) {
	    return entityManager.find(Authority.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Authority authority) {
	    entityManager.merge(authority);
	    return;
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
} // class AuthorityDao
