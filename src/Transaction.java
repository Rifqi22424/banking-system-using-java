import java.util.*;

class Transaction {
    private String transactionId;
    private String sourceAccountNumber;
    private String destinationNumber;
    private double amount;
    private Date timeStamp;

    public Transaction(String transactionId, String sourceAccountNumber, String destinationNumber, double amount) {
        this.transactionId = transactionId;
        this.sourceAccountNumber = sourceAccountNumber;
        this.destinationNumber = destinationNumber;
        this.amount = amount;
        this.timeStamp = new Date();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getSourceAccountNumber() {
        return sourceAccountNumber;
    }

    public void setSourceAccountNumber(String sourceAccountNumber) {
        this.sourceAccountNumber = sourceAccountNumber;
    }

    public String getDestinationAccountNumber() {
        return destinationNumber;
    }

    public void setDestinationAccountNumber(String destinationNumber) {
        this.destinationNumber = destinationNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    
}