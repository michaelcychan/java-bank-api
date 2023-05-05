package michaelcychan.javabankapi;

import michaelcychan.javabankapi.model.Client;
import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void TestClient(){
        Client client1 = new Client("Mike");
        client1.createAccount("USD", 500);
        client1.createAccount("JPY", 600);

        assertEquals("name should be correct", client1.getName(), "Mike");
        assertEquals("number of accounts should be correct", client1.getNumberOfAccounts(), 2);
    }

    @Test
    public void TestRemoveAccountClient(){
        Client client1 = new Client("Mike");
        client1.createAccount("USD", 500);
        client1.createAccount("JPY", 600);

        client1.removeAccount("USD");
        assertEquals("number of accounts should be correct", client1.getNumberOfAccounts(), 1);
    }

    @Test
    public void TestCreateDuplicateAccountRaiseError() {
        Client client1 = new Client("Mike");
        client1.createAccount("USD", 500);

        assertThrows("expected to throw", DuplicateKeyException.class,()->client1.createAccount("USD", 600));
    }
}
