package com.ajoy.service.codegen.bo;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBColumn;
import com.ajoy.model.codegen.DBTable;
import com.ajoy.model.codegen.DBTables;
import com.ajoy.model.codegen.ResponseCode;
import com.ajoy.model.codegen.SessionInfo;
import com.ajoy.service.codegen.workflow.CallContext;

public class DBTablesBO extends BaseBO
{
	private static Logger log = LogManager.getLogger(DBTablesBO.class);
	//private DBTablesBO dao = new DBTablesBO();
	
	public ResponseCode<DBTables> getDBTables(CallContext context)
	{
		log.info("getDBTables() start");
		ResponseCode<DBTables> code = new ResponseCode<>(); //dao.getTables();
		code.setObject(tables);
		code.setSuccess(true);
		log.info("getDBTables() end ");
		return code;
	}

	public ResponseCode<DBTable> getDBTableDetails(DBTable table, CallContext context)
	{
		log.info("getDBTableDetails() start");
		ResponseCode<DBTable> code = new ResponseCode<>(); //dao.getTable(table);
		
		for(DBTable tmp: tables.getTableList())
		{
			if(table.getName().equals(tmp.getName()))
			{
				code.setSuccess(true);
				code.setObject(tmp);
				return code;
			}
		}		
		log.info("getDBTableDetails() end ");
		return code;
	}

	
	//Will go away after implementation
	private static DBTables tables = new DBTables();
	
	static
	{
		DBTable table;
		tables.setTableList(new ArrayList<DBTable>());
						
		tables.getTableList().add((table = new DBTable())); 		
		table.setName("txn_main_tbl");
		table.setColumnList(new ArrayList<>(5));		
		table.getColumnList().add(new DBColumn("txn_id", DBColumn.LongDataType));
		table.getColumnList().add(new DBColumn("txn_status", DBColumn.IntDataType));

		tables.getTableList().add((table = new DBTable())); 		
		table.setName("cust_info_tbl");
		table.setColumnList(new ArrayList<>(5));		
		table.getColumnList().add(new DBColumn("cust_id", DBColumn.LongDataType));
		table.getColumnList().add(new DBColumn("cust_status", DBColumn.IntDataType));
		table.getColumnList().add(new DBColumn("cust_fname", DBColumn.StringDataType));
		table.getColumnList().add(new DBColumn("cust_lname", DBColumn.StringDataType));

		
		tables.getTableList().add((table = new DBTable())); 		
		table.setName("ext_acct_dtls_tbl");
		table.setColumnList(new ArrayList<>(5));		
		table.getColumnList().add(new DBColumn("acct_id", DBColumn.LongDataType));
		table.getColumnList().add(new DBColumn("acct_status", DBColumn.IntDataType));		
	}
}


