public class SavingsAccount extends Account {
    private double interestRate;

    public SavingsAccount(String name, int accountNumber, double balance, double interestRate) {
        super(name, accountNumber, balance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount + (amount * interestRate / 100);
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nType: Savings\nInterest Rate: " + interestRate + "%";
    }
}