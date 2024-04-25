import java.util.*;

class BankingSystem {
    private Map<String, Account> accounts;
    
    public BankingSystem() {
        accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account);
    }

    public Account getAccount(String accountNumber) {
        return accounts.getOrDefault(accountNumber, null);
    }

    public void transfer(String sourceAccountNumber, String destinationNumber, double amount) {
        Account sourceAccount = getAccount(sourceAccountNumber);
        Account destinationAccount = getAccount(destinationNumber);

        if (sourceAccount != null && destinationAccount != null) {
            sourceAccount.transfer(destinationAccount, amount);
        } else {
            System.out.println("Nomor rekening asal atau tujuan tidak valid.");
        }

    }
}
