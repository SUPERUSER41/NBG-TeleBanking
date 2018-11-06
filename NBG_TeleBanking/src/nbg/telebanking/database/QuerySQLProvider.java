package nbg.telebanking.database;

import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nbg.telebanking.models.Query;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.Connection;

public class QuerySQLProvider extends SQLProvider<Query>
{
	private static Logger logger = LogManager.getLogger(QuerySQLProvider.class);	 
    public static final String TABLE_NAME = "nbg_query";    
    private Connection con = null;
    private Statement statement=null;

	public QuerySQLProvider() 
	{
		// TODO Auto-generated constructor stub
		super();
        try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
        try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:8889/mbg_database", "root", "root");
			System.out.println("Connection was successful");
        } catch (SQLException e) {
        	System.out.println("Connection was unsuccesful");
			e.printStackTrace();
		}
 
	}
	@Override
	protected void initSQLDatabase() 
	{
		 try {
	            statement = con.createStatement();
	            if (statement
	                    .execute("create table if not exists "
	                            + TABLE_NAME+" (\n"
	                            + " query_id integer AUTO_INCREMENT PRIMARY KEY NOT NULL,\n"
	                            + " queryname varchar(50)\n"
	                            + ");"))
	            {
	                logger.debug("ITEM table created");
	            } else {
	                logger.debug("ITEM table does not need to be created");
	            }
	            logger.debug("ITEM table exists");
	        } catch (SQLException e) {
	            logger.error("Unable to initialize SQL Database", e);
	        }
		
	}

	@Override
	public int create(Query query) {
		return 0;
	}





	@Override
	public int update(Query item, int id) 
	{
		 try{
	            String query = "UPDATE "+TABLE_NAME
	                           + " SET queryname= ? "
	                           + " WHERE query_id = ? ";
	            PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, item.getQueryname());
	            item.getQuery_id();
	            ps.setInt(2,item.getQuery_id());
	            return ps.executeUpdate();
	                   
	        }catch(SQLException e){
	            logger.error("Unable to update transaction with id "+item.getQuery_id(),e);
	        }
	       
	        return 0;
	}

	@Override
	public Query retrieve(int id) {
		return null;
	}

	@Override
	public List<Query> retrieveAll() {
		return null;
	}



}
