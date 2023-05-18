package michaelcychan.javabankapi.controller;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicLong;

import michaelcychan.javabankapi.model.Bank;
import michaelcychan.javabankapi.model.Client;
import michaelcychan.javabankapi.record.BankDetails;
import michaelcychan.javabankapi.record.GreetingCounter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JavaBankApiController {
    private static final String template = "Hello, %s! Welcome to Michael Bank!";
    private final AtomicLong counter = new AtomicLong();
    private final Bank bank;

    public JavaBankApiController(Bank bank){
        this.bank = bank;
    }

    @CrossOrigin
    @GetMapping("/greeting")
    public GreetingCounter greeting(@RequestParam(value = "name", defaultValue = "my friend") String name) {
        return new GreetingCounter(counter.incrementAndGet(), String.format(template, name));
    }

    @CrossOrigin
    @GetMapping("/get-bank-detail")
    public BankDetails getBankDetails() {
        return new BankDetails(this.bank.getBank(), this.bank.getAddress(), this.bank.getClients().size());
    }

    @CrossOrigin
    @GetMapping("/client/{name}")
    public ResponseEntity<Client> getClient(@PathVariable(name = "name") String clientName) {
        System.out.printf("Getting Client name: %s\n", clientName);
        try {
            for (Client cli : bank.getClients()) {
                if (Objects.equals(clientName, cli.getName())) {
                    return ResponseEntity.status(HttpStatus.OK).body(cli);
                }
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        } catch(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
