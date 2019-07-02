package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.service.codegen.bo.DAOMethodBO;

public class DAOMethodService 
{
	private static Logger log = LogManager.getLogger(DAOMethodService.class);
	private DAOMethodBO bo = new DAOMethodBO();

	
	private DAOMethodBO getDAOMethodBO()
	{
		return bo;
	}


}
