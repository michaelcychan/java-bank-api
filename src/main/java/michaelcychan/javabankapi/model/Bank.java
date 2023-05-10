package michaelcychan.javabankapi.model;

import java.util.ArrayList;

public class Bank {
    private String bank;
    private String address;
    private ArrayList<Client> clients;

    public Bank(){}

    public Bank(String bankName, String address){
        this.bank = bankName;
        this.address = address;
        this.clients = new ArrayList<Client>();
    }

    public String getBank(){
        return this.bank;
    }

    public String getAddress(){
        return this.address;
    }

    public ArrayList<Client> getClients() {
        return this.clients;
    }

    public void addClient(Client newClient) {
        this.clients.add(newClient);
    }

}
