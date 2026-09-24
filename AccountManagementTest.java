import student.TestCase;

/**
 * Tests every public method of AccountManagement with one normal case and one
 * bad-input case, following the project's test plan.
 *
 * @author Mehad
 * @version 2026.09.24
 */
public class AccountManagementTest extends TestCase
{
    //~ Fields ................................................................

    private AccountManagement manager;


    //~ Public Methods ........................................................

    /**
     * Creates a fresh, empty bank before each test so no test can be affected
     * by another one's accounts.
     */
    public void setUp() {
        manager = new AccountManagement();
    }


    /**
     * A new bank holds nothing.
     */
    public void testNewBankIsEmpty() {
        assertEquals(0, manager.getAccountCount());
        assertEquals(0, manager.listAccounts().size());
    }


    /**
     * Normal: creating checking accounts hands out 1001, then 1002.
     */
    public void testCreateChecking() {
        assertEquals(1001, manager.createChecking("John"));
        assertEquals(1002, manager.createChecking("Jane"));
        assertEquals(2, manager.getAccountCount());

        CheckingAccount account = manager.getAccount(1001);
        assertNotNull(account);
        assertEquals("John", account.getAccountName());
        assertEquals(1001, account.getAccountNumber());
        assertEquals(0.0F, account.getBalance(), 0.001F);
    }


    /**
     * Bad input: a blank or null name creates nothing.
     */
    public void testCreateCheckingBadName() {
        assertEquals(-1, manager.createChecking(""));
        assertEquals(-1, manager.createChecking("   "));
        assertEquals(-1, manager.createChecking(null));
        assertEquals(0, manager.getAccountCount());
    }


    /**
     * Normal: a savings account is stored and is really a SavingsAccount.
     */
    public void testCreateSavings() {
        assertEquals(1001, manager.createSavings("Saver"));
        assertEquals(1, manager.getAccountCount());
        assertTrue(manager.getAccount(1001) instanceof SavingsAccount);
    }


    /**
     * Bad input: a blank savings name creates nothing.
     */
    public void testCreateSavingsBadName() {
        assertEquals(-1, manager.createSavings(""));
        assertEquals(0, manager.getAccountCount());
    }


    /**
     * Normal and bad input for looking an account up.
     */
    public void testGetAccount() {
        manager.createChecking("John");
        assertNotNull(manager.getAccount(1001));
        assertNull(manager.getAccount(9999));
    }


    /**
     * Normal and bad input for the existence check.
     */
    public void testAccountExists() {
        manager.createChecking("John");
        assertTrue(manager.accountExists(1001));
        assertFalse(manager.accountExists(9999));
    }


    /**
     * Normal: $100 into an account holding $500 leaves $600.
     */
    public void testDeposit() {
        manager.createChecking("John");
        manager.deposit(1001, 500.0F);

        assertTrue(manager.deposit(1001, 100.0F));
        assertEquals(600.0F, manager.getAccount(1001).getBalance(), 0.001F);
    }


    /**
     * Bad input: a negative deposit and a missing account both change nothing.
     */
    public void testDepositBadInput() {
        manager.createChecking("John");
        manager.deposit(1001, 500.0F);

        assertFalse(manager.deposit(1001, -50.0F));
        assertEquals(500.0F, manager.getAccount(1001).getBalance(), 0.001F);

        assertFalse(manager.deposit(9999, 50.0F));
    }


    /**
     * Normal: $200 out of $500 leaves $300.
     */
    public void testWithdraw() {
        manager.createChecking("John");
        manager.deposit(1001, 500.0F);

        assertTrue(manager.withdraw(1001, 200.0F));
        assertEquals(300.0F, manager.getAccount(1001).getBalance(), 0.001F);
    }


    /**
     * Bad input: overdrawing and a missing account both change nothing.
     */
    public void testWithdrawBadInput() {
        manager.createChecking("John");
        manager.deposit(1001, 500.0F);

        assertFalse(manager.withdraw(1001, 1000.0F));
        assertEquals(500.0F, manager.getAccount(1001).getBalance(), 0.001F);

        assertFalse(manager.withdraw(9999, 50.0F));
    }


    /**
     * A withdrawal from a savings account must actually reduce the balance
     * the account reports.
     */
    public void testWithdrawFromSavings() {
        manager.createSavings("Saver");
        manager.deposit(1001, 100.0F);

        assertTrue(manager.withdraw(1001, 50.0F));
        assertEquals(50.0F, manager.getAccount(1001).getBalance(), 0.001F);
    }


