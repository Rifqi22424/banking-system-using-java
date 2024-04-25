import java.util.*;

public abstract class Account implements TransferProcessor {
    protected String accountNumber;
    protected String holderName;
    protected double balance;
    protected List<Transaction> transactionHistory;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount);
}
