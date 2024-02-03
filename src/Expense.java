import java.util.*;

public class Expense {

    double amount;
    int noOfUsers;
    int splitType;
    List<Split> splitList;
    Transaction transaction;
    public Expense(){
        this.amount = 0.0;
        this.noOfUsers = 0;
        this.splitType = 1;
        this.splitList = new ArrayList<>();
        this.transaction = new Transaction();

    }
    public void addExpense(Scanner scanner, Map<Integer,User> userMap, List<Transaction> transactions){
        int  senderId, receiverId;
        double splitAmount, share = 0.0;
        Split split = new EqualSplit();
        System.out.print("Enter your User Id : ");
        senderId = scanner.nextInt();
        System.out.print("Enter the type of Split : 1.Equal 2.Exact 3.Percentage : ");
        this.splitType = scanner.nextInt();
        System.out.print("How many users you paid : ");
        this.noOfUsers = scanner.nextInt();
        System.out.print("Enter amount : ");
        this.amount = scanner.nextDouble();
        System.out.print("Enter Ids of users ");
        switch (splitType) {
            case 1:
                System.out.println(" : ");
                split = new EqualSplit();
                break;
            case 2:
                System.out.println("along with exact amount : ");
                split = new ExactSplit();
                break;
            case 3:
                System.out.println("along with Percentages : ");
                split = new PercentageSplit();
                break;
        }

        for(int i=0; i<noOfUsers; i++){
            receiverId = scanner.nextInt();
            if(senderId == receiverId) continue;
            if(splitType != 1) share = scanner.nextInt();
            splitAmount = split.calculateAmount(amount, noOfUsers, share);
            transaction.addTransaction(new Transaction(senderId, receiverId, splitAmount), transactions);
            userMap.get(senderId).balance += splitAmount;
            userMap.get(receiverId).balance -= splitAmount;
        }
    }

    public void settleExpense(Map<Integer, User> userMap){

        PriorityQueue<Splitwise.Pair<Double, Integer>> credits = new PriorityQueue<>((a, b) -> Double.compare(b.getKey(), a.getKey()));
        PriorityQueue<Splitwise.Pair<Double, Integer>> debts = new PriorityQueue<>((a, b) -> Double.compare(b.getKey(), a.getKey()));

        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            int userId = entry.getKey();
            User user = entry.getValue();
            double amount = user.balance;
            if(amount>0) credits.add(new Splitwise.Pair<>(amount, userId));
            else debts.add(new Splitwise.Pair<>(Math.abs(amount), userId));
        }

        double amountTransferred;
        Vector<Transaction> transactions = new Vector<>();

        while (!credits.isEmpty()) {
            Splitwise.Pair<Double, Integer> receiver = credits.poll();
            Splitwise.Pair<Double, Integer> sender = debts.poll();

            amountTransferred = Math.min(sender.getKey(), receiver.getKey());

            transactions.add(new Transaction(sender.getValue(), receiver.getValue(), amountTransferred));

            sender = new Splitwise.Pair<>(sender.getKey() - amountTransferred, sender.getValue());
            receiver = new Splitwise.Pair<>(receiver.getKey() - amountTransferred, receiver.getValue());

            if (sender.getKey() != 0)
                debts.add(sender);

            if (receiver.getKey() != 0)
                credits.add(receiver);
        }

        for (Transaction transaction : transactions) {
            int senderId = transaction.senderId;
            int receiverId = transaction.receiverId;
            System.out.println(userMap.get(senderId).name+ " owes Rs." + transaction.amount + " to "+ userMap.get(receiverId).name);
        }
    }
}
