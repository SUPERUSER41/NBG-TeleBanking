package nbg.telebanking.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nbg.telebanking.models.Message;


public class MessageSQLProvider extends SQLProvider<Message>{
	private static Logger logger = LogManager.getLogger(UserSQLProvider.class);

	public static final String TABLE_NAME = "nbg_message";

	@Override
	protected void initSQLDatabase() {
		try {
			statement = connection.createStatement();
			query = "CREATE TABLE IF NOT EXISTS " 
					+ TABLE_NAME +
					" msg_id INTEGER PRIMARY KEY AUTOINCREMENT," + 
					" body varchar(500) NOT NULL, "+ 
					" query varchar (50) NOT NULL, " + 
					" created_at DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL," + 
					" user_id INTEGER NOT NULL," + 
					" FOREIGN KEY(user_id) REFERENCES nbg_users(user_id)";
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
	public int create(Message message) {
		try{
			query = "INSERT INTO "+TABLE_NAME+ "(body, query)  VALUES (?) ";
			PreparedStatement ps = connection.prepareStatement(query);
			ps.setString(1, message.getMsgBody());
			ps.setString(2, message.getQuery());
			return ps.executeUpdate();
					
    	}catch(SQLException e){
			logger.error("Unable to add user",e);
		}
		
		return 0;
	}

	@Override
	public int update(Message t, int id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Message retrieve(int id) {
		Message msg = null;
		try{
			statement = connection.createStatement();
			query = "SELECT * FROM "+TABLE_NAME+" where user_id = "+id+";";
			logger.debug("QUERY : "+query);
			result = statement.executeQuery(query);
			while(result.next()){
				msg = new Message();
				msg.setMsgID(result.getInt(0));
				msg.setMsgBody(result.getString(1));
				msg.setQuery(result.getString(2));
				msg.setDateSent(result.getDate(3));
			}
			return msg;
			
		}catch(SQLException e){
			logger.error("Unable to retrieve message with id "+id,e);
				
		}
		return msg;
	}

	@Override
	public List<Message> retrieveAll() {
		List<Message> msgs = new ArrayList<Message>();
		try {
			statement = connection.createStatement();
			query = "SELECT * FROM " +TABLE_NAME;
			logger.debug(query);
			result = statement.executeQuery(query);
			while (result.next()) {
					Message msg = new Message(); 
					msg.setMsgID(result.getInt(0));
					msg.setMsgBody(result.getString(1));
					msg.setQuery(result.getString(2));
					msg.setDateSent(result.getDate(3));
					msgs.add(msg);		
				}
			logger.debug("Retrieved "+msgs.size()+" users");
			logger.debug(TABLE_NAME+" table already exists");
		}catch(SQLException  e) {
			logger.error("Unable to retrieve messages");

		}
		return msgs;
	}

	

}
