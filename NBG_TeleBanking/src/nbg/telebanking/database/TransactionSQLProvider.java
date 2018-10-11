package nbg.telebanking.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nbg.telebanking.models.User;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nbg.telebanking.models.Transaction;

public class TransactionSQLProvider extends SQLProvider<Transaction>{

	private static Logger logger = LogManager.getLogger(UserSQLProvider.class);

	public static final String TABLE_NAME = "nbg_transactions";
	
	@Override
	protected void initSQLDatabase() {
		try {
			statement = connection.createStatement();
			query = "create table if not exists "
					+ TABLE_NAME
					+ " (transaction_id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "amount INTEGER NOT NULL,"
					+ "description varchar(50),"
					+ "type varchar(50) NOT NULL, "
					+ "date DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL";
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
	public List<Transaction> selectAll() {
		List<Transaction> transactions = new ArrayList<Transaction>();
		try {
			statement = connection.createStatement();
			query = "SELECT * FROM " +TABLE_NAME;
			
			logger.debug(query);
			result = statement.executeQuery(query);
			while (result.next()) {
					Transaction transaction = new Transaction(); 
					transaction.setAmount(result.getInt(1));
					transaction.setDescription(result.getString(2));
					transaction.setType(result.getString(3));
					transaction.setDate(result.getDate(4));
					
					transactions.add(transaction);		
				}
			logger.debug("Retrieved "+transactions.size()+" transactions");
			logger.debug(TABLE_NAME+" table already exists");
		}catch(SQLException  e) {
			logger.error("Unable to retrieve transactions");

		}

		return transactions;
	}

	@Override
	public Transaction get(int id) {
		Transaction transaction = null;
		try{
			statement = connection.createStatement();
//			query = "SELECT t.transaction_id, first_name "+TABLE_NAME+" where user_id = "+id+";";
			logger.debug("QUERY : "+query);
			result = statement.executeQuery(query);
			while(result.next()){
				transaction = new Transaction();
				transaction.setTransID(result.getInt(1));
				transaction.setAmount(result.getInt(2));
				transaction.setDescription(result.getString(3));
				transaction.setType(result.getString(4));
				transaction.setDate(result.getDate(5));
			}
			return transaction;
			
			
		}catch(SQLException e){
			logger.error("Unable to retrieve user with id "+id,e);
				
		}
		return transaction;
		
	}

	@Override
	public int update(Transaction item, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMultiple(int[] ids) 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int add(Transaction item) 
	{
		try{
			query = "INSERT INTO "+TABLE_NAME+ "(amount, description, type, date)  VALUES (? ? ? ?) ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setInt(1, item.getAmount());
			ps.setString(2, item.getDescription());
			ps.setString(3, item.getType());
			ps.setDate(4, item.getDate());
			return ps.executeUpdate();
					
    	}catch(SQLException e){
			logger.error("Unable to add transaction",e);
		}
		
		return 0;
	}
	
	public User custDashInfo(int id) 
	{
		//current balance field for users is missing from query
		//needs a thread extension/runnable implementation first
		//float currBal;
		User newUser = null;
		try{
			statement = connection.createStatement();
			String query = "SELECT first_name, last_name, image_url FROM nbg_users"+
					" INNER JOIN nbg_transactions "+ 
					" ON nbg_users.user_id=nbg_transactions.user_id "+
					" where nbg_user.user_id = "+id;
			logger.debug("QUERY : "+query);
			result = statement.executeQuery(query);
			while(result.next())			
			{
				newUser = new User();
				newUser.setfName(result.getString(1));
				newUser.setlName(result.getString(2));
				newUser.setImageUrl(result.getString(3));				
			}
			return newUser;//getters for User object can be called to display the necessary details to the gui
			
			
		}catch(SQLException e)
		{
			logger.error("Unable to retrieve user with id "+id+ " because of error "+e);
				
		}
		return newUser;
		
	}
	
	/*public Transaction manDashInfo()
	{
		//still working on the logic for this method
		return null;
	}*/

}
