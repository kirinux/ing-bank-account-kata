package fr.ing.bank.dao;

import fr.ing.bank.model.Transaction;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TransactionDao {

    private Map<String, List<Transaction>> transactions = new HashMap<>();

    public void addTransaction(Transaction transaction) {
        String accountNumber = transaction.getAccountNumber();
        if (!transactions.containsKey(accountNumber)) {
            transactions.put(accountNumber, new ArrayList<>());
        }
        transactions.get(accountNumber).add(transaction);
    }

    public List<Transaction> getTransactions(String accountNumber) {
        return transactions.get(accountNumber);
    }
}
