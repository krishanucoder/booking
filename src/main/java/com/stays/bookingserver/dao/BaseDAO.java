package com.stays.bookingserver.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.stays.bookingserver.helper.DbConnection;

@Repository
public class BaseDAO {

	@Autowired
	protected DbConnection dbConnection;
}
