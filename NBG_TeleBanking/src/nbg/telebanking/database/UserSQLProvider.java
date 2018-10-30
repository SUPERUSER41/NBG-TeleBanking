package nbg.telebanking.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nbg.telebanking.models.User;


public class UserSQLProvider extends SQLProvider<User> 
{
	
	private static Logger logger = LogManager.getLogger(UserSQLProvider.class);

	public static final String TABLE_NAME = "nbg_user";
	

	@Override
	protected void initSQLDatabase() {
		try {
			statement = connection.createStatement();
			query = "CREATE TABLE IF NOT EXISTS "
					+ TABLE_NAME
					+ "user_id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "first_name varchar(50) NOT NULL,"
					+ "last_name varchar(50) NOT NULL,"
					+ "email varchar(50) NOT NULL,"
					+ "password varchar(50) NOT NULL,"
					+ "image_url varchar(50), "
					+ "created_at DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP, "
					+ "FOREIGN KEY(role_id) REFERENCES nbg_roles(role_id)";
			if (statement.execute(query)) {
				logger.debug(TABLE_NAME+" table created");
			} else {
				logger.debug(TABLE_NAME+" table does not need to be created");
			}
			logger.debug(TABLE_NAME+" table already exists");
		}catch(SQLException e) {
			logger.error("Unable to initialize SQL Database with table " + TABLE_NAME , e);
		}
		
	}
	
	@Override
	public int create(User user) {
		try{
			query = "INSERT INTO "+TABLE_NAME+ "(first_name, last_name, email, password)  VALUES (? ? ? ? ?) ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, user.getfName());
			ps.setString(2, user.getlName());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			return ps.executeUpdate();
					
    	}catch(SQLException e){
			logger.error("Unable to add user",e);
		}
		
		return 0;
	}
	
	@Override
	public int update(User user, int id) {
		try{
			String query = "UPDATE "+TABLE_NAME
					       + " SET image_url = ? "
					       + " WHERE id = ? ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(5, user.getImageUrl());
			return ps.executeUpdate();
			
    	}catch(SQLException e){
			logger.error("Unable to update user with id "+id,e);
		}
		
		return 0;
	}
	

	@Override
	public List<User> retrieveAll() {
		List<User> users = new ArrayList<User>();
		try {
			statement = connection.createStatement();
			query = "SELECT * FROM " +TABLE_NAME;
			
			logger.debug(query);
			result = statement.executeQuery(query);
			while (result.next()) {
					User user = new User(); 
					user.setUserID(result.getInt(0));
					user.setfName(result.getString(1));
					user.setfName(result.getString(2));
					user.setEmail(result.getString(3));
					user.setImageUrl(result.getString(5));
					user.setCreatedAt(result.getDate(6));
					users.add(user);		
				}
			logger.debug("Retrieved "+users.size()+" users");
			logger.debug(TABLE_NAME+" table already exists");
		}catch(SQLException  e) {
			logger.error("Unable to retrieve users");

		}
		return users;
	}

	@Override
	public User retrieve(int id) {
		User user = null;
		try{
			statement = connection.createStatement();
			query = "SELECT * FROM "+TABLE_NAME+" where user_id = "+id+";";
			logger.debug("QUERY : "+query);
			result = statement.executeQuery(query);
			while(result.next()){
				user = new User();
				user.setUserID(result.getInt(0));
				user.setfName(result.getString(1));
				user.setfName(result.getString(2));
				user.setEmail(result.getString(3));
				user.setImageUrl(result.getString(5));
			}
			
			return user;
			
			
		}catch(SQLException e){
			logger.error("Unable to retrieve user with id "+id,e);
				
		}
		return user;
	}

	

	


	
	public boolean userLogIn(User account)
	{
		int id=account.getUserID();
		String dbEmail="";
		String dbPw="";	
		boolean isValidUser=false;
		try
		{
			statement=connection.createStatement();
			String query="SELECT email, password, r.role_id FROM nbg_users "+
						" INNER JOIN nbg_roles r "+ 
						" ON nbg_users.user_id=nbg_roles.user_id"+
						"WHERE user_id="+id;
			result= statement.executeQuery(query);
			while(result.next())
			{
				dbEmail=result.getString(1);
				dbPw=result.getString(2);
				id=result.getInt(3);
			
				if(account.getEmail().equals(dbEmail))
				{
					if(account.getPassword().equals(dbPw))
					{
						if(account.getUserID()==id)
						{
							isValidUser=true;
						}
					}					
				}
				else
				{
					System.out.println("Invalid credentials, login failed");
				}			
			}
			return isValidUser;
		}
		catch(SQLException se)
		{
			logger.error("Cannot connect to database");
			System.out.println("Could not connect because of database failure");
			se.printStackTrace();
		}
		return isValidUser;
	}
	

}
