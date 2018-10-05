package nbg.telebanking.models;

import java.io.Serializable;
import java.sql.Date;

public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int transID;
	private int amount;
	private String description;
	private String type;
	private Date date;
	
	public Transaction() {
		this(0, 0, "", "",null);
	}
	
	public Transaction(int transID, int amount, String description, String type, Date created_at) {
		super();
		this.transID = transID;
		this.amount = amount;
		this.description = description;
		this.type = type;
		this.date = created_at;
	}

	public int getTransID() {
		return transID;
	}

	public void setTransID(int transID) {
		this.transID = transID;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date created_at) {
		this.date = created_at;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+ " [id=" + transID + ", type=" + type + ", dateCreated="
				+ date + "]";
	}
	

}
