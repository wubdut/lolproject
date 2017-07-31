package com.dao;

import static org.junit.Assert.*;

import org.junit.Test;

import com.models.Logger;

public class LoggerDaoTest {

	public LoggerDao loggerDao;
	
	@Test
	public void testCreate() {
		loggerDao = new LoggerDao();
		Logger logger = new Logger();
		loggerDao.create(logger);
	}
	
	@Test
	public void testDelete() {
		loggerDao = new LoggerDao();
		Logger logger = new Logger();
		loggerDao.delete(logger);
	}
	
	@Test
	public void testUpdate() {
		loggerDao = new LoggerDao();
		Logger logger = new Logger();
		loggerDao.update(logger);
	}
	
	@Test
	public void testQueryAll() {
		loggerDao = new LoggerDao();
		loggerDao.getAll();
	}

}
