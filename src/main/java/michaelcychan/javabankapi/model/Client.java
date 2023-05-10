package michaelcychan.javabankapi.model;

import org.springframework.dao.DuplicateKeyException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Client {
    private String name;
    private boolean vip;
    private ArrayList<Account> accounts;

    public Client(String name) {
        this.name = name;
        this.vip = false;
        this.accounts = new ArrayList<>();
    }

    public String getName() {
        return this.name;
    }

    public Boolean getVip() {
        return this.vip;
    }

    public void setVip(Boolean newVipStatus) {
        this.vip = newVipStatus;
    }

    public ArrayList<Account> getAccounts() {
        return this.accounts;
    }

    private void addAccount(Account account){
        this.accounts.add(account);
    }

    public int getNumberOfAccounts(){
        return this.accounts.size();
    }

    public void removeAccount(String currency) {
        int originalLength = this.getNumberOfAccounts();
        this.accounts = this.accounts.stream().filter(acc -> !Objects.equals(acc.getCurrency(), currency)).collect(Collectors.toCollection(ArrayList::new));
        if (originalLength == this.getNumberOfAccounts()) {
            throw new IllegalArgumentException("Client not found");
        }
    }

    public Account getAccountFromCurrency(String currency) {
        List<Account> tempList = this.accounts.stream().filter(acc -> Objects.equals(currency, acc.getCurrency())).toList();
        if (tempList.size() == 0) {
            return null;
        } else {
            return tempList.get(0);
        }
    }

    public void createAccount(Account newAccount){
        boolean alreadyExisted = false;
        for (Account account : this.accounts) {
            if (Objects.equals(account.getCurrency(), newAccount.getCurrency())) {
                throw new DuplicateKeyException("Currency already existed");
            }
        }
        this.addAccount(newAccount);
    }
}
