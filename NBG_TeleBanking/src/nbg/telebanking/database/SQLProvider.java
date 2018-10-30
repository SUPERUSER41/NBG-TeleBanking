/**
 * 
 */
package nbg.telebanking.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Daneil Greaves
 *
 */
public abstract class SQLProvider<T> {
	
	protected Connection connection = null;
	protected Statement statement = null;
	protected String query = null;
	protected ResultSet result = null;
	
	private static final String DRIVER = "com.mysql.jdbc.Driver";
	
	private static Logger logger = LogManager.getLogger(SQLProvider.class);
	
	
	public SQLProvider() {
		try {
			logger.trace("Attempting to connect to database, errors may occur");
			Class.forName(DRIVER); 
			
			
			String url = "jdbc:sqlite:nbg_telebanking.db"; 
			connection = DriverManager.getConnection(url);
			
			initSQLDatabase();
			
			logger.info("Connected to database");
			logger.debug( "Connected to database");

		}
		catch (SQLException e) {
			logger.error("Unable to connect to database",e);
		}
		catch (ClassNotFoundException e) {
			logger.error("Failed to load JDBC/OBDC Driver",e);
		}
		catch (NullPointerException e) {
			logger.error("Unable to find database",e);
		}
	}

	abstract protected void initSQLDatabase();
	abstract public int create(T t);
	abstract public int update(T t, int id);
	abstract public T retrieve(int id);
	abstract public List<T> retrieveAll();
	
	
	

}
