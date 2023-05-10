package michaelcychan.javabankapi.model;

import org.junit.Test;
import org.springframework.dao.DuplicateKeyException;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.junit.Assert.*;

public class ClientTest {

    @Test
    public void TestClient(){
        Client client1 = new Client("Mike");
        Account mockedUSDAccount = mock(Account.class);
        when(mockedUSDAccount.getCurrency()).thenReturn("USD");
        Account mockedJPYAccount = mock(Account.class);
        when(mockedJPYAccount.getCurrency()).thenReturn("JPY");
        client1.createAccount(mockedUSDAccount);
        client1.createAccount(mockedJPYAccount);

        assertEquals("name should be correct", client1.getName(), "Mike");
        assertEquals("number of accounts should be correct", client1.getNumberOfAccounts(), 2);
    }

    @Test
    public void TestRemoveAccountClient(){
        Client client1 = new Client("Mike");
        Account mockedUSDAccount = mock(Account.class);
        when(mockedUSDAccount.getCurrency()).thenReturn("USD");
        Account mockedJPYAccount = mock(Account.class);
        when(mockedJPYAccount.getCurrency()).thenReturn("JPY");
        client1.createAccount(mockedUSDAccount);
        client1.createAccount(mockedJPYAccount);

        client1.removeAccount("USD");
        assertEquals("number of accounts should be correct", client1.getNumberOfAccounts(), 1);
    }

    @Test
    public void TestNonExistentAccount(){
        Client client1 = new Client("Mike");
        Account mockedUSDAccount = mock(Account.class);
        when(mockedUSDAccount.getCurrency()).thenReturn("USD");
        client1.createAccount(mockedUSDAccount);

        assertThrows("expected to throw", IllegalArgumentException.class, ()->client1.removeAccount("JPY"));
        assertEquals("number of accounts should be correct", 1, client1.getNumberOfAccounts());
    }

    @Test
    public void TestCreateDuplicateAccountRaiseError() {
        Client client1 = new Client("Mike");
        Account mockedUSDAccount = mock(Account.class);
        client1.createAccount(mockedUSDAccount);
        Account mockedUSDAccountDuplicate = mock(Account.class);

        assertThrows("expected to throw", DuplicateKeyException.class,()->client1.createAccount(mockedUSDAccountDuplicate));
    }
}
