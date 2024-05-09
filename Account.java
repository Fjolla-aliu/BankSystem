public class Account {
    private int accountId;
    private String userName;
    private double balance;

    public Account(int accountId, String userName) {
        this.accountId = accountId;
        this.userName = userName;
        this.balance = 0.0;
    }

    public int getAccountId() {
        return accountId;
    }

    public String getUserName() {
        return userName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws FundsException {
        if (amount > balance) {
            throw new FundsException("Not enough funds to make the withdrawal.");
        }
        balance -= amount;
    }

    public void performTransaction(double amount, String transactionReason) throws FundsException {
        withdraw(amount);
        System.out.println("Transaction successful: $" + amount + " " + transactionReason);
    }
}
