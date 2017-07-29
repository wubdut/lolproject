package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.models.Team;


@Repository
@Transactional
public class TeamDao {

	// ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * Save the user in the database.
	   */
	  public void create(Team team) {
	    entityManager.persist(team);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Team team) {
	    if (entityManager.contains(team))
	      entityManager.remove(team);
	    else
	      entityManager.remove(entityManager.merge(team));
	    return;
	  }
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Team> getAll() {
	    return entityManager.createQuery("from Team").getResultList();
	  }
	  
	  /**
	   * Return the user having the passed email.
	   */
	  public Team getByUsername(String username) {
	    return (Team) entityManager.createQuery(
	        "from User where username = :username")
	        .setParameter("username", username)
	        .getSingleResult();
	  }

	  /**
	   * Return the user having the passed id.
	   */
	  public Team getById(long id) {
	    return entityManager.find(Team.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Team team) {
	    entityManager.merge(team);
	    return;
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
	
} //class Team
