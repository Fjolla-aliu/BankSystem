public class Transaction {
    private double amount;
    private int originatingAccountId;
    private int resultingAccountId;
    private String transactionReason;

    public Transaction(double amount, int originatingAccountId, int resultingAccountId, String transactionReason) {
        this.amount = amount;
        this.originatingAccountId = originatingAccountId;
        this.resultingAccountId = resultingAccountId;
        this.transactionReason = transactionReason;
    }

    public double getAmount() {
        return amount;
    }

    public int getOriginatingAccountId() {
        return originatingAccountId;
    }

    public int getResultingAccountId() {
        return resultingAccountId;
    }

    public String getTransactionReason() {
        return transactionReason;
    }

}
