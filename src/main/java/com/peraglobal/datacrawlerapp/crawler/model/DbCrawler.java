package com.peraglobal.datacrawlerapp.crawler.model;

public class DbCrawler extends Crawler {

	private DbConnection dbConnection;

	public DbConnection getDbConnection() {
		return dbConnection;
	}

	public void setDbConnection(DbConnection dbConnection) {
		this.dbConnection = dbConnection;
	}
	
}
