import java.util.UUID;

public class CurrentAccount extends Account {
    private double overdraftLimit;
    private double transactionFee;

    public CurrentAccount(String accountNumber, String holderName, double balance, double overdraftLimit, double transactionFee) {
        super(accountNumber, holderName, balance);
        this.overdraftLimit = overdraftLimit;
        this.transactionFee = transactionFee;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, accountNumber, amount);
            transactionHistory.add(transaction);
            System.out.println("Deposit sejumlah $" + amount + " berhasil. Saldo baru: $" + balance);
        } else {
            System.out.println("Jumlah deposit tidak valid.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && (balance - amount >= -overdraftLimit)) {
            balance -= amount;
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, accountNumber, -amount);
            transactionHistory.add(transaction);
            System.out.println("Withdraw sejumlah $" + amount + " berhasil. Saldo baru: $" + balance);
            balance -= transactionFee;
            System.out.println("Biaya transaksi ditagih. Saldo baru: $" + balance);
        } else {
            System.out.println("Dana tidak mencukupi atau jumlah penarikan tidak valid.");
        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            destinationAccount.balance += amount;

            Transaction transactionOutgoing = new Transaction(UUID.randomUUID().toString(), accountNumber, destinationAccount.accountNumber, -amount);
            Transaction transactionIncoming = new Transaction(UUID.randomUUID().toString(), accountNumber, destinationAccount.accountNumber, amount);

            transactionHistory.add(transactionOutgoing);
            destinationAccount.transactionHistory.add(transactionIncoming);

            System.out.println("Transfer sejumlah $" + amount + " ke rekening " + destinationAccount.getAccountNumber() + " berhasil.");
        } else {
            System.out.println("Dana tidak mencukupi atau jumlah transfer tidak valid.");
        }
    }
}