    /**
     * Normal: $200 moves across and the total held by the bank is unchanged.
     */
    public void testTransfer() {
        manager.createChecking("John");
        manager.createChecking("Jane");
        manager.deposit(1001, 500.0F);

        assertTrue(manager.transfer(1001, 1002, 200.0F));
        assertEquals(300.0F, manager.getAccount(1001).getBalance(), 0.001F);
        assertEquals(200.0F, manager.getAccount(1002).getBalance(), 0.001F);
    }


    /**
     * Bad input: a transfer larger than the balance leaves BOTH accounts
     * untouched. This is the test that proves money cannot be created by a
     * half-completed transfer.
     */
    public void testTransferInsufficientFunds() {
        manager.createChecking("John");
        manager.createChecking("Jane");
        manager.deposit(1001, 100.0F);

        assertFalse(manager.transfer(1001, 1002, 500.0F));
        assertEquals(100.0F, manager.getAccount(1001).getBalance(), 0.001F);
        assertEquals(0.0F, manager.getAccount(1002).getBalance(), 0.001F);
    }


    /**
     * Bad input: a transfer to a missing account must not destroy the money
     * it would have taken out.
     */
    public void testTransferMissingAccount() {
        manager.createChecking("John");
        manager.deposit(1001, 100.0F);

        assertFalse(manager.transfer(1001, 9999, 50.0F));
        assertEquals(100.0F, manager.getAccount(1001).getBalance(), 0.001F);

        assertFalse(manager.transfer(9999, 1001, 50.0F));
        assertEquals(100.0F, manager.getAccount(1001).getBalance(), 0.001F);
    }


    /**
     * Bad input: transferring to the same account, or a non-positive amount,
     * is refused.
     */
    public void testTransferSameAccountOrBadAmount() {
        manager.createChecking("John");
        manager.createChecking("Jane");
        manager.deposit(1001, 100.0F);

        assertFalse(manager.transfer(1001, 1001, 50.0F));
        assertFalse(manager.transfer(1001, 1002, -50.0F));
        assertFalse(manager.transfer(1001, 1002, 0.0F));
        assertEquals(100.0F, manager.getAccount(1001).getBalance(), 0.001F);
        assertEquals(0.0F, manager.getAccount(1002).getBalance(), 0.001F);
    }


    /**
     * Normal: deleting an account removes it from the bank.
     */
    public void testDeleteAccount() {
        manager.createChecking("John");
        manager.createChecking("Jane");

        assertTrue(manager.deleteAccount(1001));
        assertEquals(1, manager.getAccountCount());
        assertNull(manager.getAccount(1001));
        assertNotNull(manager.getAccount(1002));
    }


    /**
     * Bad input: deleting an account that is not there, twice over.
     */
    public void testDeleteAccountBadInput() {
        manager.createChecking("John");

        assertFalse(manager.deleteAccount(9999));
        assertTrue(manager.deleteAccount(1001));
        assertFalse(manager.deleteAccount(1001));
        assertEquals(0, manager.getAccountCount());
    }


    /**
     * A deleted account's number is never handed out again, so an old number
     * can never point at a different person's account.
     */
    public void testAccountNumbersAreNotReused() {
        manager.createChecking("John");
        manager.deleteAccount(1001);

        assertEquals(1002, manager.createChecking("Jane"));
        assertNull(manager.getAccount(1001));
    }


    /**
     * Normal: accounts come back sorted by number regardless of map order.
     */
    public void testListAccounts() {
        manager.createChecking("John");
        manager.createSavings("Jane");
        manager.createChecking("Joe");

        assertEquals(3, manager.listAccounts().size());
        assertEquals(1001, manager.listAccounts().get(0).getAccountNumber());
        assertEquals(1002, manager.listAccounts().get(1).getAccountNumber());
        assertEquals(1003, manager.listAccounts().get(2).getAccountNumber());
    }


    /**
     * Bad input: an empty bank returns an empty list, never null.
     */
    public void testListAccountsEmpty() {
        assertNotNull(manager.listAccounts());
        assertEquals(0, manager.listAccounts().size());
    }


    /**
     * The count tracks creations and deletions, and failed creations do not
     * count.
     */
    public void testGetAccountCount() {
        assertEquals(0, manager.getAccountCount());
        manager.createChecking("John");
        manager.createChecking("Jane");
        assertEquals(2, manager.getAccountCount());

        manager.createChecking("");
        assertEquals(2, manager.getAccountCount());

        manager.deleteAccount(1001);
        assertEquals(1, manager.getAccountCount());
    }
}