package nbg.telebanking.models;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private int userID;
	private String fName;
	private String lName;
	private String email;
	private String password;
	private String imageUrl;
	private Date createdAt;
	
	public User() {
		this(0,"","","","","",null);
	}
	
	public User(int userID, String fName, String lName, String email, String password, String imageUrl,Date createdAt) {
		super();
		this.userID = userID;
		this.fName = fName;
		this.lName = lName;
		this.email = email;
		this.password = password;
		this.imageUrl = imageUrl;
		this.createdAt = createdAt;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+ " [id=" + userID + ", name=" + fName + lName + ", created_at="
				+ createdAt + "]";
	}
	
	

}
