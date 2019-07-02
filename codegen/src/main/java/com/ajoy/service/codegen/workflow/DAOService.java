package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DAOS;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.bo.DAOSBO;

public class DAOService 
{
	private static Logger log = LogManager.getLogger(DAOService.class);
	private DAOSBO bo = new DAOSBO();

	
	public ResponseCode<DAOS> updateDAOS(DAOS daos, CallContext context)
	{
		DAOSBO bo = getDAOSBO();
		return bo.updateDOAS(daos, context);
	}
	
	public ResponseCode<DAOS> getDAOS( CallContext context)
	{
		DAOSBO bo = getDAOSBO();
		return bo.getDAOS(context);

	}

	
	private DAOSBO getDAOSBO()
	{
		return bo;
	}

}
