import student.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests the checking account class
 * 
 *  @author Caleb Terstegen
 *  @version 2026.09.24
 */
public class CheckingAccountTest
    extends TestCase
{
    //~ Fields ................................................................
    private CheckingAccount account1;

    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * sets up
     */
    public void setUp() {
        account1 = new CheckingAccount("John", 123456);
    }
    
    /**
     * tests the get balance method
     */
    public void testGetBalance() {
        assertEquals(0.0F, account1.getBalance(), 0.001F);
    }
    /**
     * tests the get account number method
     */
    public void testGetAccountNumber() {
        assertEquals(123456, account1.getAccountNumber());
    }
    /**
     * tests the get account name method
     */
    public void testGetAccountName() {
        assertEquals("John", account1.getAccountName());
    }
    /**
     * tests the deposit method
     */
    public void testDeposit() {
        assertEquals(0.0F, account1.getBalance(), 0.001F);
        account1.deposit(100.0F);
        assertEquals(100.0F, account1.getBalance(), 0.001F);
    }
    /**
     * tests the withdraw method
     */
    public void testWithdraw() {
        assertEquals(0.0F, account1.getBalance(), 0.001F);
        assertEquals(0.0F, account1.withdraw(100.0F), 0.001F);
        assertEquals(0.0F, account1.getBalance(), 0.001F);
        account1.deposit(100.0F);
        assertEquals(100.0F, account1.getBalance(), 0.001F);
        assertEquals(0.0F, account1.withdraw(100.0F), 0.001F);
        assertEquals(0.0F, account1.getBalance(), 0.001F);
    }
}
