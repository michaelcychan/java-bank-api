package michaelcychan.javabankapi.record;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BankDetails(String bank, String address, @JsonProperty("clients-number") int cliNum) {
}
