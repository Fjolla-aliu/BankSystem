import java.util.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank("Bank", 10.0, 0.05);

        // Creating accounts
        bank.createAccount(1, "Fjolla");
        bank.createAccount(2, "Filan");

        // Deposit
        bank.deposit(1, 110.0);

        // Withdraw
        bank.withdraw(1, 60.0);

        // Perform transaction
        bank.performTransaction(1, 2, 30.0, "Payment");

        // List transactions for an account
        bank.listTransactions(1);

        // Check account balance
        bank.checkBalance(1);

        // List all accounts
        bank.listAccounts();

        // Check bank total transaction fee amount
        System.out.println("Total transaction fee amount: $" + bank.getTotalTransactionFeeAmount());

        // Check bank total transfer amount
        System.out.println("Total transfer amount: $" + bank.getTotalTransferAmount());
    }
}