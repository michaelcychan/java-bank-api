package michaelcychan.javabankapi.controller;

import michaelcychan.javabankapi.model.Account;
import michaelcychan.javabankapi.model.Bank;
import michaelcychan.javabankapi.model.Client;
import michaelcychan.javabankapi.record.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    private final Bank bank;

    public AccountController(Bank bank) {
        this.bank = bank;
    }
    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody Map<String, Object> request){

        String clientName = (String) request.get("client");
        String currency = (String) request.get("currency");
        int amount = (int) request.get("amount");

        try {

            if (!checkBank()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            Optional<Client> foundClient = bank.getClients().stream()
                    .filter(client -> Objects.equals(client.getName(), clientName))
                    .findFirst();

            if (foundClient.isEmpty()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            Account newAccount = new Account(currency, amount);

            foundClient.get().createAccount(newAccount);

            return ResponseEntity.status(HttpStatus.OK).body(foundClient.get().getAccountFromCurrency(currency));


        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @CrossOrigin
    @PutMapping("/move-fund")
    public ResponseEntity<Account> changeFund(@RequestBody Map<String, Object> request) {
        try {
            String clientName = (String) request.get("client");
            String currency = (String) request.get("currency");
            Optional<Integer> deposit = Optional.ofNullable((Integer) request.get("deposit"));
            Optional<Integer> withdraw = Optional.ofNullable((Integer) request.get("withdraw"));

            if (!checkBank()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            Optional<Client> foundClient = bank.getClients().stream()
                    .filter(client -> Objects.equals(client.getName(), clientName))
                    .findFirst();

            if (foundClient.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (foundClient.get().getAccountFromCurrency(currency) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if ((deposit.isPresent() && withdraw.isPresent()) || (deposit.isEmpty() && withdraw.isEmpty())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            } else if (deposit.isPresent()) {
                foundClient.get().getAccountFromCurrency(currency).addToAmount(deposit.get());
                return ResponseEntity.status(HttpStatus.OK).body(foundClient.get().getAccountFromCurrency(currency));
            } else {
                foundClient.get().getAccountFromCurrency(currency).deductAmount(withdraw.get());
                return ResponseEntity.status(HttpStatus.OK).body(foundClient.get().getAccountFromCurrency(currency));
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Message> removeAccount(@RequestBody Map<String, Object> request) {
        try {
            String clientName = (String) request.get("client");
            String currency = (String) request.get("currency");

            if (!checkBank()) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }

            Optional<Client> foundClient = bank.getClients().stream()
                    .filter(client -> Objects.equals(client.getName(), clientName))
                    .findFirst();

            if (foundClient.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            if (foundClient.get().getAccountFromCurrency(currency) == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            foundClient.get().removeAccount(currency);

            if (foundClient.get().getAccountFromCurrency(currency) != null) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new Message(currency + " account CANNOT be deleted."));
            }

            Message json = new Message(currency + " account has been deleted successfully");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    private boolean checkBank(){
        return !(bank == null || bank.getClients() == null);
    }
}
