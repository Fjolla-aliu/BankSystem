import java.util.*;

public class Bank {
    private String bankName;
    private List<Account> accounts;
    private List<Transaction> transactions;
    private double totalTransactionFeeAmount;
    private double totalTransferAmount;
    private double transactionFlatFeeAmount;
    private double transactionPercentFeeValue;

    public Bank(String bankName, double transactionFlatFeeAmount, double transactionPercentFeeValue) {
        this.bankName = bankName;
        this.accounts = new ArrayList<>();
        this.transactions = new ArrayList<>();
        this.totalTransactionFeeAmount = 0.0;
        this.totalTransferAmount = 0.0;
        this.transactionFlatFeeAmount = transactionFlatFeeAmount;
        this.transactionPercentFeeValue = transactionPercentFeeValue;
    }

    public void createAccount(int accountId, String userName) {
        Account account = new Account(accountId, userName);
        accounts.add(account);
    }

    public void deposit(int accountId, double amount) {
        Account account = findAccount(accountId);
        if (account != null) {
            account.deposit(amount);
            System.out.println("Deposit successful: $" + amount + " deposited into account " + accountId);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdraw(int accountId, double amount) {
        Account account = findAccount(accountId);
        if (account != null) {
            try {
                account.withdraw(amount);
                System.out.println("Withdrawal successful: $" + amount + " withdrawn from account " + accountId);
            } catch (FundsException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void performTransaction(int fromAccountId, int toAccountId, double amount, String transactionReason) {
        Account fromAccount = findAccount(fromAccountId);
        Account toAccount = findAccount(toAccountId);
        if (fromAccount != null && toAccount != null) {
            try {
                double transactionAmount = calculateTransactionAmount(amount);
                fromAccount.performTransaction(transactionAmount, transactionReason);
                toAccount.deposit(transactionAmount);
                transactions.add(new Transaction(transactionAmount, fromAccountId, toAccountId, transactionReason));
                totalTransferAmount += transactionAmount;
                totalTransactionFeeAmount += (amount - transactionAmount);
            } catch (FundsException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Account not found.");
        }
    }

    public void listTransactions(int accountId) {
        for (Transaction transaction : transactions) {
            if (transaction.getOriginatingAccountId() == accountId
                    || transaction.getResultingAccountId() == accountId) {
                System.out.println(
                        "Amount: $" + transaction.getAmount() + ", From: " + transaction.getOriginatingAccountId() +
                                ", To: " + transaction.getResultingAccountId() + ", Reason: "
                                + transaction.getTransactionReason());
            }
        }
    }

    public void checkBalance(int accountId) {
        Account account = findAccount(accountId);
        if (account != null) {
            System.out.println("Account balance for account " + accountId + " is: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public void listAccounts() {
        System.out.println("List of accounts in " + bankName + ":");
        for (Account account : accounts) {
            System.out.println("Account ID: " + account.getAccountId() + ", User: " + account.getUserName());
        }
    }

    public double getTotalTransactionFeeAmount() {
        return totalTransactionFeeAmount;
    }

    public double getTotalTransferAmount() {
        return totalTransferAmount;
    }

    private Account findAccount(int accountId) {
        for (Account account : accounts) {
            if (account.getAccountId() == accountId) {
                return account;
            }
        }
        return null;
    }

    private double calculateTransactionAmount(double amount) {
        double transactionAmount = amount;
        if (transactionFlatFeeAmount > 0) {
            transactionAmount -= transactionFlatFeeAmount;
        }
        if (transactionPercentFeeValue > 0) {
            transactionAmount -= (amount * transactionPercentFeeValue / 100);
        }
        return transactionAmount;
    }
}
