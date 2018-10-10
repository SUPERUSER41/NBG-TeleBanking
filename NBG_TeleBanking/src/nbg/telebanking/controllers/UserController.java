package nbg.telebanking.controllers;

import nbg.telebanking.database.UserSQLProvider;
import nbg.telebanking.models.User;

public class UserController {
	private UserSQLProvider userSQLProvider;
	
	public UserController() {
		userSQLProvider = new UserSQLProvider();
	}
	
	public boolean register() {
		User user = new User();
		userSQLProvider.add(user);
		return true;
	}

}
