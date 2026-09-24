
public class SavingsAccount
extends CheckingsAccount {
    //~ Fields ................................................................
    String accountName;
    int accountNumber;
    float interest;
    float withdrawlLimit;
    float balance;

    
    //~ Constructors ..........................................................
    /**
     * Constructs the SavingsAccount object
     */
    SavingsAccount(String accountName, int accountNumber) {
        super(accountName, accountNumber);
        this.interest = 0.05F;
        this.withdrawlLimit = 500;
        this.balance = 0.0f;
    }

//~Public  Methods ........................................................
    /**
     * Returns the interest rate for the savings account
     */
    public float getInterest() {
        return interest;
    }
    /**
     * Applies interest to the account balance
     * @param months
     * @return the amount of interest applied
     */
    public float applyInterest(int months) {
        float interestAmount = balance * (interest) * months;
        balance += interestAmount;
        return interestAmount;
    }

    /**
     * Returns the withdrawal limit for the savings account
     * @return the withdrawal limit
     */
    public float getWithdrawalLimit() {
        return withdrawlLimit;
    }

    @Override 
    public float withdraw(float amount) {
        if (amount <= balance && amount <= withdrawlLimit) {
            balance -= amount;
        }
        else {
            System.out.println("Withdrawal amount exceeds limit or insufficient funds");
        }
        return balance;
    }

}