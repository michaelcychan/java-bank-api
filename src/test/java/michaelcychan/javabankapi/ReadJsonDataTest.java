package michaelcychan.javabankapi;

import michaelcychan.javabankapi.model.Bank;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ReadJsonDataTest {

    @Test
    public void TestReadJsonData() {
        Bank bank = ReadJsonData.readJsonFile("./data/bank-data.json");
        assertEquals("Michael Bank",bank.getBank() );
        assertEquals("222B Bakers Street, London", bank.getAddress());
        assertEquals("Michael", bank.getClients().get(0).getName());
    }
}
