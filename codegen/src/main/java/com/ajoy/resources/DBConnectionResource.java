package com.ajoy.resources;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.ajoy.model.codegen.DBInfo;

public class DBConnectionResource
{
	private static Logger log = LogManager.getLogger(DBConnectionResource.class);

	private static DBConnectionResource singleton = new DBConnectionResource();

	private Map<String, DataSource> dataSourceMap = new HashMap<>();

	public static DBConnectionResource getInstance()
	{
		return singleton;
	}

	public void creatDataSource(DBInfo config)
	{
		log.info("created data source "+config.getName()+" start");
		BasicDataSource  dataSource = new BasicDataSource();
		dataSource.setUrl(config.getUrl());
		dataSource.setUsername(config.getUser());
		dataSource.setPassword(config.getPass());
		dataSource.setDriverClassName(config.getDriverClassName());

		// Min number of connections to be created in the pool
		dataSource.setInitialSize(0);

		// Man number of connections that be created in the pool
		dataSource.setMaxActive(2);
		// The eviction thread will check this often for unused connection to bring down the pool
		// size
		dataSource.setTimeBetweenEvictionRunsMillis(5000);
		// If a connection is idle (not used) for more than this millis it can be clean by the
		// cleaning thread
		dataSource.setMinEvictableIdleTimeMillis(300000);

		// The maximum number of millis the getConnection() on data source will wait
		dataSource.setMaxWait(5000);
		dataSource.setDefaultAutoCommit(false);

		dataSourceMap.put(config.getName(), dataSource);
		log.info("created data source "+config.getName()+" end");			
	}

	public Connection getConnection(String dbName) throws SQLException
	{
		DataSource ds = dataSourceMap.get(dbName);
		
		if(ds == null)
		{
			log.warn("No data source with name: "+dbName+", need to call createDataSource() to create data source. Will give null");
			return null;
		}
		
		return ds.getConnection();
	}
}
