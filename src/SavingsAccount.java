import java.util.UUID;

public class SavingsAccount extends Account {
    private double minimumBalance;
    private double interestRate;

    public SavingsAccount(String accountNumber, String holderName, double balance, double minimumBalance, double interestRate) {
        super(accountNumber, holderName, balance);
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, accountNumber, amount);
            transactionHistory.add(transaction);
            System.out.println("Deposit sejumlah $" + amount + " berhasil. Saldo baru: $" + balance);
            balance += balance * (interestRate / 100);
            System.out.println("Bunga ditambahkan. Saldo baru: $" + balance);
        } else {
            System.out.println("Jumlah deposit tidak valid.");
        }
    }

    @Override
    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance && balance - amount >= minimumBalance) {
            balance -= amount;
            Transaction transaction = new Transaction(UUID.randomUUID().toString(), accountNumber, accountNumber, -amount);
            transactionHistory.add(transaction);
            System.out.println("Withdraw sejumlah $" + amount + " berhasil. Saldo baru: $" + balance);
        } else {
            System.out.println("Dana tidak mencukupi atau jumlah penarikan tidak valid atau di bawah saldo minimum.");
        }
    }

    public void transfer(Account destinationAccount, double amount) {
        if (amount > 0 && amount <= balance && balance - amount >= minimumBalance) {
            balance -= amount;
            destinationAccount.balance += amount;

            Transaction transactionOutgoing = new Transaction(UUID.randomUUID().toString(), accountNumber, destinationAccount.accountNumber, -amount);
            Transaction transactionIncoming = new Transaction(UUID.randomUUID().toString(), accountNumber, destinationAccount.accountNumber, amount);

            transactionHistory.add(transactionOutgoing);
            destinationAccount.transactionHistory.add(transactionIncoming);

            System.out.println("Transfer sejumlah $" + amount + " ke rekening " + destinationAccount.getAccountNumber() + " berhasil.");
        } else {
            System.out.println("Dana tidak mencukupi atau jumlah penarikan tidak valid atau di bawah saldo minimum.");
        }
    }
}
