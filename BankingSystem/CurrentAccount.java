public class CurrentAccount extends Account {
    private double overdraftLimit;

    public CurrentAccount(String name, int accountNumber, double balance, double overdraftLimit) {
        super(name, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount > balance + overdraftLimit) {
            throw new IllegalArgumentException("Exceeds overdraft limit");
        }
        balance -= amount;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public String getDetails() {
        return super.getDetails() + "\nType: Current\nOverdraft Limit: ₹" + overdraftLimit;
    }
}