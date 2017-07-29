package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.models.Play;
import com.models.User;



@Repository
@Transactional
public class PlayDao {

	// ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * Save the user in the database.
	   */
	  public void create(Play play) {
	    entityManager.persist(play);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Play play) {
	    if (entityManager.contains(play))
	      entityManager.remove(play);
	    else
	      entityManager.remove(entityManager.merge(play));
	    return;
	  }
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Play> getAll() {
	    return entityManager.createQuery("from Play").getResultList();
	  }
	  

	  /**
	   * Return the user having the passed id.
	   */
	  public Play getById(long id) {
	    return entityManager.find(Play.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Play play) {
	    entityManager.merge(play);
	    return;
	  }
	  
	  @SuppressWarnings("unchecked")
	  public List<Play> getByNameAndDate(String name, String date) {
	    return (List<Play>) entityManager.createQuery(
	        "from Play where (team1.name like :name or team2.name like :name) and date like :date")
	        .setParameter("name", "%"+name+"%").setParameter("date", "%"+date+"%").getResultList();
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
	
} //class Team
