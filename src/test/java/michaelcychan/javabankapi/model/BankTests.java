package michaelcychan.javabankapi.model;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BankTests {

    @Test
    public void TestBasicBankWithNoClient() {
        Bank bank = new Bank("Test Bank Name", "Test Bank Address, London");
        assertEquals("Test Bank Name", bank.getBank());
        assertEquals("Test Bank Address, London", bank.getAddress());
        assertEquals(0, bank.getClients().size());
    }

    @Test
    public void TestBankAddNewClient() {
        Bank bank = new Bank("Test Bank Name", "Test Bank Address, London");
        Client mockedClient = mock(Client.class);
        when(mockedClient.getName()).thenReturn("Test Client Name");
        when(mockedClient.getVip()).thenReturn(true);
        bank.addClient(mockedClient);
        assertEquals(1, bank.getClients().size());
        assertEquals("Test Client Name", bank.getClients().get(0).getName());
        assertTrue(bank.getClients().get(0).getVip());
    }
}
