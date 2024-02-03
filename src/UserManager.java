import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UserManager {
    static int userCount = 4;
    public void initializeUsers(Map<Integer,User> userMap){
        String user[] = {"Ganesh", "Pavan", "Sai", "Rahul"};
        for(int i=0; i<user.length; i++) {
            userMap.put(i+1, new User(i+1, user[i]));
        }
    }
    public void showUsers(Map<Integer,User> userMap){
        for (Map.Entry<Integer, User> entry : userMap.entrySet()) {
            Integer key = entry.getKey();
            User user = entry.getValue();
            System.out.println("UserId: " + key + ", User Name: " + user.name);
        }
    }
    public int addUser(String userName, Map<Integer,User> userMap){
        userCount++;
        userMap.put(userCount, new User(userCount, userName));
        return userCount;
    }
    public String deleteUser(int userId, Map<Integer,User> userMap){
        userCount--;
        String userName = userMap.get(userId).name;
        userMap.remove(userId);
        return userName;
    }
    public void editUser(User user, Map<Integer,User> userMap){
        userMap.put(user.id, user);
    }
}
