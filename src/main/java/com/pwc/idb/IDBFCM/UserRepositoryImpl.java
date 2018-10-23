package com.pwc.idb.IDBFCM;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

public class UserRepositoryImpl implements UserRepositoryCustom {
 
	@Autowired
	private MongoTemplate mongpTemp;

	@Override
	public User findUserByEmailId(String email) {
		// TODO Auto-generated method stub
		return null;
	}
	 
//	@Override
//	//public User findUserByEmailId(String email) {
//	Query query = new Query();
//	//this.mongpTemp.find(query())
//	}

}
