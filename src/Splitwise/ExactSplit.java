package Splitwise;

public class ExactSplit implements Split{
    @Override
    public double calculateAmount(double amount, int noOfUsers, double share){
        return share;
    }
}
