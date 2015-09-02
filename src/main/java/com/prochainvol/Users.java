package com.prochainvol;

import java.util.Map;

import org.apache.log4j.Logger;

import com.prochainvol.json.JsonUtilities;
import com.prochainvol.json.ProchainvolObject;

public class Users extends ProchainvolObject {

	private static final Logger logger = Logger.getLogger(Constants.class
			.getName());

	private static Users instance;
	
	private static final String USERS_FILENAME = "/users.json";
	private static final String json = "{\"pratt\": {\"name\": \"pratt\",\"password\": \"corto\"},\"thomas\": {\"name\": \"thomas\",\"password\": \"sidolou1\"}}";
	
	public static Users getInstance() throws ProchainvolException {
		if (instance == null) {
			instance = JsonUtilities.readFromInputStream(Users.class, USERS_FILENAME);
		}
		return instance;
	}

	private Map<String, User> userMap;


	private Users() {}

	public boolean checkUser(String name, String password) {
		boolean result = false;
		User user = userMap.get(name);
		if (user != null && user.getPassword().equals(password)) {
			result = true;
		}
//		System.out.println(user + ", " + result);
		return result;
	}
	
	public User getUser(String userName) {
		return userMap.get(userName);
	}

}
