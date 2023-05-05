package michaelcychan.javabankapi;

import michaelcychan.javabankapi.model.Bank;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JavaBankApiApplication {


	public static void main(String[] args) {
		SpringApplication.run(JavaBankApiApplication.class, args);
	}

	@Bean
	public Bank bank(){
		return ReadJsonData.readJsonFile("./data/bank-data.json");
	}

}
