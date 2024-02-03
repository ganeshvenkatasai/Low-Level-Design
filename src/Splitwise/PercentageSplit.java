package Splitwise;

public class PercentageSplit implements Split{
    @Override
    public double calculateAmount(double amount, int noOfUsers, double share){
        return amount * (share/100);
    }
}
