import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/*
* AccountManagement class is responsible for managing user accounts.
* It provides functionalities to add, remove, and retrieve account information.
* 
* this class never prints
* 
* @author: Mehad Abdi
* @version 2026.09.24
*/

public class AccountManagement {
    //~ Fields ................................................................
 
    /** Every account, keyed by its account number. */
    private HashMap<Integer, CheckingAccount> accounts;

    private int nextAccountNumber;
    private static final int STARTING_ACCOUNT_NUMBER = 1001;



    
    //~ Constructors ..........................................................

    /**
     * Constructs an empty bank with no accounts.
     */

    public AccountManagement() {
        accounts = new HashMap<Integer, CheckingAccount>();
        nextAccountNumber = STARTING_ACCOUNT_NUMBER;
    }

    //~ Public Methods ..........................................................

    /**
     * creates a new checking and stores it
     *
     * @param accountName the display name for the account
     * @return the new account number, or -1 if the name was blank
     */

    public int createChecking(String accountName) {
        if (accountName == null || accountName.trim().isEmpty()) {
            return -1;
        }
        int number = nextAccountNumber;
        accounts.put(number, new CheckingAccount(accountName, number));
        nextAccountNumber++;
        return number;
    }
    /**
     * Creates a savings account and stores it.
     *
     * @param accountName the display name for the account
     * @return the new account number, or -1 if the name was blank
     */

    public int createSavings(String accountName) {
        if (accountName == null || accountName.trim().isEmpty()) {
            return -1;
        }
        int number = nextAccountNumber;
        accounts.put(number, new SavingsAccount(accountName, number));
        nextAccountNumber++;
        return number;
    }

    /**
     * Returns the account with the given number.
     *
     * @param accountNumber the number to look up
     * @return the account, or null if no account has that number
     */

    public CheckingAccount getAccount(int accountNumber) {
        return accounts.get(accountNumber);
    }

    /**
     * Reports whether an account with the given number exists.
     *
     * @param accountNumber the number to check
     * @return true if the account exists
     */
    public boolean accountExists(int accountNumber) {
        return accounts.containsKey(accountNumber);
    }

    /**
     * Deposits into the account with the given number.
     *
     * @param accountNumber the account to deposit into
     * @param amount the amount to deposit
     * @return true if the deposit was applied
     */
    public boolean deposit(int accountNumber, float amount) {
        CheckingAccount account = accounts.get(accountNumber);
        if (account == null) {
            return false;
        }
        return account.deposit(amount);
    }

    /**
     * Withdraws from the account with the given number.
     *
     * @param accountNumber the account to withdraw from
     * @param amount the amount to withdraw
     * @return true if the withdrawal was applied
     */
    public boolean withdraw(int accountNumber, float amount) {
        CheckingAccount account = accounts.get(accountNumber);
        if (account == null) {
            return false;
        }
        return account.withdraw(amount);
    }

    /**
     * Moves money from one account to another. The withdrawal happens first;
     * the deposit only happens if it succeeded, so a failed transfer leaves
     * both balances exactly as they were.
     *
     * @param fromAccount the account to take money from
     * @param toAccount the account to give money to
     * @param amount the amount to move
     * @return true if the full transfer completed
     */

    public boolean transfer(int fromAccount, int toAccount, float amount) {
        if (fromAccount == toAccount || amount <= 0) {
            return false;
        }
 
        CheckingAccount from = accounts.get(fromAccount);
        CheckingAccount to = accounts.get(toAccount);
        if (from == null || to == null) {
            return false;
        }
 
        if (!from.withdraw(amount)) {
            return false;
        }
 
        if (!to.deposit(amount)) {
            // The deposit failed after the money was already taken out, so
            // put it back rather than losing it.
            from.deposit(amount);
            return false;
        }
 
        return true;
    }
 
 
    /**
     * Removes an account from the bank.
     *
     * @param accountNumber the account to delete
     * @return true if an account was removed, false if none had that number
     */

    public boolean deleteAccount(int accountNumber) {
        return accounts.remove(accountNumber) != null;
    }
 
 
    /**
     * Returns every account, ordered by account number. A HashMap has no
     * reliable iteration order, so the numbers are sorted first to give the
     * user a stable listing.
     *
     * @return the accounts in ascending account-number order, empty if none
     */

    public ArrayList<CheckingAccount> listAccounts() {
        ArrayList<Integer> numbers = new ArrayList<Integer>(accounts.keySet());
        Collections.sort(numbers);
 
        ArrayList<CheckingAccount> result =
            new ArrayList<CheckingAccount>();
        for (int number : numbers) {
            result.add(accounts.get(number));
        }
        return result;
    }
 
 
    /**
     * Returns how many accounts the bank currently holds.
     *
     * @return the account count
     */
    
    public int getAccountCount() {
        return accounts.size();
    }
}

