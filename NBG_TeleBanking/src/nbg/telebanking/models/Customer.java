package nbg.telebanking.models;

import java.util.Date;
import java.util.InputMismatchException;


public class Customer extends User{

	private static final long serialVersionUID = 1L;
	private User user;
	private String imageURL;
	
	public Customer() throws InputMismatchException{
		super();
		this.user=null;
		this.imageURL="";
	}
	
	public Customer(String firstName, String lastName, String emailAddress, int userId, String password, String inimageURL, Date createdAt) throws InputMismatchException{
		super();
		this.imageURL=inimageURL;
	}
	
	public Customer(Customer obj) throws InputMismatchException{
		super(obj);
		this.imageURL=getImageURL();
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		if(user.equals(null)) {
			throw new InputMismatchException("User Information is needed.");
		}
		this.user = user;
	}

	/**
	 * @return the imageURL
	 */
	public String getImageURL() {
		return imageURL;
	}

	/**
	 * @param imageURL the imageURL to set
	 */
	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return ""+ super.toString() + ", imageURL=" + imageURL + "]";
	}

	
	
	

}
