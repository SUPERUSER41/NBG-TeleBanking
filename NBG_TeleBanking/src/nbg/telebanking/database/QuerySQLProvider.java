package nbg.telebanking.database;

import java.sql.SQLException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import nbg.telebanking.models.Query;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class QuerySQLProvider extends SQLProvider<Query>
{

	private static Logger logger = LogManager.getLogger(QuerySQLProvider.class);
	
    public static final String TABLE_NAME = "nbg_query";    


	@Override
	protected void initSQLDatabase() {
		 try {
	            statement = connection.createStatement();
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
	public List<Query> selectAll() {
		List<Query> items = new ArrayList<Query>();
		 
        try {
            statement = connection.createStatement(); 
            String query = "SELECT * FROM " + TABLE_NAME;
                                                            
            logger.debug(query);
            result = statement.executeQuery(query); 
  
            while (result.next()) 
            {
                Query item = new Query(); 
                item.setQueryID(result.getInt(1));
                item.setQueryname(result.getString(2)); 
                items.add(item);
               
            }
            logger.debug("Retrieved "+items.size()+" query");
        } catch (SQLException e) {
            logger.error("Unable to retrieve query",e);
 
        }
        return items;
	}

	@Override
	public Query get(int id) {
		Query item = null;
        try{
            statement = connection.createStatement();
            String query = "select * from "+TABLE_NAME+" where query_id = "+id+";";
            logger.debug("QUERY : "+query);
            result = statement.executeQuery(query);
            while(result.next()){
                item = new Query();
                item.setQueryID(result.getInt(1)); 
                item.setQueryname(result.getString(2)); 
            }
           
            return item;
        }catch(SQLException e){
            logger.error("Unable to retrieve query with id "+item.getQueryID(),e);
               
        }
        return item;
	}

	@Override
	public int update(Query item, int id) {
		 try{
	            String query = "UPDATE "+TABLE_NAME
	                           + " SET queryname= ? "
	                           + " WHERE query_id = ? ";
	            PreparedStatement ps = connection.prepareStatement(query);
	            ps.setString(1, item.getQueryname());
	            item.getQueryID();
	            ps.setInt(2,item.getQueryID());
	            return ps.executeUpdate();
	                   
	        }catch(SQLException e){
	            logger.error("Unable to update transaction with id "+item.getQueryID(),e);
	        }
	       
	        return 0;
	}

	@Override
	public int delete(int id) 
	{
		try{
            String query = "DELETE FROM "+TABLE_NAME
                           + " WHERE query_id = ? ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1,id);
            return ps.executeUpdate();
                   
        }catch(SQLException e){
     }
       
        return 0;
	}

	@Override
	public int deleteMultiple(int[] ids) {
		try{
            String IdsConcatenated = Arrays.toString(ids).replace("[","").replace("]","");
            String query = "DELETE FROM "+TABLE_NAME
                           + " WHERE query_id in ("+IdsConcatenated+") ";
            PreparedStatement ps = connection.prepareStatement(query);
           
            return ps.executeUpdate();
                   
        }catch(SQLException e){
            logger.error("Unable to delete Query with ids : "+Arrays.toString(ids),e);
        }
       
        return 0;
	}

	@Override
	public int add(Query item) {
		try{
            String query = "INSERT INTO "+TABLE_NAME
                           + "(query_id, queryname)  VALUES (?,?) ";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setInt(1, item.getQueryID());
            ps.setString(2, item.getQueryname());
            return ps.executeUpdate();
                   
        }catch(SQLException e){
            logger.error("Unable to add query",e);
        }
       
        return 0;
	}

}
