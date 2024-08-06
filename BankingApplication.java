package myPackage;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Define custom exceptions
class NegativeDepositException extends Exception {
    public NegativeDepositException(String message) {
        super(message);
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}

// AccountManager class
class AccountManager {
    private Map<String, Double> accounts = new HashMap<>();

    public void createAccount(String accountNumber, double initialBalance) throws InvalidAccountException {
        if (accountNumber == null || accountNumber.isEmpty()) {
            throw new InvalidAccountException("Invalid account number.");
        }
        accounts.put(accountNumber, initialBalance);
        System.out.println("Account created successfully.");
    }

    public void deposit(String accountNumber, double amount) throws NegativeDepositException, InvalidAccountException {
        if (amount <= 0) {
            throw new NegativeDepositException("Deposit amount must be positive.");
        }
        if (!accounts.containsKey(accountNumber)) {
            throw new InvalidAccountException("Account not found.");
        }
        double newBalance = accounts.get(accountNumber) + amount;
        accounts.put(accountNumber, newBalance);
        System.out.println("Deposited: " + amount + ", New Balance: " + newBalance);
    }

    public void withdraw(String accountNumber, double amount) throws InsufficientFundsException, InvalidAccountException {
        if (!accounts.containsKey(accountNumber)) {
            throw new InvalidAccountException("Account not found.");
        }
        double currentBalance = accounts.get(accountNumber);
        if (amount > currentBalance) {
            throw new InsufficientFundsException("Insufficient funds. Available balance: " + currentBalance);
        }
        double newBalance = currentBalance - amount;
        accounts.put(accountNumber, newBalance);
        System.out.println("Withdrew: " + amount + ", New Balance: " + newBalance);
    }

    public double getBalance(String accountNumber) throws InvalidAccountException {
        if (!accounts.containsKey(accountNumber)) {
            throw new InvalidAccountException("Account not found.");
        }
        return accounts.get(accountNumber);
    }
}

// Main class
public class BankingApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager accountManager = new AccountManager();
        boolean exit = false;

        while (!exit) {
            System.out.println("\nBanking Application Menu:");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount(scanner, accountManager);
                    break;
                case 2:
                    performDeposit(scanner, accountManager);
                    break;
                case 3:
                    performWithdrawal(scanner, accountManager);
                    break;
                case 4:
                    checkBalance(scanner, accountManager);
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void createAccount(Scanner scanner, AccountManager accountManager) {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter initial balance: ");
            double initialBalance = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            accountManager.createAccount(accountNumber, initialBalance);
        } catch (InvalidAccountException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void performDeposit(Scanner scanner, AccountManager accountManager) {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter amount to deposit: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            accountManager.deposit(accountNumber, amount);
        } catch (InvalidAccountException | NegativeDepositException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void performWithdrawal(Scanner scanner, AccountManager accountManager) {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            System.out.print("Enter amount to withdraw: ");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            accountManager.withdraw(accountNumber, amount);
        } catch (InvalidAccountException | InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void checkBalance(Scanner scanner, AccountManager accountManager) {
        try {
            System.out.print("Enter account number: ");
            String accountNumber = scanner.nextLine();
            double balance = accountManager.getBalance(accountNumber);
            System.out.println("Current Balance: " + balance);
        } catch (InvalidAccountException e) {
            System.out.println(e.getMessage());
        }
    }
}
