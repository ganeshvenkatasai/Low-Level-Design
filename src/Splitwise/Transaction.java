package Splitwise;

import java.util.List;
import java.util.Map;

public class Transaction {
    int senderId;
    int receiverId;
    double amount;
    public Transaction(){
    }
    public Transaction(int senderId, int receiverId, double amount) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.amount = amount;
    }

    public void showTransactions(Map<Integer,User> userMap, List<Transaction> transactions){
        for(Transaction transaction : transactions){
            String senderName = userMap.get(transaction.senderId).name;
            String receiverName = userMap.get(transaction.receiverId).name;
            double amount = transaction.amount;
            System.out.println(senderName + " paid Rs." + amount + " to " + receiverName);
        }
    }

    public void addTransaction(Transaction transaction, List<Transaction> transactions){
        transactions.add(transaction);
    }
}
