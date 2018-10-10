package nbg.telebanking.models;

import java.io.Serializable;

public class Query implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int queryID;
	private String queryname;

	public Query() {
		this(0,"");
	}

	public Query(int queryID, String queryname) {
		super();
		this.queryID = queryID;
		this.queryname = queryname;
	}

	public int getQueryID() {
		return queryID;
	}

	public void setQueryID(int queryID) {
		this.queryID = queryID;
	}

	public String getQueryname() {
		return queryname;
	}

	public void setQueryname(String queryname) {
		this.queryname = queryname;
	}

	@Override
	public String toString() {
		return "Query [query_id=" + queryID + ", queryname=" + queryname + "]";
	}

}
