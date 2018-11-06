package nbg.telebanking.models;

import java.io.Serializable;
import java.util.Date;
import java.util.InputMismatchException;

public class User implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private int userId;
    protected String firstName;
    protected String lastName;
    protected String emailAddress;
    protected String password;
    protected Date createdAt;


    public User() throws InputMismatchException {
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.userId = 0;
        this.createdAt = null;
    }

    public User(String infirstName, String inLastname, String inemailAddress, int inuserID, Date createdAt) throws InputMismatchException {
        this.firstName = infirstName;
        this.lastName = inLastname;
        this.emailAddress = inemailAddress;
        this.userId = inuserID;
        this.createdAt = createdAt;
    }

    public User(User obj) throws InputMismatchException {
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.emailAddress = obj.getEmailAddress();
        this.userId = getUserId();
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        if (firstName.length() > 50) {
            this.firstName = null;
            throw new InputMismatchException("Error:Firstname cannot be more than 50 character long.");
        } else if (!firstName.matches("[a-zA-Z]*")) {
            this.lastName = null;

            throw new InputMismatchException("Error:Firstname cannot have any Integers");
        } else {
            this.firstName = firstName;
        }
    }

    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        if (lastName.length() > 50) {
            this.lastName = null;
            throw new InputMismatchException("Error:Lastname cannot be more than 50 character long.");
        } else if (!lastName.matches("[a-zA-Z]*")) {
            this.lastName = null;
            throw new InputMismatchException("Error:Lastname cannot have any Integers.");
        }
        this.lastName = lastName;

    }

    public String getEmailAddress() {
        return emailAddress;
    }


    public void setEmailAddress(String emailAddress) {
        if (emailAddress.length() > 50) {
            this.emailAddress = null;
            throw new InputMismatchException("Error: Emailaddress cannot be more than 50 character long.");
        } else if (emailAddress.contains("% $ # ^ & *")) {
            this.emailAddress = null;
            throw new InputMismatchException("Invaild emailAddress entered.");
        }
        this.emailAddress = emailAddress;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User [firstName=" + getFirstName() + ", lastName=" + getLastName() + ", emailAddress=" + getEmailAddress()
                + ", userId=" + getUserId() + "]";
    }

    public void show() {
        System.out.println(toString());
    }


}
