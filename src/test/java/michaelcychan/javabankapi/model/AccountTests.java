package michaelcychan.javabankapi.model;

import org.junit.Test;
import static org.junit.Assert.*;

public class AccountTests {

    @Test
    public void TestGetAmount(){
        Account account1 = new Account("USD", 500);
        assertEquals("getAmount should return correct amount", account1.getAmount(), 500);
        assertEquals("getCurrency should return the correct currency", account1.getCurrency(), "USD");
    }

    @Test
    public void TestAddAndDeductFund(){
        Account account1 = new Account("USD", 500);
        account1.addToAmount(650);
        account1.deductAmount(50);
        assertEquals("Add and Deduct should work", account1.getAmount(), 1100);
    }

    @Test
    public void TestAddToFundError() {
        Account account1 = new Account("USD", 500);
        assertThrows("negative number causes throw", IllegalArgumentException.class,()-> account1.addToAmount(-500));
    }

    @Test
    public void TestDeductFundError() {
        Account account1 = new Account("USD", 600);
        assertThrows("negative number causes throw", IllegalArgumentException.class, ()->account1.deductAmount(-40));
    }


}
