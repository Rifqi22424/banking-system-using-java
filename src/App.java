import java.util.*;

public class App {
    public static void main(String[] args) {
        BankingSystem bankingSystem = new BankingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Sistem Perbankan ===");
            System.out.println("1. Buat Rekening");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Cek Saldo");
            System.out.println("6. Riwayat Transaksi");
            System.out.println("7. Keluar");
            System.out.print("Masukkan pilihan Anda: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    buatRekening(bankingSystem, scanner);
                    break;
                case 2:
                    deposit(bankingSystem, scanner);
                    break;
                case 3:
                    withdraw(bankingSystem, scanner);
                    break;
                case 4:
                    transfer(bankingSystem, scanner);
                    break;
                case 5:
                    cekSaldo(bankingSystem, scanner);
                    break;
                case 6:
                    riwayatTransaksi(bankingSystem, scanner);
                    break;
                case 7:
                    System.out.println("Keluar...");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }

    private static void buatRekening(BankingSystem bankingSystem, Scanner scanner) {
        System.out.println("Pilih jenis rekening:");
        System.out.println("1. Rekening Tabungan");
        System.out.println("2. Rekening Giro");
        System.out.print("Masukkan pilihan Anda: ");
        int accountTypeChoice = scanner.nextInt();
        scanner.nextLine();
        if (accountTypeChoice == 1) {
            buatRekeningTabungan(bankingSystem, scanner);
        } else if (accountTypeChoice == 2) {
            buatRekeningGiro(bankingSystem, scanner);
        } else {
            System.out.println("Pilihan tidak valid.");
        }
    }

    private static void buatRekeningTabungan(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening: ");
        String savingsAccountNumber = scanner.nextLine();
        System.out.print("Masukkan nama pemegang rekening: ");
        String savingsHolderName = scanner.nextLine();
        System.out.print("Masukkan saldo awal: ");
        double savingsInitialBalance = scanner.nextDouble();
        System.out.print("Masukkan saldo minimum: ");
        double savingsMinimumBalance = scanner.nextDouble();
        System.out.print("Masukkan tingkat bunga: ");
        double savingsInterestRate = scanner.nextDouble();
        bankingSystem.addAccount(new SavingsAccount(savingsAccountNumber, savingsHolderName, savingsInitialBalance, savingsMinimumBalance, savingsInterestRate));
        System.out.println("Rekening Tabungan berhasil dibuat.");
    }

    private static void buatRekeningGiro(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening: ");
        String currentAccountNumber = scanner.nextLine();
        System.out.print("Masukkan nama pemegang rekening: ");
        String currentHolderName = scanner.nextLine();
        System.out.print("Masukkan saldo awal: ");
        double currentInitialBalance = scanner.nextDouble();
        System.out.print("Masukkan batas overdraft: ");
        double currentOverdraftLimit = scanner.nextDouble();
        System.out.print("Masukkan biaya transaksi: ");
        double currentTransactionFee = scanner.nextDouble();
        bankingSystem.addAccount(new CurrentAccount(currentAccountNumber, currentHolderName, currentInitialBalance, currentOverdraftLimit, currentTransactionFee));
        System.out.println("Rekening Giro berhasil dibuat.");
    }

    private static void deposit(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening: ");
        String depositAccountNumber = scanner.nextLine();
        System.out.print("Masukkan jumlah deposit: ");
        double depositAmount = scanner.nextDouble();
        bankingSystem.getAccount(depositAccountNumber).deposit(depositAmount);
    }

    private static void withdraw(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening: ");
        String withdrawAccountNumber = scanner.nextLine();
        System.out.print("Masukkan jumlah withdraw: ");
        double withdrawAmount = scanner.nextDouble();
        bankingSystem.getAccount(withdrawAccountNumber).withdraw(withdrawAmount);
    }

    private static void transfer(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening asal: ");
        String sourceAccountNumber = scanner.nextLine();
        System.out.print("Masukkan nomor rekening tujuan: ");
        String destinationAccountNumber = scanner.nextLine();
        System.out.print("Masukkan jumlah transfer: ");
        double transferAmount = scanner.nextDouble();
        bankingSystem.transfer(sourceAccountNumber, destinationAccountNumber, transferAmount);
    }

    private static void cekSaldo(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening: ");
        String checkBalanceAccountNumber = scanner.nextLine();
        Account checkBalanceAccount = bankingSystem.getAccount(checkBalanceAccountNumber);
        if (checkBalanceAccount != null) {
            System.out.println("Nomor Rekening: " + checkBalanceAccount.getAccountNumber());
            System.out.println("Nama Pemegang Rekening: " + checkBalanceAccount.getHolderName());
            System.out.println("Saldo: $" + checkBalanceAccount.getBalance());
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }

    private static void riwayatTransaksi(BankingSystem bankingSystem, Scanner scanner) {
        System.out.print("Masukkan nomor rekening: ");
        String transactionAccountNumber = scanner.nextLine();
        Account account = bankingSystem.getAccount(transactionAccountNumber);
        if (account != null) {
            List<Transaction> transactions = account.getTransactionHistory();
            System.out.println("Riwayat Transaksi untuk Rekening: " + transactionAccountNumber);
            for (Transaction transaction : transactions) {
                System.out.println("ID Transaksi: " + transaction.getTransactionId());
                System.out.println("Rekening Asal: " + transaction.getSourceAccountNumber());
                System.out.println("Rekening Tujuan: " + transaction.getDestinationAccountNumber());
                System.out.println("Jumlah: $" + transaction.getAmount());
                System.out.println("Waktu: " + transaction.getTimeStamp());
                System.out.println("--------------------");
            }
        } else {
            System.out.println("Rekening tidak ditemukan.");
        }
    }
}