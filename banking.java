import java.util.*;

// Class representing a user account
class Account {
    private String accountNumber;
    private String accountHolderName;
    private double accountBalance;

    public Account(String accountNumber, String accountHolderName) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.accountBalance = 0.0;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void deposit(double amount) {
        accountBalance += amount;
        System.out.println("Deposit successful. Current balance: " + accountBalance);
    }

    public void withdraw(double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            System.out.println("Withdrawal successful. Current balance: " + accountBalance);
        } else {
            System.out.println("Insufficient funds. Current balance: " + accountBalance);
        }
    }

    public void transfer(Account recipient, double amount) {
        if (amount <= accountBalance) {
            accountBalance -= amount;
            recipient.deposit(amount);
            System.out.println("Transfer successful. Current balance: " + accountBalance);
        } else {
            System.out.println("Insufficient funds. Current balance: " + accountBalance);
        }
    }
}

// Class representing the Banking Information System
public class BankingInformationSystem {
    private static Scanner scanner = new Scanner(System.in);
    private static Map<String, Account> accounts = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Welcome to the Banking Information System!");

        while (true) {
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    performDeposit();
                    break;
                case 3:
                    performWithdrawal();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Thank you for using the Banking Information System. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        System.out.print("Enter account holder name: ");
        String accountHolderName = scanner.nextLine();

        Account account = new Account(accountNumber, accountHolderName);
        accounts.put(accountNumber, account);

        System.out.println("Account created successfully.");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.println("Account Holder Name: " + account.getAccountHolderName());
    }

    private static void performDeposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void performWithdrawal() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        Account account = accounts.get(accountNumber);
        if (account != null) {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline character

            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private static void performTransfer() {
        System.out.print("Enter sender account number: ");
        String senderAccountNumber = scanner.nextLine();

        Account senderAccount = accounts.get(senderAccountNumber);
        if (senderAccount != null) {
            System.out.print("Enter recipient account number: ");
            String recipientAccountNumber = scanner.nextLine();

            Account recipientAccount = accounts.get(recipientAccountNumber);
            if (recipientAccount != null) {
                System.out.print("Enter transfer amount: ");
                double amount = scanner.nextDouble();
                scanner.nextLine(); // Consume newline character

                senderAccount.transfer(recipientAccount, amount);
            } else {
                System.out.println("Recipient account not found.");
            }
        } else {
            System.out.println("Sender account not found.");
        }
    }
}
