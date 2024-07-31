package myPackage;


import java.util.Scanner;

//Abstract base class representing a BankAccount
abstract class BankAccount 
{
 private String accountNumber;
 private double balance;
 private Customer customer;

 public BankAccount(String accountNumber, Customer customer) 
 {
     this.accountNumber = accountNumber;
     this.customer = customer;
     this.balance = 0.0; // Default balance
 }

 public abstract void deposit(double amount);
 public abstract void withdraw(double amount);

 public double getBalance() 
 {
     return balance;
 }

 public String getAccountNumber() 
 {
     return accountNumber;
 }

 public Customer getCustomer() 
 {
     return customer;
 }

 public void displayAccountInfo() 
 {
     System.out.println("Customer: " + customer.getName());
     System.out.println("Account Number: " + accountNumber);
     System.out.println("Current Balance: $" + balance);
 }

 protected void updateBalance(double amount) 
 {
     balance += amount;
 }
}

//Concrete subclass for SavingsAccount
class SavingsAccount extends BankAccount 
{
 private double interestRate;

 public SavingsAccount(String accountNumber, Customer customer, double interestRate) {
     super(accountNumber, customer);
     this.interestRate = interestRate;
 }

 @Override
 public void deposit(double amount) 
 {
     if (amount > 0) 
     {
         updateBalance(amount);
         System.out.println("Deposited: $" + amount);
     } else 
     {
         System.out.println("Invalid deposit amount");
     }
 }

 @Override
 public void withdraw(double amount) 
 {
     if (amount > 0 && amount <= getBalance()) 
     {
         updateBalance(-amount);
         System.out.println("Withdrawn: $" + amount);
     } else if (amount > getBalance()) 
     {
         System.out.println("Insufficient balance");
     } else 
     {
         System.out.println("Invalid withdrawal amount");
     }
 }

 public void applyInterest() 
 {
     double interest = getBalance() * interestRate / 100;
     deposit(interest);
     System.out.println("Interest applied: $" + interest);
 }
}

//Concrete subclass for CheckingAccount
class CheckingAccount extends BankAccount 
{
 private double overdraftLimit;

 public CheckingAccount(String accountNumber, Customer customer, double overdraftLimit) {
     super(accountNumber, customer);
     this.overdraftLimit = overdraftLimit;
 }

 @Override
 public void deposit(double amount) 
 {
     if (amount > 0) 
     {
         updateBalance(amount);
         System.out.println("Deposited: $" + amount);
     } else {
         System.out.println("Invalid deposit amount");
     }
 }

 @Override
 public void withdraw(double amount) 
 {
     if (amount > 0 && (getBalance() - amount >= -overdraftLimit)) 
     {
         updateBalance(-amount);
         System.out.println("Withdrawn: $" + amount);
     } else if (amount > getBalance() + overdraftLimit) 
     {
         System.out.println("Overdraft limit exceeded");
     } else 
     {
         System.out.println("Invalid withdrawal amount");
     }
 }
}

//Class representing a Customer
class Customer 
{
 private String name;
 private String accountNumber;

 public Customer(String name, String accountNumber) 
 {
     this.name = name;
     this.accountNumber = accountNumber;
 }

 public String getName() 
 {
     return name;
 }

 public String getAccountNumber() 
 {
     return accountNumber;
 }
}

//Main class to interact with the user
public class Main 
{
 public static void main(String[] args)
 {
     Scanner scanner = new Scanner(System.in);
     
     System.out.println("Enter customer name:");
     String name = scanner.nextLine();
     
     System.out.println("Enter account number:");
     String accountNumber = scanner.nextLine();
     
     // Creating a customer
     Customer customer = new Customer(name, accountNumber);
     
     // Select account type
     System.out.println("Select account type (1: Savings, 2: Checking):");
     int accountType = scanner.nextInt();
     scanner.nextLine(); // Consume newline
     
     BankAccount account;
     if (accountType == 1)
     {
         System.out.println("Enter interest rate for savings account:");
         double interestRate = scanner.nextDouble();
         scanner.nextLine(); // Consume newline
         account = new SavingsAccount(accountNumber, customer, interestRate);
     } else if (accountType == 2) 
     {
         System.out.println("Enter overdraft limit for checking account:");
         double overdraftLimit = scanner.nextDouble();
         scanner.nextLine(); // Consume newline
         account = new CheckingAccount(accountNumber, customer, overdraftLimit);
     } else 
     {
         System.out.println("Invalid account type selected.");
         return;
     }
     
     boolean exit = false;
     while (!exit) 
     {
         System.out.println("\nChoose an option:");
         System.out.println("1: Deposit");
         System.out.println("2: Withdraw");
         System.out.println("3: Display Account Info");
         System.out.println("4: Exit");
         int choice = scanner.nextInt();
         scanner.nextLine(); // Consume newline

         switch (choice) 
         {
             case 1:
                 System.out.println("Enter amount to deposit:");
                 double depositAmount = scanner.nextDouble();
                 scanner.nextLine(); // Consume newline
                 account.deposit(depositAmount);
                 break;
             case 2:
                 System.out.println("Enter amount to withdraw:");
                 double withdrawAmount = scanner.nextDouble();
                 scanner.nextLine(); // Consume newline
                 account.withdraw(withdrawAmount);
                 break;
             case 3:
                 account.displayAccountInfo();
                 break;
             case 4:
                 exit = true;
                 break;
             default:
                 System.out.println("Invalid choice.");
         }
     }

     scanner.close();
 }
}
