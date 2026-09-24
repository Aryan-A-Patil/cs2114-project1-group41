import student.TestCase;
/**
 * // -------------------------------------------------------------------------
/**
 *  Tests the savings account class
 * 
 *  @author Caleb Terstegen
 *  @version 2026.09.24
 */
public class SavingsAccountTest
    extends TestCase
{
    //~ Fields ................................................................
    private SavingsAccount account1;
    //~ Constructors ..........................................................

    //~Public  Methods ........................................................
    /**
     * sets up
     */
    public void setUp() {
        account1 = new SavingsAccount("John", 123456);
    }
    /**
     * tests the get interest method
     */
    public void testGetInterest() {
        assertEquals(0.05F, account1.getInterest(), 0.001F);
    }
    /**
     * tests the get withdraw limit method
     */
    public void testGetWithdrawLimit() {
        assertEquals(500F, account1.getWithdrawalLimit(), 0.001F);
    }
    /**
     * tests the apply interest method
     */
    public void testApplyInterest() {
        account1.deposit(100F);
        assertEquals(100F, account1.getBalance(), 0.001F);
        assertEquals(50F, account1.applyInterest(10), 0.001F);
        assertEquals(150F, account1.getBalance(), 0.001F);
    }
}
