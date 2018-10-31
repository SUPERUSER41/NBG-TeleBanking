package nbg.telebanking.models;

import java.util.Date;
import java.util.InputMismatchException;

public class Transaction {
    private int transactionId;
    private String transType;
    private Date date;
    private double transAmt;
    private String description;

    public Transaction() throws InputMismatchException {
        this.transactionId = 0;
        this.transType = "";
        this.date = null;
        this.transAmt = 0.0;
        this.description = "";
    }

    public Transaction(int intransaction, String intransType, Date indate, double intransAmt, String indecription) throws InputMismatchException {
        this.transactionId = intransaction;
        this.transType = intransType;
        this.date = indate;
        this.transAmt = intransAmt;
        this.description = indecription;
    }

    public Transaction(Transaction obj) throws InputMismatchException {
        this.transactionId = obj.getTransactionId();
        this.transType = obj.getTransType();
        this.date = obj.getDate();
        this.transAmt = obj.getTransAmt();
        this.description = obj.getDescription();
    }


    public int getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(int transactionId) {
        if (transactionId == -1) {
            this.transactionId = 0;
            throw new InputMismatchException("Transaction Id cannot be -1");
        }
        this.transactionId = transactionId;
    }

    public String getTransType() {
        return transType;
    }


    public void setTransType(String transType) {
        if (transType.equals(null)) {
            throw new InputMismatchException("Transactin type field cannot be empty.");
        } else if (!transType.equals("credit") || !transType.equals("CREDIT") || !transType.equals("Credit") || !transType.equals("debit") || !transType.equals("DEBIT") || !transType.equals("Debit")) {
            throw new InputMismatchException("Transaction type must be either Credit or Debit");
        }
        this.transType = transType;
    }


    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public double getTransAmt() {
        return transAmt;
    }

    public void setTransAmt(double transAmt) {
        if (transAmt == -1) {
            throw new InputMismatchException("Transaction Amount cannot be equal to -1");
        }
        this.transAmt = transAmt;
    }


    public String getDescription() {
        return description;
    }


    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Transaction [transactionId=" + getTransactionId() + ", transType=" + getTransType() + ", date=" + getDate()
                + ", transAmt=" + getTransAmt() + ", description=" + getDescription() + "]";
    }

}
