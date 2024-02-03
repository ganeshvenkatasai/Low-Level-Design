import java.util.*;

public class Splitwise {
    Scanner scanner;
    Map<Integer,User> userMap;
    UserManager userManager;
    Transaction transaction;
    List<Transaction> transactions;
    Expense expense;
    public Splitwise(){
        super();
        this.scanner = new Scanner(System.in);
        this.userMap = new HashMap<>();
        this.userManager = new UserManager();
        this.transaction = new Transaction();
        this.expense = new Expense();
        this.transactions = new ArrayList<>();
    }
    public void run(){

        userManager.initializeUsers(userMap);

        while(true){
            System.out.println("Enter your choise : 1.Add Expense 2.Settle Amount 3.Show Transactions 4.Show Users 5.Add User 6.Delete User 7.Edit User : ");

            int choise = scanner.nextInt();

            switch (choise) {
                case 1:
                    addExpense();
                    break;
                case 2:
                    settleAmount();
                    break;
                case 3:
                    showTransactions();
                    break;
                case 4:
                    showUsers();
                    break;
                case 5:
                    addUser();
                    break;
                case 6:
                    deleteUser();
                    break;
                case 7:
                    editUser();
                    break;
                default:
                    System.out.println("Please enter correct choise :( ");
            }
        }
    }

    private void addExpense() {
        expense.addExpense(scanner, userMap, transactions);
    }
    private void settleAmount() {
        expense.settleExpense(userMap);
    }
    public void showTransactions(){
        transaction.showTransactions(userMap, transactions);
    }
    public void showUsers(){
        userManager.showUsers(userMap);
    }
    public void addUser(){
        int userId;
        String userName;
        System.out.print("Enter User Name : ");
        userName = scanner.next();
        userId = userManager.addUser(userName, userMap);
        System.out.println("User " + userName + " Created with UserId : " + userId);
    }
    public void deleteUser(){
        System.out.print("Enter UserId to be Deleted : ");
        int userId = scanner.nextInt();
        String userName = userManager.deleteUser(userId, userMap);
        System.out.println("User " + userName + " Deleted");
    }
    public void editUser(){
        System.out.print("Enter UserId : ");
        int userId = scanner.nextInt();
        System.out.print("Enter User Name : ");
        String userName = scanner.next();
        userManager.editUser(new User(userId, userName), userMap);
        System.out.println("User " + userName + " Updated");
    }

    static class Pair<K, V> {
        private final K key;
        private final V value;
        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }
        K getKey() {
            return key;
        }
        V getValue() {
            return value;
        }

    }
}
