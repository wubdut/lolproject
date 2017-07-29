package com.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.models.Department;


@Repository
@Transactional
public class DepartmentDao {

	// ------------------------
	  // PUBLIC METHODS
	  // ------------------------
	  
	  /**
	   * Save the user in the database.
	   */
	  public void create(Department department) {
	    entityManager.persist(department);
	    return;
	  }
	  
	  /**
	   * Delete the user from the database.
	   */
	  public void delete(Department department) {
	    if (entityManager.contains(department))
	      entityManager.remove(department);
	    else
	      entityManager.remove(entityManager.merge(department));
	    return;
	  }
	  
	  /**
	   * Return all the users stored in the database.
	   */
	  @SuppressWarnings("unchecked")
	  public List<Department> getAll() {
	    return entityManager.createQuery("from Department").getResultList();
	  }
	  
	  /**
	   * Return the user having the passed email.
	   */
	  public Department getByEmail(String name) {
	    return (Department) entityManager.createQuery(
	        "from User where name = :name")
	        .setParameter("name", name)
	        .getSingleResult();
	  }

	  /**
	   * Return the user having the passed id.
	   */
	  public Department getById(long id) {
	    return entityManager.find(Department.class, id);
	  }

	  /**
	   * Update the passed user in the database.
	   */
	  public void update(Department department) {
	    entityManager.merge(department);
	    return;
	  }

	  // ------------------------
	  // PRIVATE FIELDS
	  // ------------------------
	  
	  // An EntityManager will be automatically injected from entityManagerFactory
	  // setup on DatabaseConfig class.
	  @PersistenceContext
	  private EntityManager entityManager;
} // class DepartmentDao
