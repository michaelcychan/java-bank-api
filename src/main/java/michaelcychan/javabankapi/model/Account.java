package michaelcychan.javabankapi.model;

public class Account {
    private final String currency;
    private int amount;

    public Account(String currency, int amount) {
        this.currency = currency;
        this.amount = amount;
    }

    public String getCurrency(){
        return this.currency;
    }

    public int getAmount(){
        return this.amount;
    }

    public void addToAmount(int newFund){
        if (newFund < 0) {
            throw new IllegalArgumentException("New fund must not be negative");
        }
        this.amount += newFund;
    }

    public void deductAmount(int withdrawnFund) {
        if (withdrawnFund < 0) {
            throw new IllegalArgumentException("Withdraw amount must not be negative");
        }
        this.amount -= withdrawnFund;
    }
}
