
//=========================================   (Project)  ===========================================
//---------------------------Designed by Sonia-khan:--------------------------------

import java.util.ArrayList;  // FIXED: Added import for ArrayList

//============================================🏦 BANK MANAGEMENT SYSTEM ================================================


abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected Customer customer;

    public Account(String accountNumber, Customer customer) {
        this.accountNumber = accountNumber;
        this.customer = customer;
        this.balance = 0.0;
    }

    public abstract void deposit(double amount);   
    public abstract void withdraw(double amount);  

    public double getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
}


class SavingAccount extends Account {
    private double interestRate;

    public SavingAccount(String accountNumber, Customer customer, double interestRate) {
        super(accountNumber, customer);
        this.interestRate = interestRate;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("\u001B[32m💰 " + customer.getName() + " deposited " + amount + " in " + accountNumber + "\u001B[0m");
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("\u001B[33m💵 " + customer.getName() + " withdrew " + amount + " from " + accountNumber + "\u001B[0m");
        } else {
            System.out.println("\u001B[31m⚠ Insufficient balance for " + customer.getName() + "\u001B[0m");
        }
    }
}


class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}


class Employee {
    protected String name;
    protected int id;
    protected String role;

    public Employee(String name, int id, String role) {
        this.name = name;
        this.id = id;
        this.role = role;
    }

    public void showInfo() {
        System.out.println("   " + role + ": " + name + " (ID: " + id + ")");
    }
}


class Manager extends Employee {
    public Manager(String name, int id) {
        super(name, id, "Manager");
    }

    public void hireEmployee(Bank bank, String empName, int empId, String role) {
        Employee e = new Employee(empName, empId, role);
        bank.addEmployee(e);
        System.out.println("\u001B[34m🧑‍💼 Manager " + name + " hired " + empName + " as " + role + "\u001B[0m");
    }

    public void approveLoan(String customerName, double amount) {
        System.out.println("\u001B[35m🏦 Manager " + name + " approved a loan of Rs." + amount + " for " + customerName + "\u001B[0m");
    }
}


class Bank {
    private String name;
    private ArrayList<Customer> customers = new ArrayList<>();
    private ArrayList<Employee> employees = new ArrayList<>();
    private ArrayList<Account> accounts = new ArrayList<>();

    public Bank(String name) {
        this.name = name;
        System.out.println("\u001B[36m==================================================\u001B[0m");
        System.out.println("\u001B[36m              Welcome to " + name + "               \u001B[0m");
        System.out.println("\u001B[36m==================================================\u001B[0m");
        System.out.println("\u001B[36m🏦 Bank created: " + name + "\u001B[0m");
    }

    public void addCustomer(Customer c) {
        customers.add(c);
        System.out.println("\u001B[32m✅ Customer added: " + c.getName() + "\u001B[0m");
    }

    public void addEmployee(Employee e) {
        employees.add(e);
        System.out.println("\u001B[32m✅ Employee hired: " + e.name + "\u001B[0m");
    }

    public void openAccount(Account a) {
        accounts.add(a);
        System.out.println("\u001B[32m🏦 Account opened: " + a.getAccountNumber() + "\u001B[0m");
    }

    public void showEmployees() {
        System.out.println("\n\u001B[34m=================== Employees ===================\u001B[0m");
        for (Employee e : employees) e.showInfo();
    }

    public void showCustomers() {
        System.out.println("\n\u001B[34m=================== Customers ===================\u001B[0m");
        for (Customer c : customers) System.out.println("   " + c.getName());
    }

    public void showAccounts() {
        System.out.println("\n\u001B[34m=================== Accounts ===================\u001B[0m");
        for (Account a : accounts)
            System.out.println("   " + a.getAccountNumber() + " | " + a.customer.getName() + " | Balance: " + a.getBalance());
    }
}


public class BankManagementSystem {
    public static void main(String[] args) {

        // 1. Create Bank
        Bank bank = new Bank("Sonia's National Bank");

        // 2. Hire Manager
        Manager manager = new Manager("Mr. Azhar Ahmed", 1001);
        bank.addEmployee(manager);

        // 3. Manager hires employees
        manager.hireEmployee(bank, "Mr. Ahmed Raza", 2001, "Cashier");
        manager.hireEmployee(bank, "Mr. Mohsin Ali", 2002, "Clerk");
        manager.hireEmployee(bank, "Mr. Abdul Kareem", 2003, "Cashier");
        manager.hireEmployee(bank, "Mr. Sajjad Ahmed", 2004, "Clerk");

        // 4. Add Customers
        Customer c1 = new Customer("Miss Amna");
        Customer c2 = new Customer("Miss Sumaiya");
        Customer c3 = new Customer("Miss Mishal");
        Customer c4 = new Customer("Miss Quratulain");
        Customer c5 = new Customer("Miss Maryam");

        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);
        bank.addCustomer(c4);
        bank.addCustomer(c5);

        // 5. Open Accounts
        SavingAccount a1 = new SavingAccount("ACC101", c1, 3.5);
        SavingAccount a2 = new SavingAccount("ACC102", c2, 4.0);
        SavingAccount a3 = new SavingAccount("ACC103", c3, 3.8);
        SavingAccount a4 = new SavingAccount("ACC104", c4, 4.2);
        SavingAccount a5 = new SavingAccount("ACC105", c5, 3.9);

        bank.openAccount(a1);
        bank.openAccount(a2);
        bank.openAccount(a3);
        bank.openAccount(a4);
        bank.openAccount(a5);

        // 6. Transactions
        a1.deposit(10000); a2.deposit(8000); a3.deposit(12000);
        a4.deposit(7000); a5.deposit(15000);

        a1.withdraw(2500); a2.withdraw(1500); a3.withdraw(5000);
        a4.withdraw(2000); a5.withdraw(4000);

        // 7. Loans
        manager.approveLoan(c1.getName(), 50000);
        manager.approveLoan(c5.getName(), 30000);

        // 8. Show Reports
        bank.showEmployees();
        bank.showCustomers();
        bank.showAccounts();

        System.out.println("\n\u001B[36m==================================================\u001B[0m");
        System.out.println("\u001B[36m           Thank you for using Sonia's Bank         \u001B[0m");
        System.out.println("\u001B[36m==================================================\u001B[0m");
    }
}
