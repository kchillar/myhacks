package com.ajoy.service.codegen.workflow;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBTable;
import com.ajoy.model.codegen.DBTables;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.service.codegen.bo.DBTablesBO;

public class DBTablesService 
{
	private static Logger log = LogManager.getLogger(DBTablesService.class);
	private DBTablesBO bo = new DBTablesBO();

	public ResponseCode<DBTables> getDBTables(CallContext context)
	{
		DBTablesBO bo = getTablesBO();
		return bo.getDBTables(context);
	}
	
	public ResponseCode<DBTable> getDBTableDetails(DBTable dbTable, CallContext context)
	{
		DBTablesBO bo = getTablesBO();
		return bo.getDBTableDetails(dbTable, context);
	}

	private DBTablesBO getTablesBO()
	{
		return bo;
	}
}
