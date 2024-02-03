public class EqualSplit implements Split{
    @Override
    public double calculateAmount(double amount, int noOfUsers, double share){
        return amount/(double) noOfUsers;
    }
}
