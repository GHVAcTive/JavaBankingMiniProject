public abstract class Account {
    protected String name;
    protected int accountNumber;
    protected double balance;
    private static int totalAccounts = 0;

    public Account(String name, int accountNumber, double balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
        totalAccounts++;
    }

    public abstract void withdraw(double amount);
    public abstract void deposit(double amount);

    public String getDetails() {
        return "Name: " + name + "\nAccount No: " + accountNumber + "\nBalance: ₹" + balance;
    }

    public static int getTotalAccounts() {
        return totalAccounts;
    }
}